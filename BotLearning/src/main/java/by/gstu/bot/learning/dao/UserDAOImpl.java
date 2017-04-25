package by.gstu.bot.learning.dao;

import by.gstu.bot.learning.domain.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

	protected static Logger logger = Logger.getLogger(UserDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {
	}

	public void add(User user) {
		logger.debug("Adding new user");
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {

		logger.debug("Retrieving all users");
		return sessionFactory.getCurrentSession().createQuery("from User")
			.list();
	}

	public void remove(Integer id) {
		logger.debug("Deleting existing user");
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
	}

	public User get(String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where login=:username");
		query.setParameter("username", name);
		User user = (User) query.list().get(0);
		return user;
	}

	public User get(Integer id)
	{
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public void edit(User user) {
		logger.debug("Editing existing user");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing user via id
		User existingUser = (User) session.get(User.class, user.getId());

		// Assign updated values to this user
		existingUser.setLogin(user.getLogin());
		existingUser.setPassword(user.getPassword());
		existingUser.setRole(user.getRole());

		// Save updates
		session.save(existingUser);
	}
}
