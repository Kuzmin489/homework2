package eu.twino.homework.rest;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class WebUtils {

    private WebUtils() {

    }

    public static Optional<String> getClientIp(HttpServletRequest request) {
        String remoteAddr = null;

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return Optional.ofNullable(remoteAddr);
    }

}
