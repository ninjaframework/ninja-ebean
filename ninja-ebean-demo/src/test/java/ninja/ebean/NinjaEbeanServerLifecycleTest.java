/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.ebean;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import ninja.utils.NinjaProperties;

import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

public class NinjaEbeanServerLifecycleTest {

    @Mock
    private Logger logger;

    /**
     * This tests does three things 
     * 1) Test that stuff in {@link NinjaEbeanProperties} is correct and without typos 
     * 2) Test that default properties are not altered accidentally. 
     * 3) All properties get called assuming they are ready to use inside the lifecycle.
     */
    @Test
    public void makeSureCorrectPropertiesGetParsed() {

        try {
            // /////////////////////////////////////////////////////////////////////
            // Setup and spy on the properties
            // /////////////////////////////////////////////////////////////////////
            NinjaProperties ninjaProperties = spy(NinjaProperties.class);

            NinjaEbeanDatabaseLifecycle ninjaEbeanDatabaseLifecycle = new NinjaEbeanDatabaseLifecycle(
                    logger, ninjaProperties);

            // /////////////////////////////////////////////////////////////////////
            // Execute the server startup
            // /////////////////////////////////////////////////////////////////////
            //ninjaEbeanServerLifecycle.startServer();

            // /////////////////////////////////////////////////////////////////////
            // Verify that properties are correct
            // /////////////////////////////////////////////////////////////////////
            verify(ninjaProperties).getBooleanWithDefault("ebean.ddl.generate",
                    false);
            verify(ninjaProperties)
                    .getBooleanWithDefault("ebean.ddl.run", false);

            verify(ninjaProperties).getWithDefault("ebean.datasource.name",
                    "default");
            verify(ninjaProperties).getWithDefault("ebean.datasource.username",
                    "test");
            verify(ninjaProperties).getWithDefault("ebean.datasource.password",
                    "test");

            verify(ninjaProperties).getWithDefault(
                    "ebean.datasource.databaseUrl",
                    "jdbc:h2:mem:tests;DB_CLOSE_DELAY=-1");

            verify(ninjaProperties).getWithDefault(
                    "ebean.datasource.databaseDriver", "org.h2.Driver");
            verify(ninjaProperties).getIntegerWithDefault(
                    "ebean.datasource.minConnections", 1);
            verify(ninjaProperties).getIntegerWithDefault(
                    "ebean.datasource.maxConnections", 25);

            verify(ninjaProperties).getWithDefault(
                    "ebean.datasource.heartbeatsql", "select 1");
            
            verify(ninjaProperties).getStringArray(
                    "ebean.models");
            
            verify(ninjaProperties).get("ebean.ddl.initSql");
            
            verify(ninjaProperties).get("ebean.ddl.seedSql");
            // be nice and stop the server afterwards
            //ninjaEbeanServerLifecycle.stopServer();
        } catch (Exception e) {
            // we are getting:
            //java.sql.SQLException: Trying to access the Connection Pool when it is shutting down
            //this exception is expected and ignored
            //and happens if startup and shutdown is too fast (what this test is).
        }
    }

}
