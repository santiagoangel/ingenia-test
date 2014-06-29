

package com.github.santiagoangel.ingenia.service;

import com.github.santiagoangel.ingenia.App;
import com.github.santiagoangel.ingenia.StatusMessage;
import com.github.santiagoangel.ingenia.Token;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Access to App data.
 * @author santiago
 */
@Stateless
@Path("apps")
public class AppsFacadeREST extends AbstractFacade<App> {
    @PersistenceContext(unitName = "ingenia")
    private EntityManager em;

    public AppsFacadeREST() {
        super(App.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public Response create(App entity, @HeaderParam("AUTH-TOKEN") String token) {
    	StatusMessage m = new StatusMessage();
    	String gtoken=null;	
    	if((gtoken=authorizeApp(entity.getAppname(), token))!=null){
    			
    			try{
    			//em.persist(entity);
    				em.merge(entity);
    			}catch(Exception e){
    				return Response.status(400).build();
    				
    			}
    		}else{
    			return Response.status(401).build();
    		}
    		
    	m.setStatus("OK");
		m.setMessage("NEW AUTH-TOKEN: "+gtoken);
        return Response.ok().entity(m).build();
        
    	
    }

   

	@PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public Response edit(@PathParam("id") String id, App entity, @HeaderParam("AUTH-TOKEN") String token) {
        
		StatusMessage m = new StatusMessage();
        String gtoken=null;	
if((gtoken=authorizeApp(entity.getAppname(), token))!=null){
    			
    			try{
    				em.merge(entity);
    			
    			}catch(Exception e){
    				return Response.status(400).build();
    				
    			}
    		}else{
    			return Response.status(401).build();
    		}
    		
        
m.setStatus("OK");
m.setMessage("NEW AUTH-TOKEN: "+gtoken);
return Response.ok().entity(m).build();
        
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id, @HeaderParam("AUTH-TOKEN") String token) {
        
    	App entity = em.find(App.class, id);
    	String gtoken=null;	
    	if((gtoken=authorizeApp(entity.getAppname(), token))!=null){
    			
    			try{
    				Token trashtoken= em.find(Token.class, entity.getAppname());
    				em.remove(trashtoken);
    				em.remove(entity);
    			
    				
    			}catch(Exception e){
    				return Response.status(400).build();
    				
    			}
    		}else{
    			return Response.status(401).build();
    		}
    		
        
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public App find(@PathParam("id") String id, @HeaderParam("AUTH-TOKEN") String token) {
        
    	App entity =null;
    	String gtoken=null;
    	try{
        entity = em.find(App.class, id);
        	
        if((gtoken=authorizeApp(entity.getAppname(), token))!=null){
			
        	return entity;
			
        }}catch(Exception e){
			
			
		}
		
    
    	return null;
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<App> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<App> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    protected String authorizeApp(String appname, String token) {
		
    	String gtoken=null;
    	if(token!=null&&!token.equals("")){
    		try {

    			App app = em.find(App.class, appname);
    			
    			if (app != null) {
    				Token savedtoken = em.find(Token.class, appname);
    				if(savedtoken!=null){
    					if(savedtoken.getToken().equals(token)){
    						gtoken = UUID.randomUUID().toString();
    						savedtoken.setToken(gtoken);
    						gtoken=(em.merge(savedtoken)).getToken();
    					}
    				}
    				
    			}
    		}catch(Exception e){}
    		
    		
    		
    	}
		return gtoken;
	}
    
}
