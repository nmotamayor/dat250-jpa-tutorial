plugins {
    application
    id("org.springframework.boot") version "3.1.2"  // Add this line for Spring Boot
    id("io.spring.dependency-management") version "1.1.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.hibernate.orm:hibernate-platform:6.6.0.Final"))
    implementation("org.hibernate.orm:hibernate-core")
    implementation("jakarta.transaction:jakarta.transaction-api")
    implementation("com.h2database:h2:2.2.220")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.postgresql:postgresql:42.7.4")

    implementation("org.springframework.boot:spring-boot-starter")  // Add Spring Boot starter
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")  // For JPA
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}


application {
    mainClass.set("no.hvl.dat250.jpa.tutorial.basicexample.Main")
}



tasks.named<Test>("test") {
    useJUnitPlatform()
}
