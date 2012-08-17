package ninja.ebean;

import com.avaje.ebean.EbeanServer;
import com.google.inject.AbstractModule;

/**
 * Use this module if you want to enable Ebeans support in your application.
 * 
 * In conf.Module you can simply call
 * <code>
 *    install(new NinjaEbeanModule());
 * </code>
 *
 */
public class NinjaEbeanModule extends AbstractModule {

    @Override
    protected void configure() {
        
        // binding is important as the NinjaEbeanServer handles the lifecycle
        bind(NinjaEbeanServerLifecycle.class);
        
        //this is the provider geneating a nicely configured EbeanServer
        bind(EbeanServer.class).toProvider(NinjaEbeanServerProvider.class);
        
    }

}
