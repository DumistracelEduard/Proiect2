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
                        city.addChild(child);
                        city.addScore(child.getAverageScore());
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

        Collections.sort(listCity, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return Double.compare(o1.getScore(), o2.getScore());
            }
        });
        return listCity;
    }
}
