package com.smelser.oozie.authorization;

import com.smelser.oozie.data.OozieClientFactory;
import com.smelser.oozie.utilities.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@gmail.com
 */
@RestController
@RequestMapping("api/v1")
@Scope(value = "session")
public class LoginResource {

    private final AuthorizationCookie authorizationCookie;
    private final ServiceLocator serviceLocator;
    private final OozieClientFactory factory;

    @Autowired
    public LoginResource(AuthorizationCookie authorizationCookie,
                         ServiceLocator serviceLocator,
                         OozieClientFactory factory) {
        this.authorizationCookie = authorizationCookie;
        this.serviceLocator = serviceLocator;
        this.factory = factory;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void login(@RequestParam(value = "clusterUri") String clusterUri,
                      @RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password,
                      @RequestParam(value = "stayLoggedIn") boolean stayLoggedIn,
                      HttpServletResponse response) {
        if (stayLoggedIn) {
            Cookie cookie = authorizationCookie.create(clusterUri, username, password);
            response.addCookie(cookie);
        }
        serviceLocator.register(factory.create(clusterUri, username, password));
        serviceLocator.getOozieClient().getStatus();
    }

    @RequestMapping(value = "login/demo", method = RequestMethod.GET)
    public void demoLogin(){
        System.setProperty("environment", "1");
        serviceLocator.register(factory.create("", "", ""));
        serviceLocator.getOozieClient().getStatus();
    }

    @RequestMapping(value="login/demo/cancel", method = RequestMethod.GET)
    public void cancelDemo(){
        System.setProperty("environment", "0");
        serviceLocator.removeOozieClient();
    }
}
