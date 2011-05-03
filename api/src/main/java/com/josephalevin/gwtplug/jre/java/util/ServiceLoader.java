/**
 * Copyright © 2010-2011 Joseph A. Levin <josephalevin@gmail.com>
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
