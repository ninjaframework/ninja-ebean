package ninja.ebean;

import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_DATABASE_DRIVER;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_DATABASE_URL;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_HEARTBEAT_SQL;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_MAX_CONNECTIONS;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_MIN_CONNECTIONS;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_NAME;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_PASSWORD;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DATASOURCE_USERNAME;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DDL_GENERATE;
import static ninja.ebean.NinjaEbeanProperties.EBEAN_DDL_RUN;
import ninja.lifecycle.Dispose;
import ninja.lifecycle.Start;
import ninja.utils.NinjaProperties;

import org.slf4j.Logger;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebeaninternal.server.lib.ShutdownManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * This is an internal class of Ninja Ebeans support. It is responsible for
 * creating db connections and shutting them down upon server start / stop.
 * <p>
 * As end-user you should NOT use this method directly. Instead simply inject
 * EbeanServer into the class you want to use. The interface EbeanServer is
 * configured by this class and provided by a provider.
 * <p>
 */
@Singleton
public class NinjaEbeanServerLifecycle {

    private EbeanServer ebeanServer;

    private final NinjaProperties ninjaProperties;

    private final Logger logger;

    @Inject
    public NinjaEbeanServerLifecycle(Logger logger,
                                     NinjaProperties ninjaProperties) {

        this.logger = logger;
        this.ninjaProperties = ninjaProperties;

    }

    /**
     * This method reads the configuration properties from
     * your application.conf file and configures Ebean accordingly.
     * 
     */
    @Start
    public void startServer(){
        logger.info("Starting Ebeans Module.");

        // /////////////////////////////////////////////////////////////////////
        // Setup basic parameters
        // /////////////////////////////////////////////////////////////////////
        boolean ebeanDdlGenerate = ninjaProperties.getBooleanWithDefault(
                EBEAN_DDL_GENERATE, true);
        boolean ebeanDdlRun = ninjaProperties.getBooleanWithDefault(
                EBEAN_DDL_RUN, true);

        String ebeanDatasourceName = ninjaProperties.getWithDefault(
                EBEAN_DATASOURCE_NAME, "default");
        String ebeanDatasourceUserName = ninjaProperties.getWithDefault(
                EBEAN_DATASOURCE_USERNAME, "test");
        String ebeanDatasourcePassword = ninjaProperties.getWithDefault(
                EBEAN_DATASOURCE_PASSWORD, "test");

        String ebeanDatasourceDatabaseUrl = ninjaProperties.getWithDefault(
                EBEAN_DATASOURCE_DATABASE_URL,
                "jdbc:h2:mem:tests;DB_CLOSE_DELAY=-1");

        String ebeanDatasourceDatabaseDriver = ninjaProperties.getWithDefault(
                EBEAN_DATASOURCE_DATABASE_DRIVER, "org.h2.Driver");
        int ebeanDatasourceMinConnections = ninjaProperties
                .getIntegerWithDefault(EBEAN_DATASOURCE_MIN_CONNECTIONS, 1);
        int ebeanDatasourceMaxConnections = ninjaProperties
                .getIntegerWithDefault(EBEAN_DATASOURCE_MAX_CONNECTIONS, 25);

        String ebeanDatasourceHeartbeatSql = ninjaProperties.getWithDefault(
                EBEAN_DATASOURCE_HEARTBEAT_SQL, "select 1");

        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setName(ebeanDatasourceName);

        // Define DataSource parameters
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriver(ebeanDatasourceDatabaseDriver);
        dataSourceConfig.setUsername(ebeanDatasourceUserName);
        dataSourceConfig.setPassword(ebeanDatasourcePassword);
        dataSourceConfig.setUrl(ebeanDatasourceDatabaseUrl);
        dataSourceConfig.setMinConnections(ebeanDatasourceMinConnections);
        dataSourceConfig.setMaxConnections(ebeanDatasourceMaxConnections);
        dataSourceConfig.setHeartbeatSql(ebeanDatasourceHeartbeatSql);

        serverConfig.setDataSourceConfig(dataSourceConfig);

        // set DDL options...
        serverConfig.setDdlGenerate(ebeanDdlGenerate);
        serverConfig.setDdlRun(ebeanDdlRun);

        serverConfig.setDefaultServer(true);
        serverConfig.setRegister(true);

        
        serverConfig.addPackage("models");

        // create the EbeanServer instance
        ebeanServer = EbeanServerFactory.create(serverConfig);

    }

    /**
     * This method shuts down Ebean and all connections in a nice way.
     */
    @Dispose
    public void stopServer() {
        logger.info("Stoppping Ebeans module.");
        ShutdownManager.shutdown();
    }

    public EbeanServer getEbeanServer() {
        return ebeanServer;
    }

}
