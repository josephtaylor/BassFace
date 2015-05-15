package jto.bassface.filter;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by joconnor on 5/13/15.
 */
public class ColorShiftBlocks extends AbstractFilter {

    private static final int BLOCK_SIZE = 3;

    private static final int[] BLOCK_VARIANCE = new int[BLOCK_SIZE];

    static {
        for(int i = 0; i < BLOCK_VARIANCE.length; i++) {
            BLOCK_VARIANCE[i] = (int) Math.random() * 20 + 10;
        }
    }

    public ColorShiftBlocks(PApplet parent) {
        super(parent);
    }

    public void filter(PImage dest, PImage source) {
        source.loadPixels();
        dest.loadPixels();
        int block = 0;
        for (int y = 0; y < source.height; y += (source.height / BLOCK_SIZE)) {
            dest.set(0, y, getFilteredSubImage(source, y, block));
            block++;
        }
        dest.updatePixels();
    }

    private PImage getFilteredSubImage(PImage source, int currentY, int block) {
        PImage result = new PImage(source.width, source.height / BLOCK_SIZE);
        result.copy(source, 0, currentY, source.width, source.height / BLOCK_SIZE, 0, 0, source.width, source.height / BLOCK_SIZE);
        result.loadPixels();
        int threshold = block == 0 ? 0 : 120 / block;
        for (int i = 0; i < result.pixels.length; i++) {
            if (parent.brightness(result.pixels[i]) < threshold) {
                result.pixels[i] = 0;
            }
        }
        result.updatePixels();
        return result;
    }

    public FilterType filterType() {
        return FilterType.MOVING;
    }
}
