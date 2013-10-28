package me.karma.db;

import me.karma.core.Karma;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KarmaMapper implements ResultSetMapper<Karma> {
    @Override
    public Karma map(int i, ResultSet r, StatementContext ctx) throws SQLException {
        return new Karma(r.getString("name"), r.getInt("value"));
    }
}
