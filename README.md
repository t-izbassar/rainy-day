# Rainy Day

[![Build Status](https://travis-ci.org/t-izbassar/rainy-day.svg?branch=master)](https://travis-ci.org/t-izbassar/rainy-day)
[![codecov](https://codecov.io/gh/t-izbassar/rainy-day/branch/master/graph/badge.svg)](https://codecov.io/gh/t-izbassar/rainy-day)

## Overall description

It's a repository with source codes for application,
that exposes the REST-endpoints via Java EE
technologies. Those endpoints are used to calculate
volume of the holes, that will be covered by the
rain.

Imagine the rainy day and the uneven surface. If
the day will be dull enough the holes of that
surface will be covered with the water. And it's
very important to determine the amount of volume,
that water have took. That's when this application
comes into play.

## Technologies

* [Maven](https://maven.apache.org/)
* [Java EE](http://www.oracle.com/technetwork/java/javaee/overview/index.html) -
(covered specs are JAX-RS, EJB, CDI)
* [WildFly](http://www.wildfly.org/)
* [Travis CI](https://travis-ci.org/)
* [Codecov](https://codecov.io/)
* [Docker](https://www.docker.com/)
* [Qulice](https://www.qulice.com/) - the strict static analysis.
It include [PMD](https://pmd.github.io/),
[Checkstyle](http://checkstyle.sourceforge.net/),
[FindBugs](https://github.com/findbugsproject/findbugs),
some [additional checks](https://www.qulice.com/quality.html).
Also it checks compile, classpath dependencies and tries to
find duplicates.
* [Cobertura](http://cobertura.github.io/cobertura/) - for code
coverage

For testing purposes I used [JUnit4](https://junit.org/junit4/),
[Hamcrest](http://hamcrest.org/), which are enough for unit tests.
I haven't brought [Mockito](http://site.mockito.org/) as I don't
have any mocks, but that what I would choose, If I had to.

For integration tests I used [docker-maven-plugin](https://dmp.fabric8.io),
which runs the docker container with WildFly and deployed `war`
application in it. As http client I used [jcabi-http](https://http.jcabi.com)
as it provides fluent API and supports assertions on REST responses
out of the box.

## How to build and run

@todo #2 Describe the process of how to build and
 run the project as this information is very
 important to the end-user. This should be done
 somewhere near the 0.1 version release.

## Principles

I think that the main challenge when writing software
is managing the complexity of the sources. I would
like to keep complexity as low as possible and in
order to do that I'm trying to follow those principles:

@todo #2 Describe important principles in bullet list,
 that I'm trying to follow. Listing them is important
 as they will help to understand the reasoning behind
 my decisions.

## License

The code is under [MIT License](https://github.com/t-izbassar/rainy-day/blob/master/LICENSE).
Enjoy.
