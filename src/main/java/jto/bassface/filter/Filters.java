package jto.bassface.filter;

import com.google.common.collect.Lists;
import processing.core.PApplet;

import java.util.List;

/**
 * Created by joconnor on 5/9/15.
 */
public class Filters {

    private final List<Filter> filters;
    private final int listSize;


    public Filters(PApplet parent) {
        filters = Lists.newArrayList();
        filters.add(new BlockShuffle(parent));
        filters.add(new BlockShifter(parent));
        filters.add(new LineShifter(parent));
        filters.add(new RandomDots(parent));

        listSize = filters.size();
    }

    public Filter forName(String name) {
        for (Filter filter : filters) {
            if (filter.getClass().getSimpleName().equals(name)) {
                return filter;
            }
        }
        throw new IllegalArgumentException("No filter exists with the name: " + name);
    }

    public Filter get(int index) {
        return filters.get(index);
    }

    public Filter random() {
        return filters.get((int) (Math.random() * (double) filters.size()));
    }
}
