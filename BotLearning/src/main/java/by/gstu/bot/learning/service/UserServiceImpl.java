package by.gstu.bot.learning.service;

import by.gstu.bot.learning.dao.UserDAO;
import by.gstu.bot.learning.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDAO userDAO;

    public UserServiceImpl() {
    }

    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Transactional
    public List<User> list() {
        return userDAO.list();
    }

    @Transactional
    public void remove(Integer id) {
        userDAO.remove(id);
    }

    @Transactional
    public User get(Integer id) {
        return userDAO.get(id);
    }

    @Transactional
    public User get(String login) {
        return userDAO.get(login);
    }


    @Transactional
    public void edit(User user) {
        userDAO.edit(user);
    }
}
