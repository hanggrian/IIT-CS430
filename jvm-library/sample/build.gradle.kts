plugins {
    application
    java
}

application.mainClass.set("com.example.MyApp")

dependencies.implementation(project(":library"))
