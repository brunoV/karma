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

@Path("/{name}/down")
@Produces(MediaType.APPLICATION_JSON)
public class Down {

    private final KarmaDAO karmaStore;

    public Down(KarmaDAO karma) {
        this.karmaStore = karma;
    }

    @POST
    @Timed
    public Response down(@PathParam("name") String name) throws UnsupportedEncodingException {
        boolean success = karmaStore.down(name) == 1;

        if (!success) karmaStore.insert(name, -1);

        // We need to do this because, as per the HTTP spec, spaces can be encoded
        // into the '+' character.
        // However, in my test the resulting URI with '+' sign is treated
        // differently as the same URI but with "%20".
        String encodedName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");

        return Response.seeOther(URI.create(encodedName)).build();
    }
}
