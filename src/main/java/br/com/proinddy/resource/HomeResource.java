package br.com.proinddy.resource;

import br.com.proinddy.config.DOA;
import br.com.proinddy.helper.GenericSQL;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RequestScoped
@Path("/home")
public class HomeResource {

    @Inject
    DOA doa;

    @Inject
    private GenericSQL genericSQL;

    public static final String VERSION = "1.0-final";

    @GET
    @Path("/info")
    public String info() {
        return VERSION;
    }
}
