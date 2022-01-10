package run.InputClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class WitchCity {
    private WitchCity() { }
    /**
     * calculeaza pentru fiecare lista de children scorul
     * pentru fiecare oras si dupa ordoneaza orasele
     * @param children
     * @return
     */
    public static List<City> addScoreCity(final Children children) {
        List<City> listCity = new ArrayList<>();
        int ok;
        for (Child child: children.getChildren()) {
            ok = 0;
            if (listCity.size() == 0) {
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
                if (ok == 0) {
                    City city = new City(child.getCity());
                    city.addScore(child.getAverageScore());
                    city.addChild(child);
                    listCity.add(city);
                }
            }
        }
        Comparator comparator = new Comparator<City>() {
            @Override
            public int compare(final City o1, final City o2) {
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
