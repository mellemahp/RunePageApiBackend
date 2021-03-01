package com.hmellema.league.dagger;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class BaseModule {

  @Singleton
  @Provides
  String test() {
    return "HELLO WORLD";
  }
}
