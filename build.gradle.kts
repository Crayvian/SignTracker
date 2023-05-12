plugins {
    id("java")
}

group = "de.rubingrube"
version = "3.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7");
    implementation("com.moandjiezana.toml:toml4j:0.7.2");
    compileOnly("org.bstats:bstats-bukkit:2.2.1");
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.test {
    useJUnitPlatform()
}