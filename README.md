# Rainy Day

[![Build Status](https://travis-ci.org/t-izbassar/rainy-day.svg?branch=master)](https://travis-ci.org/t-izbassar/rainy-day)
[![codecov](https://codecov.io/gh/t-izbassar/rainy-day/branch/master/graph/badge.svg)](https://codecov.io/gh/t-izbassar/rainy-day)

## Contents

- [Overall description](#overall-description)
- [Implementation idea and complexity](#implementation-idea-and-complexity)
- [Technologies](#technologies)
- [How to build and run](#how-to-build-and-run)
- [Principles](#principles)
- [License](#license)

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

## Implementation idea and complexity

Suppose we have a surface, with hills values as follows:
```java
final List<Integer> hills = Arrays.asList(3, 2, 4, 1, 2);
```

The idea is to compare the highest values from left perspective
(meaning that we are going from start to end) to the highest
values from right perspective (going from end to start), which
in my program represented by [LeftSurface.java](src/main/java/com/github/tizbassar/rainyday/calc/LeftSurface.java)
and by [RightSurface.java](src/main/java/com/github/tizbassar/rainyday/calc/RightSurface.java).

For the given above surface they will produce character of it
as follows:
```java
// will produce 3, 3, 4, 4, 4
final List<Integer> left = new LeftSurface(hills).character();
// will produce 4, 4, 4, 2, 2
final List<Integer> right = new RightSurface(hills).character();
```

We are doing so, because we need to know the relevant high values
between hills. Having those high values now we can calculate
the volume that is covered by rain (check [ActualVolume.java](src/main/java/com/github/tizbassar/rainyday/calc/ActualVolume.java)):

```java
for (int index = 0; index < hills.size(); index += 1) {
    final int min = Math.min(left.get(index), right.get(index));
    final int hill = hills.get(index);
    if (hill < min) {
        result += min - hill;
    }
}
```

So, the complexity is `O(n)` as we are doing the linear
iteration operation over the given hills. We do it three
times and we are using additional space for left and right
character of the surface (so we need triple as much space as
the given hills).

The algorithm is exposed via REST-service, which is accepting
the POST request to `rainy-day/hills` endpoint (check [HillsEndpoint.java](src/main/java/com/github/tizbassar/rainyday/rest/HillsEndpoint.java)). 
The body of the request should be json as follows 
(check [HillsDto.java](src/main/java/com/github/tizbassar/rainyday/rest/HillsDto.java)):
```json
{
  "hills": [4, 3, 2, 1, 3]
}
```

The endpoint will return the answer in the answer body. The
good practice is to return the redirect address with the
created resource. Something like this: `GET rainy-day/hills/id`.
But in my case I decided to return it straight in the answer
body, as it is also used to return the updated resource. In
our case, we don't do any operations, that affects the state.
I decided not to use `GET` for that resource, as it is hard
to provide the `hills` array using `GET`. The usage of request
body is not recommended for `GET` requests, so I decided to
go with `POST`.

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

The application is built by providing the `rainy-day.war`,
which can be deployed on any Java EE 7 compliant server
(the WildFly is one that is tested though).
To build deployable `war`, simply use
```bash
mvn package
```

For basic verification builds (Unit tests, coverage, static-analysis):
```bash
mvn verify
```

For integration tests it's required to have `docker` installed.
The `maven-docker-plugin` will try to find relevant `docker`
installation on your machine. Thus it is required to run it
as administrator. To run full set of checks, that are performed
on CI, (with `8080` port - choose any free one) run:
```bash
sudo env "PATH=$PATH" mvn verify -P integration-test \ 
-Ddocker.local.port=8080
```

Also, there is `Dockerfile`, that you can use to start WildFly
instance. It's used only for local development and is _not_
equal to the CI one.

## Principles

I think that the main challenge when writing software
is managing the complexity of the sources. I would
like to keep complexity as low as possible and in
order to do that I'm trying to follow those principles:

* I think, that projects should have continuous integration
environment, that checks the sources for every merged change.
This is important, as this will help to caught mistakes
as fast as possible. Of course it's depends of the quality
of the build process. Also the proposed changes (via PRs)
should also be checked.
* I think, that projects should have static analysis rules from
the day 0. The stricter they are - the better, as this will
allow everyone to follow the same guidelines, which is
very valuable in long-term projects.
* I think it's better to have end-to-end functional tests, that will
allow to safely refactor implementation details, than 100% coverage
with unit tests (which is still valuable).
* I think we should limit the amount of mutable classes and
control the changes to the state. The most tricky part is to
control changes to some shared state. Managing immutable
objects is much more easier and that what we should try to use
more.
* I think we should carefully control the boundaries of the
classes and overall coupling of the components and layers.
That is the most hardest thing to achieve (from my personal
feeling). It's very easy to `inject` something you need
straight to the point where you need it instead of evaluating
the abstractions and managing coupling carefully.
* I think that application should fail as fast as possible.
The more explicit the failure is - the better, as it will
allow to catch mistake earlier and in the right place. Instead
of swallowing the exception, we should throw it. Instead
of returning `null` or `0` for invalid case it's better to
throw exception and let application to fail, as this will
lead to discovering the root cause of the problem, rather
than having to do workarounds and wondering on how we
received `null` in that particular place.

Of course, there is no ideal software and no ideal projects.
Sometimes we need to make certain `dirty` decisions in order
to get things done. Sometimes we need to introduce workaround,
instead of strategic solution to the problem. But those decisions
should be made with the consequences in mind, as they will
follow us, until we really fix the issue.

## License

The code is under [MIT License](https://github.com/t-izbassar/rainy-day/blob/master/LICENSE).
Enjoy.
