package io.dedyn.hy.watchworldshop.utils;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static String getBaseUrl(HttpServletRequest request) {
        return request.getRequestURL().toString().replace(request.getServletPath(), "");
    }
}
