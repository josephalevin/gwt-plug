/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.client;

import java.util.Iterator;

/**
 *
 * @author josephalevin
 */
public interface ServiceLoaderLookup{    
    public <S> Iterator<S> lookup(Class<S> service);
}
