/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.sample.basic.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.josephalevin.gwtplug.client.ServiceLoaderLookup;
import com.josephalevin.gwtplug.sample.api.client.Animal;
import java.util.Iterator;

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
        
       
        ServiceLoaderLookup lookup = GWT.create(ServiceLoaderLookup.class);
        
        Iterator<Animal> animals = lookup.lookup(Animal.class);
        
        while (animals.hasNext()){
            Animal animal = animals.next();
            RootPanel.get().add(new Label(animal.speak()));
        }
    }

}
