package plaintext

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("api")
interface PingEndpoint {

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    String ping()

}