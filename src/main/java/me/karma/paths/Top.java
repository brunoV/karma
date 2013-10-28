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

import static com.google.common.base.Preconditions.checkState;

@Path("/karma/top/{n}")
@Produces(MediaType.APPLICATION_JSON)
public class Top {

    private final KarmaDAO karmaStore;

    public Top(KarmaDAO karma) {
        this.karmaStore = karma;
    }

    @GET
    @Timed
    public List<Karma> top(@PathParam("n") int n) {
        return karmaStore.top(n);
    }
}
