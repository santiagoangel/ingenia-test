

package com.github.santiagoangel.ingenia.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Autowired configuration in Java WS RS
 * @author santiago
 */
@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.github.santiagoangel.ingenia.service.AppsFacadeREST.class);
        resources.add(com.github.santiagoangel.ingenia.service.TokenFacadeREST.class);
        resources.add(com.github.santiagoangel.ingenia.service.UsersFacadeREST.class);
        resources.add(com.github.santiagoangel.ingenia.service.AuthFacadeREST.class);
    }
    
}
