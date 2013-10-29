package me.karma;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;
import me.karma.db.KarmaDAO;
import me.karma.health.BasicHealthCheck;
import me.karma.paths.*;
import org.skife.jdbi.v2.DBI;

public class KarmaService extends Service<KarmaConfiguration> {

    @Override
    public void initialize(Bootstrap<KarmaConfiguration> bootstrap) {
        bootstrap.setName("karma");
        bootstrap.addBundle(new AssetsBundle("/static/", "/"));
    }

    @Override
    public void run(KarmaConfiguration configuration, Environment environment) throws Exception {
        DBI jdbi = new DBIFactory().build(environment, configuration.getDatabaseConfiguration(), "db");

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
