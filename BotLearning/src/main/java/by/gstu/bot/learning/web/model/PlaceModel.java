package by.gstu.bot.learning.web.model;

import by.gstu.bot.learning.domain.Place;

public class PlaceModel extends Place {

    public PlaceModel(String name, String type, String description, String worktime) {
        super(type, name, description, worktime);
    }

    public PlaceModel(Place place) {
        super(place.getType(), place.getName(), place.getDescription(), place.getWorktime());
    }
}
