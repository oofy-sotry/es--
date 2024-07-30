plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Elasticsearch API Client - 최신 버전으로 업데이트
    implementation("co.elastic.clients:elasticsearch-java:8.10.0")

    // Jackson Databind - 버전을 최신으로 수정
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")

    // Jakarta JSON API
    implementation("jakarta.json:jakarta.json-api:2.0.1")

    // JSON 라이브러리
    implementation("org.json:json:20210307")

    // Spring Data Elasticsearch - Spring Boot 버전과 호환되는 버전으로 수정
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    
    implementation ("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.17.9")
    implementation ("org.apache.httpcomponents:httpclient:4.5.13")
    implementation ("co.elastic.clients:elasticsearch-java:8.10.2")
    implementation ("co.elastic.clients:elasticsearch-java:8.11.1")
    
}

tasks.withType<Test> {
    useJUnitPlatform()
}

