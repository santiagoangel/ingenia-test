
package com.github.santiagoangel.ingenia.service;

import com.github.santiagoangel.ingenia.Token;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Token Management Facade.
 * @author santiago
 */
@Stateless
@Path("tokens")
public class TokenFacadeREST extends AbstractFacade<Token> {
    @PersistenceContext(unitName = "ingenia")
    private EntityManager em;

    public TokenFacadeREST() {
        super(Token.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public Response create(Token entity) {
        
        try{
            super.create(entity);
            return Response.ok().build();
            }
        	catch(Exception e){
        		
        	}
        	return Response.status(400).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public Response edit(@PathParam("id") String id, Token entity) {
        
        try{
            super.edit(entity);
            return Response.ok().build();
            }
        	catch(Exception e){
        		
        	}
        	return Response.status(400).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id) {
        
        try{
        	super.remove(super.find(id));
            return Response.ok().build();
            }
        	catch(Exception e){
        		
        	}
        	return Response.status(400).build();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Token find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Token> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Token> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
