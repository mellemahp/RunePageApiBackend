genrule(
    name = "bork",
    cmd = "echo 'TEST' > $@",
    outs = ["SPork.txt"],
    message = "Dummy Test rule",
    srcs = ["@api_model//:smithy_gen-jar"]
)