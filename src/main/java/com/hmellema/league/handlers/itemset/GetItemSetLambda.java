package com.hmellema.league.handlers.itemset;

import com.amazonaws.services.lambda.runtime.Context;
import com.hmellema.league.handlers.BaseLambdaHandler;

import com.hmellema.league.model.GetItemSetOutput;
import com.hmellema.league.model.GetItemSetInput;
import java.util.Map;
import java.io.IOException;

public class GetItemSetLambda 
  extends BaseLambdaHandler<GetItemSetInput, GetItemSetOutput> {

  public GetItemSetLambda() {
    super();
    baseComponent.inject(this);
  }

  @Override
  protected GetItemSetOutput handler(
    GetItemSetInput request,
    Context context
  ) {
    return new GetItemSetOutput().name(request.getChampionName());
  }

  @Override
  protected GetItemSetInput convertToInputType(Map<String, String> inputMap) {
    String jsonString = objectMapper.toJson(inputMap);
    return objectMapper.fromJson(jsonString, GetItemSetInput.class);
  }
}
