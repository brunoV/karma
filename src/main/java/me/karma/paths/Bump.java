package me.karma.paths;

import com.yammer.metrics.annotation.Timed;
import me.karma.db.KarmaDAO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Path("/{name}/bump")
@Produces(MediaType.APPLICATION_JSON)
public class Bump {
    private final KarmaDAO karmaStore;

    public Bump(KarmaDAO karma) {
        this.karmaStore = karma;
    }

    @POST
    @Timed
    public Response bump(@PathParam("name") String name) throws UnsupportedEncodingException {
        boolean success = karmaStore.bump(name) == 1;

        if (!success) karmaStore.insert(name, 1);

        String encodedName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        return Response.seeOther(URI.create(encodedName)).build();
    }
}
