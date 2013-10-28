package me.karma.health;

import com.yammer.metrics.core.HealthCheck;

public class KarmaHealthCheck extends HealthCheck {

    public KarmaHealthCheck(String name) {
        super(name);
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
