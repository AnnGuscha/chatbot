package by.gstu.bot.learning.service;

import by.gstu.bot.learning.dao.PlaceDAO;
import by.gstu.bot.learning.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    public PlaceDAO placeDAO;

    public PlaceServiceImpl() {
    }

    @Transactional
    public void add(Place contact) {
        placeDAO.addPlace(contact);
    }

    @Transactional
    public List<Place> list() {
        return placeDAO.listPlaces();
    }

    @Transactional
    public void remove(Integer id) {
        placeDAO.removePlace(id);
    }

    @Transactional
    public Place get(Integer id) {
        return placeDAO.getPlace(id);
    }

    @Transactional
    public void edit(Place place) {
        placeDAO.editPlace(place);
    }
}
