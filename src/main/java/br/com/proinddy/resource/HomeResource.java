package br.com.proinddy.resource;

import br.com.proinddy.config.DOA;
import br.com.proinddy.exception.SqlFileNotFoundException;
import br.com.proinddy.helper.GenericSQL;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;

@RequestScoped
@Path("/home")
public class HomeResource {

    @Inject
    DOA doa;

    @Inject
    private GenericSQL genericSQL;

    public final String version = "1.0-final";

    @GET
    @Path("/info")
    public String info() {
        return this.version;
    }


}
