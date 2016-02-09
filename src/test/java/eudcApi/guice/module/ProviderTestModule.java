package eudcApi.guice.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import eudcApi.guice.GuiceInjector;
import eudcApi.guice.provider.ConfigurationTestProvider;
import eudcApi.guice.provider.EntityManagerFactoryTestProvider;
import eudcApi.guice.provider.ObjectMapperGuiceProvider;
import org.apache.commons.configuration.Configuration;

import javax.persistence.EntityManagerFactory;

@GuiceInjector.Module(override = ProviderModule.class)
public class ProviderTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryTestProvider.class);
        bind(Configuration.class).toProvider(ConfigurationTestProvider.class);
        bind(ObjectMapper.class).toProvider(ObjectMapperGuiceProvider.class);
    }
}
