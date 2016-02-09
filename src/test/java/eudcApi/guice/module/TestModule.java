package eudcApi.guice.module;

import com.google.inject.AbstractModule;
import eudcApi.common.test.ResourceIntegrationTestBase;
import eudcApi.guice.GuiceInjector.Module;

@Module()
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(ResourceIntegrationTestBase.class);
    }
}
