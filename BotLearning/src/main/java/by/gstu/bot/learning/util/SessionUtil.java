package by.gstu.bot.learning.util;

import by.gstu.bot.learning.domain.User;
import by.gstu.bot.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {

    public static UserService userService;

    public static User getSessionUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userService.get(userDetail.getUsername());
        return user;
    }

    @Autowired
    public void setSomeThing(UserService userService) {
        SessionUtil.userService = userService;
    }
}
