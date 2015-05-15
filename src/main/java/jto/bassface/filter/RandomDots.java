package jto.bassface.filter;

import jto.bassface.BassFaceSketch;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Created by joconnor on 5/10/15.
 */
public class RandomDots extends AbstractFilter {
    private static final int NUM_DOTS = 4000;
    private PGraphics graphics;

    public RandomDots(PApplet parent) {
        super(parent);
        if (parent instanceof BassFaceSketch) {
            BassFaceSketch sketch = (BassFaceSketch) parent;
            graphics = parent.createGraphics(sketch.getImageWidth(), sketch.getImageHeight());
        }
    }

    public void filter(PImage dest, PImage source) {
        source.loadPixels();
        graphics.beginDraw();
        graphics.noStroke();
        for (int i = 0; i < NUM_DOTS; i++) {
            int x = (int) parent.random(source.width);
            int y = (int) parent.random(source.height);
            graphics.fill(source.pixels[x + y * source.width]);
            int r = (int) parent.random(2, 10);
            graphics.ellipse(x, y, r, r);
        }
        graphics.endDraw();
        dest.set(0, 0, graphics.get());
    }

    public FilterType filterType() {
        return FilterType.MOVING_RANDOM;
    }
}
