package com.smelser.oozie.authorization;

import javax.servlet.http.Cookie;

/**
 * Created by paul.smelser@gmail.com on 06/10/15.
 *
 * @author psmelser
 */
public interface AuthorizationCookie {
    Cookie create(String clusterUri, String username, String password);
    UserLogin getAuthFromCookie(String cookie);
}
