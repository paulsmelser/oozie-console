package com.smelser.oozie.authorization;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul.smelser@gmail.com on 06/10/15.
 *
 * @author psmelser
 */
@Component
public class DefaultAuthorizationCookie implements AuthorizationCookie {

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

    public UserLogin getAuthFromCookie(String cookie) {
        String pattern = "OC.(\\d{1}).(\\d{1}).[A-Z, a-z, 0-9, =]*";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(cookie);
        if (matcher.find()) {
            String[] auth = EncryptUtils.base64decode(matcher.group().split("\\.")[3]).split("\\|");
            return new UserLogin(auth[0], auth[1], auth[2]);
        }
        return new UserLogin();
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
