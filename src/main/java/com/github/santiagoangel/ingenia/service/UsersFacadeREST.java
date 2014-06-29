

package com.github.santiagoangel.ingenia.service;


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

import com.github.santiagoangel.ingenia.User;



/**
 * User/Password or Credentials/APIKey Facade.
 * @author santiago
 */
@Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade<User> {
    @PersistenceContext(unitName = "ingenia")
    private EntityManager em;

    public UsersFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public Response create(User entity) {
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
    public Response edit(@PathParam("id") String id, User entity) {
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
    public User find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
