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
package com.josephalevin.gwtplug.example.barnyard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.josephalevin.gwtplug.client.ServiceLoader;
import com.josephalevin.gwtplug.example.barnyard.api.client.Animal;
import java.util.Iterator;



/**
 *
 * @author josephalevin
 */
public class MainEntryPoint implements EntryPoint{

    @Override
    public void onModuleLoad() {
        final Label label = new Label("Welcome to the gwt-plug barnyard!");               
        RootPanel.get().add(label);
        
        final Label speak = new Label("Click an below animal to see it speak:");
        RootPanel.get().add(speak);
                       
        ServiceLoader<Animal> loader = ServiceLoader.load(Animal.class);                
        
        Iterator<Animal> animals = loader.iterator();
        
        if(animals != null){
            while (animals.hasNext()){
                final Animal animal = animals.next();
                
                Button button = new Button(animal.getName());
        
                button.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        speak.setText(animal.speak());
                    }
                });
                RootPanel.get().add(button);                                
            }
        }
        
    }

}
