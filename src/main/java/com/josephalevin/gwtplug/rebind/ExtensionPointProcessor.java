/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josephalevin.gwtplug.rebind;

import com.josephalevin.gwtplug.client.Extension;
import com.josephalevin.gwtplug.client.ExtensionLoader;
import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 *
 * @author josephalevin
 */
@SupportedAnnotationTypes("com.josephalevin.gwtplug.client.Extension")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ExtensionPointProcessor extends AbstractProcessor {

    private ProcessingEnvironment env;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        this.env = env;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {

            for (TypeElement currAnnotation : annotations) {

                if (currAnnotation.getQualifiedName().contentEquals(Extension.class.getName())) {

                    for (Element extPoint : roundEnv.getElementsAnnotatedWith(currAnnotation)) {
                    }
                }
            }
        }
        return true;
    }

    void generateExtensionPointLoader(Element extPoint) {
        GenerationHelper writer = null;
        try {
            String packageName = getPackageName((TypeElement) extPoint);
            String extPointSimpleName = getSimpleClassName((TypeElement) extPoint);
            String extPointLoaderSimpleName = extPointSimpleName + "Loader";            
            String extPointLoaderClassName = packageName + "." + extPointLoaderSimpleName;
            
            Writer source = env.getFiler().createSourceFile(extPointLoaderClassName, extPoint).openWriter();
            writer = new GenerationHelper(source);
            
            writer.generatePackageDeclaration(packageName);
            
            writer.generateClassHeader(extPointLoaderSimpleName, ExtensionLoader.class.getName());
            
            
            
            writer.generateClassFooter();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public String getPackageName(TypeElement classRepresenter) {
        return env.getElementUtils().getPackageOf(classRepresenter).getQualifiedName().toString();
    }

    public String getSimpleClassName(TypeElement classRepresenter) {
        return classRepresenter.getSimpleName().toString();
    }
}
