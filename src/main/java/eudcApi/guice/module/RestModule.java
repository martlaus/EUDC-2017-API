package eudcApi.guice.module;

import com.google.inject.servlet.ServletModule;
import eudcApi.guice.GuiceInjector;
import eudcApi.service.AuthenticatedUserService;
import eudcApi.service.EventService;
import eudcApi.service.UserService;


@GuiceInjector.Module
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(AuthenticatedUserService.class);
        bind(UserService.class);
        bind(EventService.class);
    }
}
