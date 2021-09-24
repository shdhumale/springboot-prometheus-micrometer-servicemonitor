From openjdk:11
copy ./target/springboot-prometheus-micrometer-servicemonitor-0.0.1-SNAPSHOT.jar springboot-prometheus-micrometer-servicemonitor-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","springboot-prometheus-micrometer-servicemonitor-0.0.1-SNAPSHOT.jar"]