package jto.bassface.filter;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by joconnor on 5/13/15.
 */
public class ColorShift extends AbstractFilter {

    public ColorShift(PApplet parent) {
        super(parent);
    }

    public void filter(PImage dest, PImage source) {
        source.loadPixels();
        dest.loadPixels();
        for (int i = 0; i < source.pixels.length; i++) {
            int currentColor = source.pixels[i];
            float red = ((int) parent.red(currentColor) + 5) % 256;
            float green = ((int) parent.green(currentColor) + 3) % 256;
            float blue = ((int) parent.blue(currentColor) + 10) % 256;
            dest.pixels[i] = parent.color(red, green, blue);
        }
        dest.updatePixels();
    }

    public FilterType filterType() {
        return FilterType.MOVING;
    }
}
