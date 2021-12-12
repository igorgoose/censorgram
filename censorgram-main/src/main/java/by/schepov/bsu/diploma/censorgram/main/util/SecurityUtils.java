package by.schepov.bsu.diploma.censorgram.main.util;

import by.schepov.bsu.diploma.censorgram.main.security.AppUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {

    public static AppUserDetails getUserDetails() {
        return (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getCurrentUserId() {
        return getUserDetails().getId();
    }
}
