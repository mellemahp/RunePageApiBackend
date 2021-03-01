# import git repositories
load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

git_repository(
    name="api_model", 
    remote="https://github.com/mellemahp/RunePageApiModel",
    branch="main"
)

# import libraries from maven
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "2.8"
RULES_JVM_EXTERNAL_SHA = "79c9850690d7614ecdb72d68394f994fef7534b292c4867ce5e7dec0aa7bdfad"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        # common dependencies
        "biz.paluch.redis:lettuce:4.5.0.Final",
        "org.apache.commons:commons-lang3:3.11",
        "com.google.dagger:dagger:2.32",
        "com.google.dagger:dagger-compiler:2.32",
        "javax.inject:javax.inject:1",
        "com.google.code.gson:gson:2.8.6",
        "com.google.guava:guava:30.1-jre",
        # aws lambda dependencies
        "com.amazonaws:aws-lambda-java-core:1.2.1",
        "com.amazonaws:aws-lambda-java-events:3.7.0",
        "com.amazonaws:aws-lambda-java-log4j2:1.2.0",
        # test deps
        "org.mockito:mockito-all:1.10.19",
        "junit:junit:4.12",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

# python rules for jinja
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
http_archive(
    name = "rules_python",
    url = "https://github.com/bazelbuild/rules_python/releases/download/0.1.0/rules_python-0.1.0.tar.gz",
    sha256 = "b6d46438523a3ec0f3cead544190ee13223a52f6a6765a29eae7b7cc24cc83a0"
)

load("@rules_python//python:pip.bzl", "pip_install")

# Create a central repo that knows about the dependencies needed for
# requirements.txt.
pip_install(
   name = "python_imports", 
   requirements="//:configuration/requirements.txt"
)