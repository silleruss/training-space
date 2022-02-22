import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.silleruss"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux:2.6.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0-native-mt")
	implementation ("io.arrow-kt:arrow-fx-coroutines:1.0.1")
	developmentOnly("org.springframework.boot:spring-boot-devtools:2.6.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.3")
	testImplementation("io.projectreactor:reactor-test:3.4.14")
	testImplementation("com.h2database:h2:2.1.210")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
