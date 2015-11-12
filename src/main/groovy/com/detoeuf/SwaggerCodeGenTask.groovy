package com.detoeuf
import io.swagger.codegen.ClientOptInput
import io.swagger.codegen.ClientOpts
import io.swagger.codegen.CodegenConfig
import io.swagger.codegen.DefaultGenerator
import io.swagger.models.Swagger
import io.swagger.parser.SwaggerParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SwaggerCodeGenTask extends DefaultTask {

    @TaskAction
    def swaggerCodeGen() {
        Swagger swagger = new SwaggerParser().read(project.file(project.swaggerInputSpec).absolutePath)
        CodegenConfig config = forName(project.swaggerLanguage)
        config.setOutputDir(project.file('target/generated-sources/swagger').absolutePath)

        if (project.has('swaggerTemplateDirectory')) {
            config.additionalProperties().put("templateDir", project.file(project.swaggerTemplateDirectory).absolutePath)
        }

        ClientOptInput input = new ClientOptInput().opts(new ClientOpts()).swagger(swagger)
        input.setConfig(config)
        project.delete('src/swagger')
        new DefaultGenerator().opts(input).generate()
        project.copy {
            from 'target/generated-sources/swagger/src/main/java'
            into 'src/swagger/java'
        }
    }

    private CodegenConfig forName(String name) {
        ServiceLoader<CodegenConfig> loader = ServiceLoader.load(CodegenConfig.class)
        for (CodegenConfig config : loader) {
            if (config.getName().equals(name)) {
                return config
            }
        }

        // else try to load directly
        try {
            return (CodegenConfig) Class.forName(name).newInstance()
        } catch (Exception e) {
            throw new RuntimeException("Can't load config class with name ".concat(name), e)
        }
    }

}
