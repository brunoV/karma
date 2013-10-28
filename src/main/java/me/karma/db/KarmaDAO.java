package me.karma.db;

import me.karma.core.Karma;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(KarmaMapper.class)
public interface KarmaDAO {

    @SqlUpdate("create table if not exists karma (name varchar PRIMARY KEY, value integer)")
    void createKarmaTableIfNotExists();

    @SqlUpdate(
        "update karma set value = :value where name = :name;" +
        "insert into karma (name, value) select :name, :value " +
        "where not exists (select 1 from karma where name = :name);")
    void set(@Bind("name") String name, @Bind("karma") int value);

    // Simulated upsert: we bump the value, assuming the name exists. If it doesn't,
    // the first update does nothing.
    // The insert that follows inserts a row for the name whose karma we want to
    // bump, setting the value to 1. If the name already exists, it does nothing.
    @SqlUpdate(
        "update karma set value = value + 1 where name = :name;" +
        "insert into karma (name, value) select :name, 1 " +
        "where not exists (select 1 from karma where name = :name);")
    void bump(@Bind("name") String name);

    @SqlUpdate(
        "update karma set value = value - 1 where name = :name;" +
        "insert into karma (name, value) select :name, -1 " +
        "where not exists (select 1 from karma where name = :name);")
    void down(@Bind("name") String name);

    @SqlQuery("select value from karma where name = :name")
    Integer get(@Bind("name") String name);

    @SqlQuery("select name, value from karma order by value desc limit :n")
    List<Karma> top(@Bind("n") int n);

    @SqlQuery("select name, value from karma order by value asc limit :n")
    List<Karma> bottom(@Bind("n") int n);
}
