package jto.bassface;

import com.google.common.io.Resources;
import jto.bassface.filter.Filters;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

/**
 * Created by joconnor on 5/9/15.
 */
public class FilterTester extends PApplet {

    @Override
    public void setup() {
        size(800, 600);
        background(255);

        PImage image = loadImage(new File(Resources.getResource("re__DSC0035.JPG").getPath()).getAbsolutePath());
        PImage dest = new PImage(image.width, image.height);

        BassFaceSketch bassFaceSketch = new BassFaceSketch();
        bassFaceSketch.setImageWidth(image.width);
        bassFaceSketch.setImageHeight(image.height);
        Filters filters = new Filters(bassFaceSketch);

        filters.forName("RandomDots").filter(dest, image);
        //image(image, 0, 0);
        image(dest, 0, 0);
    }

    public static void main(String[] args) {
        PApplet.main(FilterTester.class.getName());
    }
}
