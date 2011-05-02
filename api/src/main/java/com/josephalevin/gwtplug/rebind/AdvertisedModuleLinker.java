/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josephalevin.gwtplug.rebind;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.Artifact;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.Shardable;

/**
 *
 * @author josephalevin
 */
@LinkerOrder(LinkerOrder.Order.PRE)
@Shardable
public class AdvertisedModuleLinker extends AbstractLinker{

    @Override
    public String getDescription() {
        return "Advertised Modules";
    }

    @Override
    public ArtifactSet link(TreeLogger logger, LinkerContext context, ArtifactSet artifacts) throws UnableToCompleteException {
        logger.log(TreeLogger.Type.INFO, "A ***************************");
        return super.link(logger, context, artifacts);
    }

    @Override
    public ArtifactSet link(TreeLogger logger, LinkerContext context, ArtifactSet artifacts, boolean onePermutation) throws UnableToCompleteException {        
        logger.log(TreeLogger.Type.INFO, "B ***************************");
        logger.log(TreeLogger.Type.INFO, context.getModuleName());
        logger.log(TreeLogger.Type.INFO, "" + artifacts.size());
        
//        for(Artifact a : artifacts){
//            logger.log(TreeLogger.Type.INFO, a.toString());
//        }
        return super.link(logger, context, artifacts, onePermutation);
    }

    @Override
    public ArtifactSet relink(TreeLogger logger, LinkerContext context, ArtifactSet newArtifacts) throws UnableToCompleteException {
        logger.log(TreeLogger.Type.INFO, "C ***************************");
        return super.relink(logger, context, newArtifacts);
    }
    
    
    
    

}
