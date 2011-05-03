/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java.util;

import com.google.gwt.core.client.GWT;
import com.josephalevin.gwtplug.client.ServiceLoaderLookup;

/**
 *
 * @author Joseph A. Levin <josephalevin@gmail.com>
 */
public final class ServiceLoader <S> implements Iterable<S>{
    private final Class<S> service;
    
    private static final ServiceLoaderLookup lookup = GWT.create(ServiceLoaderLookup.class);
    
    private ServiceLoader(Class<S> service) {
        this.service = service;
    }
    
    @Override
    public Iterator<S> iterator(){
        return lookup.lookup(service);
    }
    
    public static <S> ServiceLoader<S> load(Class<S> service){
        return new ServiceLoader<S>(service);
    }
     
    
    
    public static <S> ServiceLoader<S> loadInstalled(Class<S> service){
        return new ServiceLoader<S>(service);
    }    

     /**
     * Returns a string describing this service.
     *
     * @return  A descriptive string
     */
    @Override
    public String toString() {
	return "java.util.ServiceLoader[" + service.getName() + "]";
    }
    
    
}
