package com.hmellema.league.handlers.itemset;

import com.amazonaws.services.lambda.runtime.Context;
import com.hmellema.lambdatools.handlers.services.ApiGatewayRestHandler;

import com.hmellema.league.dagger.DaggerBaseComponent;

import com.hmellema.league.model.GetItemSetOutput;
import com.hmellema.league.model.GetItemSetInput;

public class GetItemSetLambda 
  extends ApiGatewayRestHandler<GetItemSetInput, GetItemSetOutput> {

  public GetItemSetLambda() {
    super(GetItemSetInput.class);
    DaggerBaseComponent.builder().build().inject(this);
  }

  @Override
  protected GetItemSetOutput handler(
    GetItemSetInput request,
    Context context
  ) {
    return new GetItemSetOutput().name(request.getChampionName());
  }
}
