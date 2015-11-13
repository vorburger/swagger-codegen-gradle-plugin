package com.detoeuf

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertTrue

class SwaggerCodeGenPluginTest {
    @Test
    public void swaggerCodeGenPluginAddsSwaggerCodeGenTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'swaggerCodeGen'

        assertTrue(project.tasks.swagger instanceof SwaggerCodeGenTask)
    }
}
