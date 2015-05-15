package jto.bassface;

import com.google.common.io.Resources;
import jto.bassface.filter.Filter;
import jto.bassface.filter.FilterType;
import jto.bassface.filter.Filters;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

/**
 * Created by joconnor on 5/9/15.
 */
public class FilterTester extends PApplet {

    Filters filters;
    PImage image;
    PImage dest;
    Filter filter;

    @Override
    public void setup() {
        size(800, 600);
        background(255);

        image = loadImage(new File(Resources.getResource("re__DSC0035.JPG").getPath()).getAbsolutePath());
        dest = new PImage(image.width, image.height);

        filters = new Filters(this);
        filter = filters.forName("ColorShiftBlocks");

        filter.filter(dest, image);
        //image(image, 0, 0);
        image(dest, 0, 0);
    }

    public void draw() {
        if (FilterType.MOVING.equals(filter.filterType())) {
            filter.filter(dest, dest);
            image(dest, 0, 0);
        }
    }

    public static void main(String[] args) {
        PApplet.main(FilterTester.class.getName());
    }
}
