/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josephalevin.gwtplug.sample.impl.server;

import com.josephalevin.gwtplug.sample.api.client.Animal;

/**
 * In the server package.  Should be ignored by the gwt compiler.
 * @author Joseph A. Levin <josephalevin@gmail.com>
 */
public class Pig implements Animal{

    @Override
    public String speak() {
        return "oink";
    }
    
}
