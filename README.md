     _______  .___ _______        ____.  _____   
     \      \ |   |\      \      |    | /  _  \  
     /   |   \|   |/   |   \     |    |/  /_\  \
    /    |    \   /    |    \/\__|    /    |    \
    \____|__  /___\____|__  /\________\____|__  /
         web\/framework   \/                  \/



Ebean support for Ninja
=======================
Ebean is a simple and powerful ORM tool.
This plugin allows to use Ebean in any Ninja
application.

More
----

 * Source: http://github.com/ninjaframework/ninja
 * Continuous integration: [![Build Status](https://api.travis-ci.org/ninjaframework/ninja-ebean.svg)](https://travis-ci.org/ninjaframework/ninja-ebean)


Getting started
---------------
Configuring the module for your application is quite easy. There
is a demo application that shows you how to do it.
Check out subproject ninja-ebean-demo/pom.xml for
further information.

More about Ebean: https://ebean.io/

Overview
--------

This module works with the Ebean version 12.1.1+.

If you need compatibility with earlier versions of Ebean please see [v2.0.0](https://github.com/ninjaframework/ninja-ebean/releases/tag/ninja-ebean-2.0.0) which supports a minimum Ebean version v3.2.5, and has also been tested with the later versions
in the 6.x-8.x series.

If you need compatibility with earlier versions of Ebean please see [v1.5.1](https://github.com/ninjaframework/ninja-ebean/releases/tag/ninja-ebean-1.5.1) which supports a minimum Ebean version v3.2.5, and has also been tested with the later versions
in the 4.x series.

NOTE: This module only supports a single (default) Ebean server.  Feel free to
contribute code to the project if you need other features. Thanks!

Setup
-----

1) Add your db conf to your application.conf file. For a simple H2 database:

    ebean.datasource.databaseUrl=jdbc:h2:testdatabase:tests;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE

For a MySQL database (you'll also need to add the MySQL driver dependency to
your project)

    ebean.ddl.generate = false
    ebean.ddl.run = false
    ebean.models = com.company.models.*,org.otherorg.models.Foo
    ebean.datasource.name = NameOfEbeanServer
    ebean.datasource.databaseUrl = jdbc:mysql://localhost:3306/dbname
    ebean.datasource.databaseDriver = com.mysql.jdbc.Driver
    ebean.datasource.username = root
    ebean.datasource.password = test


Please note that <code>ebean.models</code> accepts a comma delimited list of
both class names as well as packages (just make sure it ends with .*)

2) Add the ninja-ebeans dependency to your pom.xml:

    <dependency>
        <groupId>org.ninjaframework</groupId>
        <artifactId>ninja-ebean-module</artifactId>
        <version>3.0.0</version>
    </dependency>

3) Add ebean's enhancer plugin to your pom.xml:

    <plugin>
        <groupId>io.ebean</groupId>
        <artifactId>ebean-maven-plugin</artifactId>
        <version>12.1.1</version>
        <executions>
            <execution>
                <id>ebean-enhancer</id>
                <phase>process-classes</phase>
                <configuration>
                    <classSource>${project.build.outputDirectory}</classSource>
                    <packages>models</packages>
                    <transformArgs>debug=1</transformArgs>
                </configuration>
                <goals>
                    <goal>enhance</goal>
                </goals>
            </execution>
        </executions>
    </plugin>

4) Install the module in your conf.Module:

    protected void configure() {

        // This installs the NinjaModule and handles the lifecycle
        install(new NinjaEbeanModule());
    }


And that's it already :)
