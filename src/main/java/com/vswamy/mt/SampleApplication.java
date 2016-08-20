package com.vswamy.mt;


import com.vswamy.mt.handlers.CacheHandler;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SampleApplication extends Application<SampleConfiguration>
{
    public static void main(String[] args) throws Exception
    {
        new SampleApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SampleConfiguration> bootstrap)
    {
        bootstrap.addBundle(new MigrationsBundle<SampleConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(SampleConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
    });
    }

    @Override
    public void run(SampleConfiguration configuration, Environment environment)
    {
        final CacheHandler resource = new CacheHandler();
        environment.jersey().register(resource);
    }

}