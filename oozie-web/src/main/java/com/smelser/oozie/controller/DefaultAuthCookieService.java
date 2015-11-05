package com.smelser.oozie.controller;

import com.smelser.oozie.utilities.EncryptUtils;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

/**
 * Created by paul.smelser@gmail.com on 06/10/15.
 *
 * @author psmelser
 */
@Component
public class DefaultAuthCookieService implements AuthCookieService{

    @Override
    public Cookie create(String clusterUri, String username, String password) {
        String cookieValue = String.format("%s|%s|%s", clusterUri, username, password);
        Cookie cookie = new Cookie("_oc", String.format("OC.1.2.%s",
                EncryptUtils.base64encode(cookieValue)));
        cookie.setMaxAge(24 * 60 * 60 * 80);
        cookie.setPath("/");
        cookie.setSecure(new Boolean(false));
        return cookie;
    }

    public static String decryptCookie(Cookie cookie, String salt) throws InvalidKeyException,
            InvalidAlgorithmParameterException, InvalidParameterSpecException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException,
            IOException {
        return EncryptUtils.base64decode(cookie.getValue());
    }

    public static String encrytCookie(String cookieValue, String salt) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return EncryptUtils.base64encode(cookieValue);
    }
}
