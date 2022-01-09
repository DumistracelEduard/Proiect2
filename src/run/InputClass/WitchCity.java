package run.InputClass;

import java.util.*;

public class WitchCity {
    public static List<City> addScoreCity(Children children) {
        List<City> listCity = new ArrayList<>();
        int ok;
        for (Child child: children.getChildren()) {
            ok = 0;
            if(listCity.size() == 0) {
                City city = new City(child.getCity());
                city.addScore(child.getAverageScore());
                city.addChild(child);
                listCity.add(city);
            } else {
                for (City city: listCity) {
                    if (city.getName().equals(child.getCity())) {
                        city.addScore(child.getAverageScore());
                        city.addChild(child);
                        ok = 1;
                    }
                }
                if(ok == 0) {
                    City city = new City(child.getCity());
                    city.addScore(child.getAverageScore());
                    city.addChild(child);
                    listCity.add(city);
                }
            }
        }
        Comparator comparator = new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                if (Double.compare(o2.getScore(), o1.getScore()) == 0) {
                    return o1.getName().compareTo(o2.getName());
                }
                return Double.compare(o2.getScore(), o1.getScore());
            }
        };
        listCity.sort(comparator);
        return listCity;
    }
}
