plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	id("io.freefair.lombok") version "8.10"
}

group = "cz.tomkre.sandbox"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2023.0.3"))
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}

tasks.register<Exec>("buildDockerImage") {
	group = "docker"
	dependsOn("build")
	commandLine("sh", "-c", """
		docker image build -t tomkre/sbs-app:1.0 .
	""".trimIndent())
}

tasks.bootBuildImage {
	imageName = "tomkre/sbs-app:1.0"
	docker {
		publishRegistry {
			username.set(findProperty("registryUsername").toString())
			password.set(findProperty("registryPassword").toString())
			url.set(findProperty("registryUrl").toString())
		}
	}
}