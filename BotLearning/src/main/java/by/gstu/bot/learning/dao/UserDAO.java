package by.gstu.bot.learning.dao;

import by.gstu.bot.learning.domain.User;

import java.util.List;

public interface UserDAO {

	void add(User user);

	List<User> list();

	void remove(Integer id);

	User get(Integer id);

	void edit(User user);

	User get(String login);
}