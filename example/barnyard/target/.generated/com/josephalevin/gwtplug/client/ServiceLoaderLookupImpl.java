package com.josephalevin.gwtplug.client;
import java.util.Iterator;
public class ServiceLoaderLookupImpl implements com.josephalevin.gwtplug.client.ServiceLoaderLookup {
public <S> Iterator<S> lookup(Class<S> service){
if (service.getName().equals("com.josephalevin.gwtplug.example.barnyard.api.client.Animal")){
return (Iterator<S>) new com.josephalevin.gwtplug.client.ServiceLoaderIterator<com.josephalevin.gwtplug.example.barnyard.api.client.Animal>(3){
public com.josephalevin.gwtplug.example.barnyard.api.client.Animal get (int index){switch(index){
case 0: return new com.josephalevin.gwtplug.example.barnyard.dog.client.Dog();
case 1: return new com.josephalevin.gwtplug.example.barnyard.pig.client.Pig();
case 2: return new com.josephalevin.gwtplug.example.barnyard.cow.client.Cow();
default:throw new ArrayIndexOutOfBoundsException(index);
}
}
};
}
return null;
}
}
