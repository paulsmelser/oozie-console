package org.smelser.oozie.controller;

import javax.servlet.http.Cookie;

/**
 * Created by paul.smelser@gmail.com on 06/10/15.
 *
 * @author psmelser
 */
public interface AuthCookieService {
    public Cookie create(String clusterUri, String username, String password);
}
