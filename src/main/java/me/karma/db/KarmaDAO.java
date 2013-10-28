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

    @SqlUpdate("insert into karma (name, value) values (:name, :value)")
    int insert(@Bind("name") String name, @Bind("value") int value);

    @SqlUpdate("update karma set value = :value where name = :name")
    int set(@Bind("name") String name, @Bind("karma") int value);

    @SqlUpdate("update karma set value = value + 1 where name = :name")
    int bump(@Bind("name") String name);

    @SqlUpdate( "update karma set value = value - 1 where name = :name")
    int down(@Bind("name") String name);

    @SqlQuery("select value from karma where name = :name")
    Integer get(@Bind("name") String name);

    @SqlQuery("select name, value from karma order by value desc limit :n")
    List<Karma> top(@Bind("n") int n);

    @SqlQuery("select name, value from karma order by value asc limit :n")
    List<Karma> bottom(@Bind("n") int n);
}
