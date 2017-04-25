package by.gstu.bot.learning.service;

import by.gstu.bot.learning.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public interface UserService {

    void add(User user);

    List<User> list();

    void remove(Integer id);

    User get(Integer id);

    User get(String login);

    void edit(User user);
}
