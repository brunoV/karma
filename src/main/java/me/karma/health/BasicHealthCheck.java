package me.karma.health;

import com.yammer.metrics.core.HealthCheck;
import me.karma.db.KarmaDAO;

/**
 * Basic health check for the Karma service.
 * </p>
 * Will check that we can get the karma for a predetermined key without raising
 * an exception.
 */
public class BasicHealthCheck extends HealthCheck {

    private final KarmaDAO karmaStore;
    private static final String TEST_KEY = "__test_key";

    public BasicHealthCheck(KarmaDAO dao) {
        super("basic-get");
        this.karmaStore = dao;
    }

    @Override
    protected Result check() throws Exception {
        try {
            karmaStore.get(TEST_KEY);
        } catch (RuntimeException e) {
            return Result.unhealthy(e);
        }

        return Result.healthy();
    }
}
