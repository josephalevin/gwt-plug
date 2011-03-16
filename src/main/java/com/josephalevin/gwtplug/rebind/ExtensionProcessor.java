/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.rebind;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 *
 * @author josephalevin
 */
@SupportedAnnotationTypes("com.josephalevin.gwtplug.client.Extension")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ExtensionProcessor extends AbstractProcessor{
    
    private ProcessingEnvironment env;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);        
        this.env = env;
    }
    
    

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment re) {
        return true;
    }

}
