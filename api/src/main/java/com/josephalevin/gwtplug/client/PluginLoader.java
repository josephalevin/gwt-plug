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
package com.josephalevin.gwtplug.client;

import com.google.gwt.core.client.GWT;
import java.util.Iterator;

/**
 *
 * @author Joseph A. Levin <josephalevin@gmail.com>
 */
public final class PluginLoader <P> implements Iterable<P>{
    private final Class<P> plugin;
    
    private static final PluginLookup lookup = GWT.create(PluginLookup.class);
    
    private PluginLoader(Class<P> plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public Iterator<P> iterator(){
        return lookup.lookup(plugin);
    }
    
    public Class<P> getPluginType(){
        return plugin;
    }
    
    public static <S> PluginLoader<S> load(Class<S> service){
        return new PluginLoader<S>(service);
    }        
     

     /**
     * Returns a string describing this service.
     *
     * @return  A descriptive string
     */
    @Override
    public String toString() {
	return PluginLoader.class.getName() + "[" +  plugin.getName() + "]";
    }
    
    
}
