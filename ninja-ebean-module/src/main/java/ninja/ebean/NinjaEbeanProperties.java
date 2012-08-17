package ninja.ebean;

/**
 * All currently supported Ebean properties in file
 * application.conf.
 * 
 * Add them to your application.conf file to alter them.
 * 
 */
public interface NinjaEbeanProperties {

    public final String EBEAN_DDL_GENERATE = "ebean.ddl.generate";
    public final String EBEAN_DDL_RUN = "ebean.ddl.run";

    public final String EBEAN_DATASOURCE_USERNAME = "ebean.datasource.username";
    public final String EBEAN_DATASOURCE_PASSWORD = "ebean.datasource.password";
    
    public final String EBEAN_DATASOURCE_NAME = "ebean.datasource.name";
    public final String EBEAN_DATASOURCE_DATABASE_URL = "ebean.datasource.databaseUrl";
    public final String EBEAN_DATASOURCE_DATABASE_DRIVER = "ebean.datasource.databaseDriver";
    public final String EBEAN_DATASOURCE_MIN_CONNECTIONS = "ebean.datasource.minConnections";
    public final String EBEAN_DATASOURCE_MAX_CONNECTIONS = "ebean.datasource.maxConnections";
    public final String EBEAN_DATASOURCE_HEARTBEAT_SQL = "ebean.datasource.heartbeatsql";
    public final String EBEAN_DATASOURCE_ISOLATION_LEVEL = "ebean.datasource.isolationlevel";

}
