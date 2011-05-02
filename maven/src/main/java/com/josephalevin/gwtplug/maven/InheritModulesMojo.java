    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josephalevin.gwtplug.maven;

import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jfrog.jade.plugins.common.injectable.MvnInjectableMojoSupport;
import org.jfrog.maven.annomojo.annotations.MojoComponent;
import org.jfrog.maven.annomojo.annotations.MojoGoal;
import org.jfrog.maven.annomojo.annotations.MojoParameter;
import org.jfrog.maven.annomojo.annotations.MojoPhase;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.vfs.Vfs.File;

/**
 *
 * @author josephalevin
 */
@MojoGoal("inherit")
@MojoPhase("generate-sources")
public class InheritModulesMojo extends MvnInjectableMojoSupport {

    @MojoComponent
    protected ArtifactMetadataSource artifactMetadataSource;
    @MojoParameter(required = true)
    private String createModule;
    @MojoParameter(expression = "${project.build.directory}/generated-sources/gwtplug", required = true)
    private java.io.File generateDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            Set<Artifact> artifacts = getProject().createArtifacts(getArtifactFactory(), null, null);

            ArtifactResolutionResult arr = getArtifactResolver().resolveTransitively(artifacts, getProject().getArtifact(), getProject().getRemoteArtifactRepositories(), getLocalRepository(), artifactMetadataSource);

            Set<Artifact> dependencies = arr.getArtifacts();

            List<URL> urls = new ArrayList<URL>();

            for (Artifact art : dependencies) {
                URL url = art.getFile().toURI().toURL();
                System.out.println(url);
                urls.add(url);
            }

            List<File> modules = new ArrayList<File>();
            Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(urls).setScanners(new ResourcesScanner(), new GWTModuleScanner(modules)));

            if (reflections == null) {
                throw new IllegalStateException("Failed to execute reflections.");
            }



            List<String> advertiedModules = new ArrayList<String>();
            for (File file : modules) {
//                System.out.println(file.getName());
//                System.out.println(file.getRelativePath());

                if (file.getRelativePath().startsWith("com/google/gwt")) {
                    //System.out.println(String.format("Skipping Google Module: %s", file.getRelativePath()));
                    continue;
                }
//                System.out.println(String.format("Scanning: %s", file.getRelativePath()));

                try {
                    SAXReader sax = new SAXReader(false);

                    Document doc = sax.read(file.getInputStream());
                    Node node = doc.selectSingleNode("/module/set-configuration-property[@name='gwtplug.advertised']");


                    if (node instanceof Element) {
                        Element element = (Element) node;
                        Boolean advertised = false;
                        try {
                            advertised = Boolean.valueOf(element.attributeValue("value"));
                            if (advertised) {
                                String module = file.getRelativePath().replace("/", ".");
                                module = module.replaceAll("\\.gwt\\.xml$", "");
                                advertiedModules.add(module);
                                System.out.println(module);
                            }
                        } catch (Exception e) {
                            advertised = false;
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            System.out.println(createModule);

            java.io.File file = new java.io.File(generateDirectory, createModule.replace(".", "/") + ".gwt.xml");
            file.getParentFile().mkdirs();

            StringBuilder buffer = new StringBuilder();
            buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            buffer.append("\n");
            buffer.append("<module>");
            for (String mod : advertiedModules) {
                buffer.append(String.format("<inherits name=\"%s\"/>", mod));
            }
            buffer.append("</module>");            
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.write(buffer.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        

        getProject().addCompileSourceRoot(generateDirectory.getAbsolutePath());
        

    }

    private class GWTModuleScanner extends ResourcesScanner {

        private final List<File> modules;

        public GWTModuleScanner(List<File> modules) {
            this.modules = modules;
        }

        @Override
        public boolean acceptsInput(String file) {
            return file.endsWith(".gwt.xml");
        }

        @Override
        public void scan(File file) {
            super.scan(file);
            modules.add(file);
        }
    }
}
