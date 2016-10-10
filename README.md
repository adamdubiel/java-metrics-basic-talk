# Java Metrics Basic Talk

Materials for "What is your app doint when you are not around?" talk.

## Tags

* `0-no-metrics`
* `1-simple-metrics`
* `2-background-metrics`
* `3-metrics-spring`
* `4-metered-queue`
* `5-jvm-metrics`

## Metrics

* [Dropwizard metrics](http://metrics.dropwizard.io/3.1.0/)
* [Metrics HDR histogram](https://bitbucket.org/marshallpierce/hdrhistogram-metrics-reservoir)

## Graphite and Grafana

* [Graphite](http://graphite.readthedocs.io/en/latest/)
* [Grafana](http://grafana.org/)
* [Scaling Graphite blogpost](http://allegro.tech/2015/09/scaling-graphite.html)

`docker-compose.yml` is located in`dashboards` directory.

Dashboards can be imported into Grafana.

## WRK - Load generator

During presentation [wrk2](https://github.com/giltene/wrk2) was used, but `wrk` it will work as well.
