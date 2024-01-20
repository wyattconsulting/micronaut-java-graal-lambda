package com.example;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import io.micronaut.aws.cdk.function.MicronautFunction;
import io.micronaut.starter.application.ApplicationType;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.lambda.Architecture;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionUrl;
import software.amazon.awscdk.services.lambda.FunctionUrlAuthType;
import software.amazon.awscdk.services.lambda.FunctionUrlOptions;
import software.amazon.awscdk.services.lambda.Tracing;
import software.amazon.awscdk.services.logs.RetentionDays;
import software.constructs.Construct;

public class AppStack extends Stack {

    private final AppStack appStack = this;

    public AppStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public AppStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        Map<String, String> environmentVariables = new HashMap<>();

        environmentVariables.put("AWS_CODEGURU_PROFILER_GROUP_ARN", "arn:aws:codeguru-profiler:us-east-2:468673670728:profilingGroup/micronaut-graal");
        environmentVariables.put("AWS_CODEGURU_PROFILER_ENABLED", "TRUE");
        
        Function function = MicronautFunction.create(ApplicationType.FUNCTION,
                true,
                appStack,
                "micronaut-function")
                .handler("com.example.FunctionRequestHandler")
                .environment(environmentVariables)
                .code(Code.fromAsset(functionPath()))
                .timeout(Duration.seconds(1))
                .memorySize(128)
                .logRetention(RetentionDays.ONE_WEEK)
                .tracing(Tracing.ACTIVE)
                .architecture(Architecture.X86_64)
                .build();
                
        FunctionUrl functionUrl = function.addFunctionUrl(FunctionUrlOptions.builder()
                .authType(FunctionUrlAuthType.NONE)
                .build());

        CfnOutput.Builder.create(appStack, "MnTestApiUrl")
                .exportName("MnTestApiUrl")
                .value(functionUrl.getUrl())
                .build();
    }

    public static String functionPath() {

        File dir = new File("../app/build/libs/");
        var files = dir.listFiles((File file, String name) -> name.endsWith("zip"));
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));

        return Arrays.stream(files).findFirst().get().toString();
    }
}
