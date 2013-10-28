package me.karma.paths;

import com.yammer.metrics.annotation.Timed;
import me.karma.core.Karma;
import me.karma.db.KarmaDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.google.common.base.Objects.firstNonNull;

@Path("/karma/{name}")
@Produces(MediaType.APPLICATION_JSON)
public class KarmaResource {

    private final KarmaDAO karmaStore;

    public KarmaResource(KarmaDAO karma) {
        this.karmaStore = karma;
    }

    @GET
    @Timed
    public me.karma.core.Karma getKarma(@PathParam("name") String name) {
        return new Karma(name, firstNonNull(karmaStore.get(name), 0));
    }

    @POST
    @Timed
    public Response setKarma(@Valid me.karma.core.Karma karma) {
        karmaStore.set(karma.getName(), karma.getValue());
        return Response.ok().build();
    }
}
