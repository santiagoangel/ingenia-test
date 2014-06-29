package com.github.santiagoangel.ingenia;

import javax.servlet.ServletException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServletExceptionMapper implements ExceptionMapper<javax.servlet.ServletException>
{

public Response toResponse(ServletException exception) {
return Response.status(500).build();
}

}
@Provider
class RTExceptionMapper implements ExceptionMapper<java.lang.RuntimeException>
{

public Response toResponse(RuntimeException exception) {
return Response.status(500).build();
}

}

