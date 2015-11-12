package com.detoeuf

import org.gradle.api.Plugin
import org.gradle.api.Project

class SwaggerCodeGenPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('swagger', type: SwaggerCodeGenTask)
    }
}
