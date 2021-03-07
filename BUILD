# Dagger setup
java_plugin(
    name = "dagger_plugin",
    processor_class = "dagger.internal.codegen.ComponentProcessor",
    deps = [
        "@maven//:com_google_dagger_dagger_compiler",
    ]
)

java_library(
    name = "dagger_lib",
    exported_plugins = ["dagger_plugin"],
    exports = [
        "@maven//:com_google_dagger_dagger",
        "@maven//:javax_inject_javax_inject",
    ],
)

# Build Handlers
java_binary(
    name = "GetItemSetLambda",
    srcs = glob(["src/main/java/**/*.java"]),
    main_class = "com.hmellema.league.handlers.itemset.GetItemSetLambda",
    deps = [
        ":dagger_lib",
        "@api_model//:smithy-model-java-slim",
        "@lambda_tools//:api_gateway_handler",
        "@maven//:com_google_guava_guava",
    ],
    classpath_resources = [
        "@lambda_tools//:logging_config"
    ],
    runtime_deps = [
        "@lambda_tools//:runtime_logging_deps"
    ]
)

java_binary(
    name = "GetRunePageLambda",
    srcs = glob(["src/main/java/**/*.java"]),
    main_class = "com.hmellema.league.handlers.runepage.GetRunePageLambda",
    deps = [
        ":dagger_lib",
        "@api_model//:smithy-model-java-slim",
        "@lambda_tools//:api_gateway_handler",
        "@maven//:com_google_guava_guava",
    ],
    resources = [
        "@lambda_tools//:logging_config"
    ],
    runtime_deps = [
        "@lambda_tools//:runtime_logging_deps",
    ]
)

filegroup(
    name = "jinjafier",
    srcs = ["configuration/jinjafier.py"],
    visibility = ["//visibility:public"]
)

filegroup(
    name = "template-file",
    srcs = ["configuration/templates/template.base.yml"],
    visibility = ["//visibility:public"]
)

py_library(
    name = "jinjafier_lib",
    srcs = [":jinjafier"],
    deps = []
)

genrule(
    name="build_api_template",
    srcs=[
        ":GetItemSetLambda_deploy.jar", 
        ":GetRunePageLambda_deploy.jar"
    ],
    tools = [":jinjafier_lib", ":template-file", "@api_model//:smithy_gen-openapi"],
    cmd="""
    pwd && \
    python3 $(location jinjafier_lib) \
        --template-file $(location template-file) \
        --openapi-spec $(location @api_model//:smithy_gen-openapi) \
        --output-file $@
    """,
    outs=["template.yml"],
    visibility = ["//visibility:public"]
)

