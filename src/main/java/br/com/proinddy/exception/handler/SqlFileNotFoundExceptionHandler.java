package br.com.proinddy.exception.handler;

import br.com.proinddy.exception.model.ErrorMessage;
import br.com.proinddy.exception.SqlFileNotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SqlFileNotFoundExceptionHandler implements ExceptionMapper<SqlFileNotFoundException> {

    @ConfigProperty(name = "mixtel.report.error.msg.sqlfilenotfound")
    String sqlFileNotFound;

    @Override
    public Response toResponse(SqlFileNotFoundException exception) {

        if(exception.getMessage() == null){
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ErrorMessage(sqlFileNotFound, false)).build();
        }

        if (exception.getMessage().equalsIgnoreCase(sqlFileNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ErrorMessage(exception.getMessage(), false)).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).
                entity(new ErrorMessage(exception.getMessage(), false)).build();

    }


}
