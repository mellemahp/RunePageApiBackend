#!/usr/bin/python
import os
import argparse
import toml
import yaml
import json
import pdb

import openapi
from jinja2 import FileSystemLoader, Environment

def initialize_jinja(template_file, searchpath="./"):
    template_loader = FileSystemLoader(searchpath=searchpath)
    template_env = Environment(loader=template_loader)
    template = template_env.get_template(template_file)

    return template

def render(template, openapi_spec, lambda_list, base_package): 
    with open(openapi_spec) as spec: 
        openapi_data = json.load(spec)

    output_text = template.render(
        open_api_definition=openapi_data,
        lambda_list=lambda_list,
        base_package=base_package
    )

    return output_text

def get_paths(openapi_spec): 
    with open(openapi_spec) as fileobj:
        openapi_obj = openapi.load(fileobj)

    return openapi_obj.paths

def get_lambda_list(paths):
    lambda_list = []
    for path in paths.keys(): 
        for method in paths[path].keys(): 
            lambda_list.append({
                "method": method,
                "path": path, 
                "operationId": paths[path][method]['operationId'],
                "base_route": paths[path][method]['operationId'].lower().replace(method, '')
            })
    return lambda_list

def main(args): 
    template = initialize_jinja(args.template_file)
    paths = get_paths(args.openapi_spec)
    lambda_list = get_lambda_list(paths)
    with open(args.output_file, 'w') as file: 
        file.write(
            render(
                template, 
                args.openapi_spec, 
                lambda_list,
                args.base_package
            )
        )

if __name__ == "__main__": 
    print("Executing Jinjafier...")
    import os
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "-t", "--template-file", 
        help="template file to use for generating SAM deploy yaml",
        type=str
    )
    parser.add_argument(
        "-o", "--output-file", 
        help="location to write output template file",
        type=str
    )
    parser.add_argument(
        "-s", "--openapi-spec",
        help="open API json specification to use",
        type=str
    )
    parser.add_argument(
        "-b", "--base-package", 
        help="base java package",
        type=str
    )
    args = parser.parse_args()
    main(args)

    print("Jinjafication Complete")