swagger-codegen-maven-plugin
============================

[![Build Status](https://travis-ci.org/thebignet/swagger-codegen-gradle-plugin.svg?branch=master)](https://travis-ci.org/thebignet/swagger-codegen-gradle-plugin)

A Gradle plugin to support the [swagger](http://swagger.io) code generation project

Usage
============================

Add to your `build->plugins` section (default phase is `generate-sources` phase)
```groovy
ext {
    swaggerInputSpec = 'src/main/resources/petstore.yaml'
    swaggerOutput = 'src/swagger'
    swaggerLanguage = 'java'
}

apply plugin: 'swaggerCodeGen'

sourceSets {
    swagger {
        java.srcDir file('src/swagger/java')
    }
}
```

Followed by:

```
gradle swagger
```

### Configuration parameters

- `inputSpec` - swagger spec file path
- `language` - target generation language
- `templateDirectory` - directory with mustache templates

- TODO output directory

-- 
[maven-url]: https://search.maven.org/#search%7Cga%7C1%7Cswagger-codegen-maven-plugin
[maven-img]: https://img.shields.io/maven-central/v/com.garethevans.plugin/swagger-codegen-maven-plugin
