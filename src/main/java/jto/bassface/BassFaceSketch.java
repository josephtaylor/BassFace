package jto.bassface;

import hypermedia.net.UDP;
import jto.bassface.filter.Filter;
import jto.bassface.filter.Filters;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

/**
 * Created by joconnor on 5/3/15.
 */
public class BassFaceSketch extends PApplet {
    private UDP udp;
    private PImage leftovers;
    private PImage filteredImage;

    private int currentImageListIndex = 0;
    private int currentImageIndex = 0;
    private int previousMidiNote = 0;
    private List<List<PImage>> imageLists;
    private Filter currentFilter;

    private static class Range {
        public int low;
        public int high;
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{BassFaceSketch.class.getName()});
    }

    @Override
    public void setup() {
        size(800, 600);
        background(255);

        udp = new UDP(this, 5202);
        udp.listen(true);

        imageLists = new ImageLoader(this).loadImages();


        PImage first = imageLists.get(0).get(0);
        filteredImage = new PImage(first.width, first.height);
        leftovers = new PImage(first.width, first.height);
        leftovers.format = ARGB;
        leftovers.loadPixels();
        for (int i = 0; i < leftovers.pixels.length; i++) {
            leftovers.pixels[i] = color(255, 255, 255, 0);
        }
        leftovers.updatePixels();
    }

    public void receive(byte[] data, String ip, int port) {
        println((int) data[0]);
        int midiNote = (int) data[0];
        if (midiNote == previousMidiNote) {
            currentImageIndex = (int) random(imageLists.get(currentImageListIndex).size());
            return;
        }
        buildLeftovers(imageLists.get(currentImageListIndex).get(currentImageIndex));
        currentImageListIndex = (int) random(imageLists.size());
        currentImageIndex = (int) random(imageLists.get(currentImageListIndex).size());
        updateFilter();
        println("ListIndex: " + currentImageListIndex + "\t\tImageIndex: " + currentImageIndex);
    }

    @Override
    public void draw() {
        PImage currentImage = imageLists.get(currentImageListIndex).get(currentImageIndex);
        tint(255, 50);
        if (null != currentFilter) {
            image(filteredImage, 0, 0);
        } else {
            image(currentImage, 0, 0);
        }

        noTint();
        image(leftovers, 0, 0);

//        if (keyPressed) {
//            receive(new byte[]{(byte) ((int) random(0, 127))}, "127.0.0.1", 5202);
//        }
    }

//    public void mousePressed() {
//        saveFrame((int) random(Integer.MAX_VALUE) + ".png");
//    }

    private void buildLeftovers(PImage currentImage) {
        currentImage.loadPixels();
        leftovers.loadPixels();
        Range range = new Range();
        range.low = (int) random(0, 50);
        range.high = (int) random(80, 255);
        for (int i = 0; i < currentImage.pixels.length; i++) {
            if (brightness(currentImage.pixels[i]) > range.low &&
                    brightness(currentImage.pixels[i]) <= range.high) {
                leftovers.pixels[i] = currentImage.pixels[i] & 0x66FFFFFF;
            } else {
                leftovers.pixels[i] &= 0x00FFFFFF;
            }
        }
        leftovers.updatePixels();
    }

    private void updateFilter() {
        float rand = random(1);
        if (rand < 0.2f) {
            currentFilter = Filters.random();
            currentFilter.filter(filteredImage, imageLists.get(currentImageListIndex).get(currentImageIndex));
            return;
        }
        currentFilter = null;
    }
}
