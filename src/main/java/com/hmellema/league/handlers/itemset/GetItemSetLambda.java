package com.hmellema.league.handlers.itemset;

import com.amazonaws.services.lambda.runtime.Context;
import com.hmellema.league.handlers.BaseLambdaHandler;

import com.hmellema.league.model.GetItemSetOutput;
import com.hmellema.league.model.GetItemSetInput;

public class GetItemSetLambda 
  extends BaseLambdaHandler<GetItemSetInput, GetItemSetOutput> {

  public GetItemSetLambda() {
    super(GetItemSetInput.class);
    baseComponent.inject(this);
  }

  @Override
  protected GetItemSetOutput handler(
    GetItemSetInput request,
    Context context
  ) {
    return new GetItemSetOutput().name(request.getChampionName());
  }
}
