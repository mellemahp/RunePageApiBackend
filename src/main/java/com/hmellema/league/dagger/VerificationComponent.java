package com.hmellema.league.dagger;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { VerificationModule.class })
public interface VerificationComponent {}
