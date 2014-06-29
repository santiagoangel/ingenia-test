

package com.github.santiagoangel.ingenia.service;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.github.santiagoangel.ingenia.App;
import com.github.santiagoangel.ingenia.StatusMessage;
import com.github.santiagoangel.ingenia.Token;
import com.github.santiagoangel.ingenia.User;

/**
 * Token generation Facade
 * @author santiago
 */
@Stateless
@Path("auth")
public class AuthFacadeREST {
	@PersistenceContext(unitName = "ingenia")
	private EntityManager em;

	public AuthFacadeREST() {

	}

	@POST
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	public Response token(@HeaderParam("Credentials") String credentials,
			@HeaderParam("APIKey") String key, @HeaderParam("ApplicationName") String appname) {
		if(credentials!=null&&key!=null&&appname!=null&&!credentials.equals("")&&!key.equals("")&&!appname.equals("")){
			return getResponse(credentials, key, appname);
		}

		return Response.status(400).build();

	}

	@GET
	@Consumes({ "text/plain", "text/html" })
	@Produces({ "application/xml", "application/json" })
	public Response tokentest(@QueryParam("Credentials") String credentials,
			@QueryParam("APIKey") String key, @QueryParam("ApplicationName") String appname) {

		if(credentials!=null&&key!=null&&appname!=null&&!credentials.equals("")&&!key.equals("")&&!appname.equals("")){
			return getResponse(credentials, key, appname);
		}

		return Response.status(400).build();

	}

	protected Response getResponse(String credentials, String key,
			String appname) {
		StatusMessage m = new StatusMessage();
		User access = em.find(User.class, credentials);
		if (access != null) {
			if (access.getPassword().equals(key)) {
				m.setStatus("Authorized. Token for "+ appname);
				try {
					m.setMessage(generateToken(credentials, key, appname));
					return Response.ok().entity(m).build();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		m.setStatus("ERROR");
		m.setMessage("Unauthorized");
		return Response.status(401).entity(m).build();
	}

	protected String generateToken(String credentials, String key,
			String appname) {

		String gtoken = null;

		try {

			App app = em.find(App.class, appname);
			gtoken = UUID.randomUUID().toString();
			if (app == null) {

				app = new App();
				app.setAppname(appname);
				app.setCompany("TOBEDEFINED");

				em.persist(app);
				App savedapp = em.find(App.class, appname);
				Token token = new Token();
				
				token.setToken(gtoken);
				token.setAppname(appname);
				token.setApp(savedapp);

				em.persist(token);

			} else {
				Token savedtoken = em.find(Token.class, appname);
				if(savedtoken!=null){
					savedtoken.setToken(gtoken);
					em.merge(savedtoken);					
				}
				else{
					Token token = new Token();
					
					token.setToken(gtoken);
					token.setAppname(appname);
					token.setApp(app);
					em.persist(token);					
				}
				
			}

		} catch (Exception e) {

			e.printStackTrace();
			gtoken = null;
		}

		return gtoken;

	}

	protected EntityManager getEntityManager() {
		return em;
	}
	
	

}
