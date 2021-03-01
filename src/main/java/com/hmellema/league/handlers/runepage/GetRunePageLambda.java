package com.hmellema.league.handlers.runepage;

import com.amazonaws.services.lambda.runtime.Context;
import com.hmellema.league.handlers.BaseLambdaHandler;
import com.hmellema.league.model.GetRunePageInput;
import com.hmellema.league.model.GetRunePageOutput;
import com.hmellema.league.model.Rune;
import com.hmellema.league.model.RuneType;
import java.util.Map;
import java.io.IOException;
import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;

public class GetRunePageLambda 
  extends BaseLambdaHandler<GetRunePageInput, GetRunePageOutput> {

  public GetRunePageLambda() {
    super(GetRunePageInput.class);
    baseComponent.inject(this);
  }

  @Override
  protected GetRunePageOutput handler(
    GetRunePageInput request,
    Context context
  ) {
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
