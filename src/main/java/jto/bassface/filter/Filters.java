package jto.bassface.filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by joconnor on 5/9/15.
 */
public class Filters {

    private static final List<Filter> FILTERS;
    private static final int LIST_SIZE;


    static {
        FILTERS = Lists.newArrayList();
        FILTERS.add(new BlockShuffle());
        FILTERS.add(new BlockShifter());
        FILTERS.add(new LineShifter());

        LIST_SIZE = FILTERS.size();
    }

    public static Filter forName(String name) {
        for (Filter filter : FILTERS) {
            if (filter.getClass().getSimpleName().equals(name)) {
                return filter;
            }
        }
        throw new IllegalArgumentException("No filter exists with the name: " + name);
    }

    public static Filter get(int index) {
        return FILTERS.get(index);
    }

    public static Filter random() {
        return FILTERS.get((int) (Math.random() * (double) FILTERS.size()));
    }
}
