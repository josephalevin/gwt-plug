/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.rebind;

import java.net.URL;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 *
 * @author josephalevin
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class AdvertisedModuleProcessor extends AbstractProcessor{
    
    private ProcessingEnvironment env;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        this.env = env;
    }
    
    

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment round) {
        System.out.println("******************************************");       
        
//        System.err.println(System.getProperty("java.class.path"));
        System.out.println(ClassLoader.getSystemClassLoader().toString());
        System.out.println(Thread.currentThread().getContextClassLoader().toString());
        
        
//       for(Entry entry : System.getProperties().entrySet()){
//           System.out.printf("%0$s = %1$s\n", entry.getKey(), entry.getValue());
//       }
//        
        try{            
//            FileObject fo = env.getFiler().getResource(StandardLocation.CLASS_PATH, "", "META-INF/services/gwt.modules.advertised");            
//            System.out.println(fo.toUri().toString());   
            
            Enumeration<URL> e = Thread.currentThread().getContextClassLoader().getResources("META-INF/services/gwt.modules.advertised");
            while(e.hasMoreElements()){
                URL url = e.nextElement();
                System.out.println(url.toString());
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
//        return false;
        //throw new UnsupportedOperationException("Hello");
        return false;
    }

}
