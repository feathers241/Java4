package Utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(hours * 3600);
        HttpServletResponse response = RRSharer.response();
        if (response != null) {
            response.addCookie(cookie);
        }
        return cookie;
    }

    public static Cookie get(String name) {
        HttpServletRequest request = RRSharer.request();
        if (request != null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static void remove(String name) {
        add(name, "", 0);
    }
}
