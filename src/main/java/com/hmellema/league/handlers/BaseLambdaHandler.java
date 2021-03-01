package com.hmellema.league.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.hmellema.league.dagger.BaseComponent;
import com.hmellema.league.dagger.DaggerBaseComponent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public abstract class BaseLambdaHandler<InputType, OutputType>
  implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
  protected LambdaLogger logger;
  protected final BaseComponent baseComponent;
  protected final Gson objectMapper;

  protected BaseLambdaHandler() {
    objectMapper = new Gson();
    baseComponent = DaggerBaseComponent.builder().build();
  }

  private APIGatewayProxyResponseEvent initializeResponse(Context context) {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("x-OriginalAwsRequestId", context.getAwsRequestId());

    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    response.setIsBase64Encoded(false);
    response.setHeaders(headers);

    return response;
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(
    APIGatewayProxyRequestEvent event,
    Context context
  ) {
    logger = context.getLogger();
    APIGatewayProxyResponseEvent response = initializeResponse(context);

    Map<String, String> requestBody = constructInputMap(event);
    InputType inputTypeRequest = convertToInputType(requestBody);
    OutputType result = handler(inputTypeRequest, context);

    String jsonString = objectMapper.toJson(result);
    response.setBody(jsonString);
    response.setStatusCode(200);
    return response;
  }

  private Map<String, String> constructInputMap(APIGatewayProxyRequestEvent request) {
    Map<String, String> inputMap = new HashMap();
    if (request.getQueryStringParameters() != null) {
      inputMap.putAll(request.getQueryStringParameters()); 
    }
    if (request.getPathParameters() != null) {
      inputMap.putAll(request.getPathParameters());
    }
    if (request.getBody() != null) {
      Map<String, String>bodyMap = objectMapper.fromJson(request.getBody(), Map.class);
      inputMap.putAll(bodyMap);
    }

    return inputMap;
  }

  protected abstract InputType convertToInputType(Map<String, String> inputMap);

  protected abstract OutputType handler(
    InputType request,
    Context context
  );
}
