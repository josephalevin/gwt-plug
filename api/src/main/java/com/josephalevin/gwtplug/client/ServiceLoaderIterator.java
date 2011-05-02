/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josephalevin.gwtplug.client;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Joseph A. Levin <josephalevin@gmail.com>
 */
public abstract class ServiceLoaderIterator <S> implements Iterator<S> {

        private final int size;
        private int index = 0;
        public ServiceLoaderIterator(int size) {
            this.size = size;            
        }
        
        protected abstract S get(int index);

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public S next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            S result = get(index);
            index++;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
