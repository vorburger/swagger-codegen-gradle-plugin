package org.detoeuf

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Ignore
import org.junit.Test

import static org.junit.Assert.assertTrue

class SwaggerCodeGenTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('swagger', type: SwaggerCodeGenTask)
        assertTrue(task instanceof SwaggerCodeGenTask)
    }

    @Ignore("Wait until swagger-codegen detects file type correctly.  Version 2.1.3 cannot get yaml file from classpath which is why this test is ignored")
    @Test
    public void basicConfiguration() {
        Project project = ProjectBuilder.builder().build()
        project.set('swaggerInputSpec', 'petstore.yaml')
        project.set('swaggerApiPackage', 'com.orange.testApi')
        project.set('swaggerInvokerPackage', 'com.orange.testPackage')
        project.set('swaggerModelPackage', 'com.orange.testModel')
        project.set('swaggerLanguage', 'java')
        project.set('swaggerOutput', 'target/generated-sources/swagger')
        project.set('swaggerSrc', 'src/swagger')
        def task = project.task('swagger', type: SwaggerCodeGenTask, dependsOn: 'processTestResources')
        assertTrue(task instanceof SwaggerCodeGenTask)
        task.execute()
    }

}
