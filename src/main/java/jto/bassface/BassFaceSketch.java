package jto.bassface;

import hypermedia.net.UDP;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

/**
 * Created by joconnor on 5/3/15.
 */
public class BassFaceSketch extends PApplet {
    private UDP udp;

    private int currentImageListIndex = 0;
    private int currentImageIndex = 0;
    private int previousMidiNote = 0;
    private List<List<PImage>> imageLists;

    public static void main(String[] args) {
        PApplet.main(new String[]{BassFaceSketch.class.getName()});
    }

    @Override
    public void setup() {
        size(800, 600);

        udp = new UDP(this, 5202);
        udp.listen(true);

        imageLists = new ImageLoader(this).loadImages();
    }

    public void receive(byte[] data, String ip, int port) {
        println((int) data[0]);
        int midiNote = (int) data[0];
        if (midiNote == previousMidiNote) {
            currentImageIndex = (int) random(imageLists.get(currentImageListIndex).size());
            return;
        }
        currentImageListIndex = (int) random(imageLists.size());
        currentImageIndex = (int) random(imageLists.get(currentImageListIndex).size());
        println("ListIndex: " + currentImageListIndex + "\t\tImageIndex: " + currentImageIndex);
    }

    @Override
    public void draw() {
        image(imageLists.get(currentImageListIndex).get(currentImageIndex), 0, 0);

        if (random(1) < 0.08f) {
            receive(new byte[]{(byte) ((int) random(0, 127))}, "127.0.0.1", 5202);
        }
    }
}
