package jto.bassface.filter;

import com.google.common.collect.Lists;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.Collections;
import java.util.List;

/**
 * Created by joconnor on 5/9/15.
 */
public class BlockShuffle extends AbstractFilter {

    public static final int X_DIVISIONS = 3;
    public static final int Y_DIVISIONS = 3;

    List<PImage> subImages;

    public BlockShuffle(PApplet parent) {
        super(parent);
    }

    public void filter(PImage dest, PImage source) {
        int blockWidth = source.width / X_DIVISIONS;
        int blockHeight = source.height / Y_DIVISIONS;
        setupSubImages(source, blockWidth, blockHeight);

        Collections.shuffle(subImages);


        int imageIndex = 0;
        for (int x = 0; x < source.width; x += blockWidth) {
            for (int y = 0; y < source.height; y += blockHeight) {
                System.out.println(x + " " + y);
                dest.set(x, y, subImages.get(imageIndex++));
            }
        }
    }

    public FilterType filterType() {
        return FilterType.STATIC;
    }

    private void setupSubImages(PImage source, int blockWidth, int blockHeight) {
        subImages = Lists.newArrayList();
        for (int x = 0; x < source.width; x += blockWidth) {
            for (int y = 0; y < source.height; y += blockHeight) {
                PImage square = new PImage(blockWidth, blockHeight);
                square.copy(source, x, y, blockWidth, blockHeight, 0, 0, blockWidth, blockHeight);
                subImages.add(square);
            }
        }
    }
}
