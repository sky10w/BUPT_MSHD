package com.example.SE_disaster.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.example.SE_disaster.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.SE_disaster.utils.JwtUtil;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        return true;
        if (handler instanceof HandlerMethod) {
            String token = request.getHeader("Authorization");
            System.out.println(token);
            try {
                if (token != null && !token.isEmpty()) {
                    JwtUtil jwtUtil = new JwtUtil();
                    Map<String, Claim> claims = jwtUtil.verifyToken(token);
                    System.out.println(claims);
                    if (claims != null) {
                        Claim claim = claims.get("claims");
                        System.out.println(claim);
                        Object uid = claim.asMap().get("uid");
                        System.out.println(uid);
                        UserUtil.setId(Long.valueOf(uid.toString()));
                        return true;
                    }
                }
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
                return false;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 删除用户id
        UserUtil.removeId();
    }
}
