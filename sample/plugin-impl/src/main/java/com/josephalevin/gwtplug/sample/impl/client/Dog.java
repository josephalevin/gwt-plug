/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.sample.impl.client;

import com.josephalevin.gwtplug.sample.api.client.Animal;

/**
 *
 * @author josephalevin
 */
public class Dog implements Animal{   

    @Override
    public String speak() {
        return "woof";
    }

}
