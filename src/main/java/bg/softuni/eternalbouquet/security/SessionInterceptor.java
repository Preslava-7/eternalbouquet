package bg.softuni.eternalbouquet.security;

import bg.softuni.eternalbouquet.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String endpoint = request.getRequestURI();

        if (endpoint.startsWith("/bouquets")) {
            return true;
        }

        if (endpoint.equals("/") ||
                endpoint.equals("/index") ||
                endpoint.startsWith("/index?") ||
                endpoint.equals("/login") ||
                endpoint.equals("/register") ||
                endpoint.equals("/error") ||
                endpoint.startsWith("/css/") ||
                endpoint.startsWith("/js/") ||
                endpoint.startsWith("/images/")) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
