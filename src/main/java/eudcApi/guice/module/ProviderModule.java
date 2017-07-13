package eudcApi.guice.module;

import com.google.inject.AbstractModule;
import eudcApi.db.DatabaseMigrator;
import eudcApi.db.FlywayDbMigrator;
import eudcApi.guice.GuiceInjector.Module;
import eudcApi.guice.provider.ConfigurationProvider;
import eudcApi.guice.provider.EntityManagerFactoryProvider;
import eudcApi.guice.provider.EntityManagerProvider;
import eudcApi.guice.provider.HttpClientProvider;
import org.apache.commons.configuration.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.client.Client;

@Module
public class ProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Configuration.class).toProvider(ConfigurationProvider.class);
        bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryProvider.class);
        bind(EntityManager.class).toProvider(EntityManagerProvider.class);
        bind(Client.class).toProvider(HttpClientProvider.class);
        bind(DatabaseMigrator.class).to(FlywayDbMigrator.class);
    }
}
