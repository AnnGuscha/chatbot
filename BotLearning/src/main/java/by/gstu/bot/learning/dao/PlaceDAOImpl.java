package by.gstu.bot.learning.dao;

import by.gstu.bot.learning.domain.Place;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PlaceDAOImpl implements PlaceDAO {

	protected static Logger logger = Logger.getLogger("main/java/by/gstu/bot/learning/dao");

	public PlaceDAOImpl() {
	}

	@Autowired
	private SessionFactory sessionFactory;

	public void addPlace(Place place) {
		logger.debug("Adding new place");
		sessionFactory.getCurrentSession().save(place);
	}

	@SuppressWarnings("unchecked")
	public List<Place> listPlaces() {
		logger.debug("Retrieving all places");
		return sessionFactory.getCurrentSession().createQuery("from Place")
			.list();
	}

	public void removePlace(Integer id) {
		logger.debug("Deleting existing place");
		Place place = (Place) sessionFactory.getCurrentSession().load(
				Place.class, id);
		if (null != place) {
			sessionFactory.getCurrentSession().delete(place);
		}
	}

	public Place getPlace(Integer id)
	{
		return (Place) sessionFactory.getCurrentSession().get(Place.class, id);
	}

	public void editPlace(Place place) {
		logger.debug("Editing existing place");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing place via id
		Place existingPlace = (Place) session.get(Place.class, place.getId());

		// Assign updated values to this place
		existingPlace.setName(place.getName());
		existingPlace.setType(place.getType());
		existingPlace.setDescription(place.getDescription());
		existingPlace.setWorktime(place.getWorktime());

		// Save updates
		session.save(existingPlace);
	}
}
