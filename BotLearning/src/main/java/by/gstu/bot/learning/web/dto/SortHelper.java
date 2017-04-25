package by.gstu.bot.learning.web.dto;

import by.gstu.bot.learning.domain.Place;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortHelper {
    public static <T> List<T> order(String sortDirection, List<T> filtered, Comparator orderingFunction) {
        Collections.sort(filtered, orderingFunction);
        if (sortDirection.equals("desc"))
            Collections.reverse(filtered);
        return filtered;
    }

    public static List<Place> sort(List<Place> placeList) {
        return placeList.stream().sorted((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).collect(Collectors.toList());
    }

    public static List<? extends Place> search(List<? extends Place> placeList, String sSearch) {
        return placeList.stream().filter(c -> c.getName().toLowerCase().contains(sSearch.toLowerCase())
                || c.getType().toLowerCase().contains(sSearch.toLowerCase())
                || c.getDescription().toLowerCase().contains(sSearch.toLowerCase())
        ).collect(Collectors.toList());
    }
}
