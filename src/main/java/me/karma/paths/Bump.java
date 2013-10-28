package me.karma.paths;

import com.yammer.metrics.annotation.Timed;
import me.karma.core.Karma;
import me.karma.db.KarmaDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkState;

@Path("/karma/{name}/bump")
@Produces(MediaType.APPLICATION_JSON)
public class Bump {
    private final KarmaDAO karmaStore;

    public Bump(KarmaDAO karma) {
        this.karmaStore = karma;
    }

    @GET
    @Timed
    public Karma bump(@PathParam("name") String name) {
        boolean success = karmaStore.bump(name) == 1;

        if (!success) karmaStore.insert(name, 1);

        Integer value = karmaStore.get(name);

        checkState(value != null, "Karma of [%s] cannot be null after bumping", name);

        return new Karma(name, value);
    }
}
