package com.hmellema.league.dagger;

import com.hmellema.league.handlers.itemset.GetItemSetLambda;
import com.hmellema.league.handlers.runepage.GetRunePageLambda;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { BaseModule.class })
public interface BaseComponent {
  void inject(GetItemSetLambda getItemSetLambda);
  void inject(GetRunePageLambda getItemSetLambda);
}
