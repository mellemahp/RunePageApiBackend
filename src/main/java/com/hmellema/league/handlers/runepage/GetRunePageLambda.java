package com.hmellema.league.handlers.runepage;

import com.amazonaws.services.lambda.runtime.Context;
import com.hmellema.lambdatools.handlers.services.ApiGatewayRestHandler;
import com.hmellema.league.model.GetRunePageInput;
import com.hmellema.league.model.GetRunePageOutput;
import com.hmellema.league.model.Rune;
import com.hmellema.league.model.RuneType;

import com.hmellema.league.dagger.DaggerBaseComponent;

import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;

public class GetRunePageLambda 
  extends ApiGatewayRestHandler<GetRunePageInput, GetRunePageOutput> {

  public GetRunePageLambda() {
    super(GetRunePageInput.class);
    DaggerBaseComponent.builder().build().inject(this);
  }

  @Override
  protected GetRunePageOutput handler(
    GetRunePageInput request,
    Context context
  ) {
    log.info("{}", "SOME STUFF");
    return new GetRunePageOutput()
      .name(request.getChampionName())
      .juliOmeter(new BigDecimal(1000))
      .runes(ImmutableList.of(
        new Rune().name("Dark Harvest")
          .type(RuneType.PRECISION)
          .effect("BORKS UP THE ENEMY")
      ));
  }
}
