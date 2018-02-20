package com.tutorial.app;

import com.tutorial.app.resources.UserResource;
import com.tutorial.app.dao.HeartBeatDAO;
import com.tutorial.app.dao.UserDAO;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Server extends Application<AppConfig> {
    public static void main(String[] args) throws Exception {
        new Server().run(args);
    }

    private HibernateBundle<AppConfig> hibernateBundle;
    private MigrationsBundle<AppConfig> migrationsBundle;

    @Override
    public String getName() {
        return "Health";
    }

    // This method is used to configure different aspects of the application,
    // as it gets executed before run(), like bundles, source providers, etc.
    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        migrationsBundle = new MigrationsBundle<AppConfig>() {
            
            public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
                return configuration.getDataSourceFactory();
            }
        };
        hibernateBundle = new ScanningHibernateBundle<AppConfig>("com.tutorial.app.domain") {

			public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
				return configuration.getDataSourceFactory();
			}
        };
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(migrationsBundle);
    }

    @Override
    public void run(AppConfig configuration,
                    Environment environment) {
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        final HeartBeatDAO hbDAO = new HeartBeatDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new UserResource(userDAO, hbDAO));
    }
}