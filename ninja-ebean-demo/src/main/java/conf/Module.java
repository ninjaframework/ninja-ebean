package conf;

import ninja.ebean.NinjaEbeanModule;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    protected void configure() {

        // This installs the NinjaModule and handles the lifecycle
        install(new NinjaEbeanModule());
    }

}
