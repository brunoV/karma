package me.karma.paths;

import com.yammer.metrics.annotation.Timed;
import me.karma.core.Karma;
import me.karma.db.KarmaDAO;
import me.karma.params.PositiveIntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bottom/{n}")
@Produces(MediaType.APPLICATION_JSON)
public class Bottom {

    private final KarmaDAO karmaStore;

    public Bottom(KarmaDAO karma) {
        this.karmaStore = karma;
    }

    @GET
    @Timed
    public List<Karma> bottom(@PathParam("n") PositiveIntParam n) {
        return karmaStore.bottom(n.get());
    }
}
