/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.dev.resource.Resource;
import java.net.URL;

import java.util.Enumeration;

/**
 *
 * @author josephalevin
 */
public class ServiceLoaderLookupGenerator extends Generator{
    private static final String META_INF_SERVICES = "META-INF/services/";
    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
         logger.log(TreeLogger.Type.INFO, typeName);
        
//        for(Resource res : context.getResourcesOracle().getResources()){
//            logger.log(TreeLogger.Type.INFO, res.getLocation());
//        }
        
//        for(JPackage pkg : context.getTypeOracle().getPackages()){
//            logger.log(TreeLogger.Type.INFO, pkg.getName());
//        }
//         
//          logger.log(TreeLogger.Type.INFO, System.getProperty("java.class.path"));
//        
//          try{
//            Enumeration<URL> e = ClassLoader.getSystemResources(META_INF_SERVICES + "com.example.Plugin");
//            while(e.hasMoreElements()){
//                URL url = e.nextElement();
//                logger.log(TreeLogger.Type.INFO, url.toString());
//            }
//          }
//          catch(Exception e){
//              logger.log(TreeLogger.Type.ERROR, e.getLocalizedMessage(), e);
//              throw new UnableToCompleteException();
//          }
        
        throw new UnsupportedOperationException("Not supported yet.");
         
    }

   

}
