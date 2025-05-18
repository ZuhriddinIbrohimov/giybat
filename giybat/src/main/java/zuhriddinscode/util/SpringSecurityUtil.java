package zuhriddinscode.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import zuhriddinscode.config.CustomUserDetails;

public class SpringSecurityUtil {

    public static CustomUserDetails getCurrentProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user;
    }

    public static Integer getCurrentUserId() {    // murojaat qilayotgan foydalanuvchi idsi
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getId();
    }
}