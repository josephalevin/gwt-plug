/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.sample.basic.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.josephalevin.gwtplug.sample.api.client.Animal;
import java.util.Iterator;
import java.util.ServiceLoader;


/**
 *
 * @author josephalevin
 */
public class MainEntryPoint implements EntryPoint{

    @Override
    public void onModuleLoad() {
        final Label label = new Label("Hello, GWT!!!");
        final Button button = new Button("Click me!");
        
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                label.setVisible(!label.isVisible());
            }
        });

        RootPanel.get().add(button);
        RootPanel.get().add(label);
                       
        ServiceLoader<Animal> loader = ServiceLoader.load(Animal.class);                
        
        Iterator<Animal> animals = loader.iterator();
        
        if(animals != null){
            while (animals.hasNext()){
                Animal animal = animals.next();
                RootPanel.get().add(new Label(animal.speak()));
            }
        }
    }

}
