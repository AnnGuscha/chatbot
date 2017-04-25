package by.gstu.bot.learning.service;

import by.gstu.bot.learning.domain.Place;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("placeService")
@Transactional
public interface PlaceService {

    void add(Place place);

    List<Place> list();

    void remove(Integer id);

    Place get(Integer id);

    void edit(Place place);
}
