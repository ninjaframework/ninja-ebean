3.0.2
=====
 
 * 2022-01-21 Upgrade to NinjaFramework 6.8.1 and Ebean 12.13.1

3.0.1
=====

 * 2020-06-11 Use native ebean package finder vs. guava (works better on Java 11) (jjlauer)

3.0.0
=====

 * 2020-04-29 Bump to Ebean 12.1.1+ (jjlauer)
 * 2020-04-29 Bump to Ninja 6.0.0+ (jjlauer)
 * 2020-04-29 Requires Java 8+ (jjlauer)

2.0.0
=====

 * 2016-01-08 Expose DDL Seed and Init SQL properties (drouillard)
 * 2016-01-08 Bump to ebean 6.16.4 (drouillard)

1.5.1
=====

 * 2015-08-27 Ebean DDL properties default to false to avoid accidental database recreation (metacity)
 * 2015-08-27 Call loadFromProperties() explicitly to avoid NPE in Ebean 6.4.1 (metacity)

1.5.0
=====

 * 2015-03-17 Bump to Ninja 4.0.5 in tests (ra) 
 * 2015-03-12 H2 DB now in provided scope so downstream projects do not need to manually
   exclude it (if they are using another vendor)
 * 2015-03-12 javax.persistence now in provided scope so downstream projects do not need to manually
   exclude it (if they are using another vendor)
 * 2015-03-12 ebean.models accepts packages -- simply add a ".*" at the end of the package
   name to differentiate it from a single classname.
 * 2015-03-12 Factory method to create ebean server in NinjaEbeanServerLifecycle
   to allow subclasses to override config.

1.4.0
=====

 * 2014-02-17 Bump to Ninja 3.0.0 and ebean 3.2.5 (ra)

1.2.1
=====

 * 2013-07-17 Version bump to Ninja 1.5 (ra)
 * 2013-07-17 Fixed Java Version to 1.7 (ra)
 * 2013-07-17 Better shutdown of Ebean (especially in tests) (ra)
 
 
1.2
===

 * Bump to Ninja 1.4.4

1.1
===

 * Updated to Ninja version 1.3
 * Fixed a small bug when property "ebean.models" was not set
