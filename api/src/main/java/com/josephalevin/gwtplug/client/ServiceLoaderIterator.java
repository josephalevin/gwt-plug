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
