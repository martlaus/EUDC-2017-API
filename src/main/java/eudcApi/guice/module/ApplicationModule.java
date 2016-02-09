package eudcApi.guice.module;

import com.google.inject.AbstractModule;
import eudcApi.ApplicationLauncher;
import eudcApi.ApplicationManager;
import eudcApi.guice.GuiceInjector;

@GuiceInjector.Module
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(ApplicationLauncher.class);
        requestStaticInjection(ApplicationManager.class);
    }
}
