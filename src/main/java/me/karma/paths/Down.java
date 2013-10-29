package me.karma.paths;

import com.yammer.metrics.annotation.Timed;
import me.karma.core.Karma;
import me.karma.db.KarmaDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkState;

@Path("/{name}/down")
@Produces(MediaType.APPLICATION_JSON)
public class Down {

    private final KarmaDAO karmaStore;

    public Down(KarmaDAO karma) {
        this.karmaStore = karma;
    }

    @GET
    @Timed
    public Karma down(@PathParam("name") String name) {
        boolean success = karmaStore.down(name) == 1;

        if (!success) karmaStore.insert(name, -1);

        Integer value = karmaStore.get(name);

        checkState(value != null, "Karma of [%s] cannot be null after decreasing", name);

        return new Karma(name, value);
    }
}
