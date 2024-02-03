package com.example;

import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;
import java.io.File;
import java.util.Collections;

class AppStackTest {

    @Test
    void testAppStack() {
        if (functionPathExists()) {
            Template template = getTemplateFromStack();
            template.hasResourceProperties("AWS::Lambda::Function", Collections.singletonMap("Handler", "com.example.FunctionRequestHandler"));
        }
    }

    private boolean functionPathExists() {
        return new File(AppStack.functionPath()).exists();
    }

    private Template getTemplateFromStack() {
        AppStack stack = createAppStack();
        return Template.fromStack(stack);
    }

    private AppStack createAppStack() {
        return new AppStack(new App(), "TestMicronautAppStack");
    }
}