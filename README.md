# springboot-prometheus-micrometer-servicemonitor
This project contains Springboot application that can be integrated with Prometheus Operator on Kubernetes and grafana using Service Monitor

Project Overview

The project aims to create a Spring Boot application that exposes metrics in a way that Prometheus can easily scrape them, using Kubernetes Service Monitors to automate the discovery of these metrics.


Key Components

README.md: Provides a basic description of the project.

Dockerfile: Defines how to build a Docker image for the Spring Boot application.

mvnw and mvnw.cmd: Maven Wrapper scripts for Unix and Windows, respectively. These allow building the project without requiring Maven to be installed globally.

pom.xml:  Maven configuration file. It defines project dependencies (like Spring Boot Actuator, Micrometer Prometheus) and build configurations.

src/main/java/com/siddhu/: Contains the main application code.

SiddhuController.java:  A simple Spring MVC controller with two endpoints:

/: Increments a counter (userCounter) every time the default page is accessed.
/uservisit: Returns the current value of the userCounter.
It uses Micrometer's Counter to track user visits. The Counter is registered with the MeterRegistry, which is how Micrometer makes the metric available.
SpringbootPrometheusMicrometerServicemonitorApplication.java: The main Spring Boot application class.

src/main/resources/application.properties:  Configuration file:

spring.application.name: Sets the application name.
management.endpoints.web.exposure.include: health,metrics,prometheus: Crucially, this line tells Spring Boot Actuator to expose the /health, /metrics, and /prometheus endpoints over HTTP. The /prometheus endpoint is what Prometheus will scrape to get the metrics.
management.metrics.tags.application: Adds a tag to all metrics with the application's name.
src/test/java/com/siddhu/: Contains a basic Spring Boot test.

yamlfiles/:  Kubernetes configuration files.

My\_SpringBoot\_Docker\_K8.yaml: Defines a Kubernetes Deployment and Service for the Spring Boot application. 
The Deployment ensures that one instance of the application runs as a pod.
The Service exposes the application on port 8080 within the Kubernetes cluster.
My\_SpringBoot\_Docker\_K8\_ServiceMonitor.yaml: This is the key file for Prometheus integration. It defines a ServiceMonitor, which tells the Prometheus Operator how to discover and scrape metrics from the Spring Boot application's service. 
It selects the service by label (app: springboot-prometheus-micrometer-servicemonitor).
It specifies that Prometheus should scrape the /actuator/prometheus path on the service's http-traffic port.
The other YAML files define MongoDB deployment and service, which are not directly related to the core functionality of Prometheus and Micrometer.
.mvn/wrapper/: Contains the Maven Wrapper files, allowing the project to be built without a global Maven installation.

maven-wrapper.properties: Configuration for the Maven Wrapper.
MavenWrapperDownloader.java: Java program that downloads the Maven Wrapper JAR if it's not present.
How it Works Together

Spring Boot Application:

Exposes metrics at the /actuator/prometheus endpoint using Spring Boot Actuator and Micrometer.
Defines a simple counter metric to track user visits.
Kubernetes Deployment and Service:

The Deployment runs the application in a Kubernetes pod.
The Service makes the application accessible within the cluster.
Prometheus and ServiceMonitor:

The ServiceMonitor tells the Prometheus Operator how to find and scrape metrics from the application's Service.
Prometheus then collects these metrics, which can be visualized in Grafana.
In essence, this project sets up a basic Spring Boot application and the necessary Kubernetes configurations to monitor it with Prometheus, using the Prometheus Operator and Service Monitors to simplify the setup.


