package eudcApi.guice.module;

import com.google.inject.AbstractModule;
import eudcApi.guice.GuiceInjector.Module;
import eudcApi.server.EmbeddedJettyTest;

@Module
public class StaticModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(EmbeddedJettyTest.class);
    }
}
