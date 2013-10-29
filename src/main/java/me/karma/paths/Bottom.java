package me.karma.paths;

import com.yammer.metrics.annotation.Timed;
import me.karma.core.Karma;
import me.karma.db.KarmaDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public List<Karma> bottom(@PathParam("n") int n) {
        return karmaStore.bottom(n);
    }
}
