package jto.bassface.filter;

import processing.core.PApplet;

/**
 * Created by joconnor on 5/10/15.
 */
public abstract class AbstractFilter implements Filter {
    protected final PApplet parent;

    public AbstractFilter(final PApplet parent) {
        this.parent = parent;
    }
}
