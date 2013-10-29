package me.karma;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.jdbi.DBIFactory;
import me.karma.db.KarmaDAO;
import me.karma.health.BasicHealthCheck;
import me.karma.paths.*;
import org.skife.jdbi.v2.DBI;

public class KarmaService extends Service<KarmaConfiguration> {

    private static final String SERVICE_NAME = "karma";
    private static final String DB_NAME = "store";
    private static final String STATIC_CONTENT_FOLDER = "/static/";
    private static final String URI_PATH = "/";


    @Override
    public void initialize(Bootstrap<KarmaConfiguration> bootstrap) {
        bootstrap.setName(SERVICE_NAME);

        // This assets bundle lets us serve static content from a resource
        // folder on the root path of application.
        // The rootPath setting in the config file needs to point to a subpath
        // for the service.
        bootstrap.addBundle(new AssetsBundle(STATIC_CONTENT_FOLDER, URI_PATH));
    }

    @Override
    public void run(KarmaConfiguration configuration, Environment environment) throws Exception {
        DatabaseConfiguration dbConfig = configuration.getDatabaseConfiguration();

        DBI jdbi = new DBIFactory().build(environment, dbConfig, DB_NAME);

        KarmaDAO dao = jdbi.onDemand(KarmaDAO.class);
        dao.createKarmaTableIfNotExists();

        environment.addResource(new KarmaResource(dao));
        environment.addResource(new Bump(dao));
        environment.addResource(new Down(dao));
        environment.addResource(new Top(dao));
        environment.addResource(new Bottom(dao));

        environment.addHealthCheck(new BasicHealthCheck(dao));
    }

    public static void main(String[] args) throws Exception {
        new KarmaService().run(args);
    }
}
