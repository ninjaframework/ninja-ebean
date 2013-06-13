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
 * Continuous integration: https://buildhive.cloudbees.com/job/reyez/job/ninja-ebean/
 

Getting started
---------------
Configuring the module for your application is quite easy. There
is a demo application that shows you how to do it.
Check out subproject ninja-ebean-demo/pom.xml for 
further information.

More about Ebean: http://www.avaje.org

NOTE:
Obviously this module lacks documentation. Feel free to
contribute to the project and the wiki. Thanks!


Setup
-----

1) Add you db conf to your application.conf file. For instance:

    ebean.datasource.databaseUrl=jdbc:h2:testdatabase:tests;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE
 
2) Add the ninja-ebeans dependency to your pom.xml:

    <dependency>
        <groupId>org.ninjaframework</groupId>
        <artifactId>ninja-ebean-module</artifactId>
        <version>1.1</version>
    </dependency>
    
3) Add ebean's enhancer plugin to your pom.xml:

    <plugin>
        <groupId>org.avaje</groupId>
        <artifactId>ebean-maven-enhancement-plugin</artifactId>
        <version>2.8.1</version>
        <executions>
            <execution>
                <id>main</id>
                <phase>process-classes</phase>
                <goals>
                    <goal>enhance</goal>
                </goals>
                <configuration>
                    <packages>models</packages>
                    <transformArgs>debug=1</transformArgs>
                    <!-- workaround against bug in ebean: https://groups.google.com/forum/?fromgroups#!topic/ebean/w2Q6PSeXKAk%5B1-25%5D -->
                    <classSource>${project.build.outputDirectory}</classSource>
                    <classDestination>${project.build.outputDirectory}</classDestination>
                </configuration>
            </execution>
        </executions>
    </plugin>

4) Install the module in your conf.Module:

    protected void configure() {

        // This installs the NinjaModule and handles the lifecycle
        install(new NinjaEbeanModule());
    }
    
    
And that's it already :)

