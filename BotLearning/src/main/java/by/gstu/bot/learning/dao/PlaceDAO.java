package by.gstu.bot.learning.dao;

import by.gstu.bot.learning.domain.Place;

import java.util.List;

public interface PlaceDAO {

	void addPlace(Place place);

	List<Place> listPlaces();

	void removePlace(Integer id);

	Place getPlace(Integer id);

	void editPlace(Place place);
}