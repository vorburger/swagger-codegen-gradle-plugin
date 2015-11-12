package com.detoeuf

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertTrue

class SwaggerCodeGenTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('swaggerCodeGen', type: SwaggerCodeGenTask)
        assertTrue(task instanceof SwaggerCodeGenTask)
    }

    @Test
    public void basicConfiguration() {
        Project project = ProjectBuilder.builder().build()
        project.set("swaggerInputSpec", 'src/test/resources/petstore.yaml')
        project.set("swaggerLanguage", "java")
        def task = project.task('swaggerCodeGen', type: SwaggerCodeGenTask)
        assertTrue(task instanceof SwaggerCodeGenTask)
        task.execute()
    }

}
