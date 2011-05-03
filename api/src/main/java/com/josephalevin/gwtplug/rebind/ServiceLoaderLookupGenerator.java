/**
 * This file is released under the MIT License.
 *
 * Copyright (c) 2011 Joseph A. Levin <josephalevin@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.josephalevin.gwtplug.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.josephalevin.gwtplug.client.ServiceLoaderIterator;
import com.josephalevin.gwtplug.client.ServiceLoaderLookup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author josephalevin
 */
public class ServiceLoaderLookupGenerator extends Generator {

    private static final String META_INF_SERVICES = "META-INF/services/";

    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
        if(!ServiceLoaderLookup.class.getName().equals(typeName)){
            logger.log(TreeLogger.ERROR, String.format("Do not extend or implement the %s.", ServiceLoaderLookup.class.getName()));
            throw new UnableToCompleteException();
        }
        
        TypeOracle oracle = context.getTypeOracle();
        
        JClassType type = oracle.findType(typeName);
        
        String packageName  = type.getPackage().getName();
        String implName = type.getName() + "Impl";
        
        PrintWriter writer = context.tryCreate(logger, packageName, implName);
        
        if(writer != null){

            Map <JClassType, List<JClassType>> lookup = new HashMap<JClassType, List<JClassType>>();
            for (JClassType service : oracle.getTypes()) {
                List<JClassType> impls = implementations(logger, oracle, service);
                if(impls != null && !impls.isEmpty()){
                    lookup.put(service, impls);
                }
            }
                        
        
            writer.format("package %s;", packageName);
            writer.println();
            
            writer.format("import %s;", Iterator.class.getName());
            writer.println();
            
            writer.format("public class %s implements %s {", implName, typeName);
            writer.println();
            
            
            writer.println("public <S> Iterator<S> lookup(Class<S> service){");
            
            //loop over each service type and map to the iterator
            for(Entry<JClassType, List<JClassType>> entry : lookup.entrySet()){
                JClassType serviceType = entry.getKey();
                List<JClassType> impls = entry.getValue();
                
                writer.format("if (service.getName().equals(\"%s\")){", serviceType.getQualifiedBinaryName());
                writer.println();
                
                writer.format("return (Iterator<S>) new %s<%s>(%s){", ServiceLoaderIterator.class.getName(),serviceType.getQualifiedBinaryName(), impls.size());
                writer.println();
                
                writer.format("public %s get (int index){", serviceType.getQualifiedBinaryName());
                writer.println("switch(index){");
                for(int i = 0; i < impls.size(); i++){
                    writer.format("case %s: return new %s();", i, impls.get(i).getQualifiedBinaryName());
                    writer.println();
                }
                writer.println("default:throw new ArrayIndexOutOfBoundsException(index);");
                writer.println("}");                                
                writer.println("}");
                
                writer.println("};");
                
                writer.println("}");
            }
            
            writer.println("return null;");
            
            writer.println("}");                              

            writer.println("}");

            //commit the generated source code
            context.commit(logger, writer);  
        }
        return String.format("%s.%s", packageName, implName);                

    }    
    


    private List<JClassType> implementations(TreeLogger logger, TypeOracle oracle, JClassType type){
        try{
            Enumeration<URL> i = ClassLoader.getSystemResources(META_INF_SERVICES + type.getQualifiedBinaryName());
            List<JClassType> result = new ArrayList<JClassType>();
            while (i.hasMoreElements()) {
                URL url = i.nextElement();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));                
                    for(String line = reader.readLine(); line != null; line = reader.readLine()){
                        line = line.trim();
                        if(!line.isEmpty()){
                            try{
                                JClassType impl = oracle.getType(line);
                                if(impl != null){
                                    result.add(impl);
                                }
                            }
                            catch(NotFoundException nfe){
                                //means the class is not able to be used by the client code
                                logger.log(TreeLogger.WARN, String.format("Ignoring type not visible to GWT compiler: %s", line));
                                continue;
                            }
                        }
                    }            
                } catch (IOException ioe) {
                    logger.log(TreeLogger.Type.WARN, "Unable to load service definitions", ioe);                
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e) {
                            logger.log(TreeLogger.ERROR, "Unable to close stream.", e);                        
                        }
                    }
                }
            }
            return result;
        }
        catch (IOException ioe){
            logger.log(TreeLogger.Type.WARN, "Unable to load service definitions", ioe);   
            return Collections.emptyList();
        }
    }
}

