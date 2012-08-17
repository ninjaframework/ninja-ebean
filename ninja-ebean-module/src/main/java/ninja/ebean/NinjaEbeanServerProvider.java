package ninja.ebean;


import com.avaje.ebean.EbeanServer;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * A simple provider that provides a singleton EbeansServer instance.
 *
 */
@Singleton
public class NinjaEbeanServerProvider implements Provider<EbeanServer> {

    private NinjaEbeanServerLifecycle ninjaEbeanServer;

    @Inject
    public NinjaEbeanServerProvider(NinjaEbeanServerLifecycle ninjaEbeanServer) {

        this.ninjaEbeanServer = ninjaEbeanServer;
        
    }

    @Override
    public EbeanServer get() {
        return ninjaEbeanServer.getEbeanServer();
    }

}
