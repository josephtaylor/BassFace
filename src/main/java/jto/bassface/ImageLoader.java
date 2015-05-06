package jto.bassface;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.List;

/**
 * Created by joconnor on 5/3/15.
 */
public class ImageLoader {

    private final PApplet parent;

    public ImageLoader(final PApplet parent) {
        this.parent = parent;
    }

    public List<List<PImage>> loadImages() {
        List<List<PImage>> imageLists = Lists.newArrayList();
        File mainImageDir = new File(Resources.getResource("img").getPath());
        for (File imageDir : mainImageDir.listFiles()) {
            parent.println("loading images in " + imageDir.getName());
            imageLists.add(loadList(imageDir));
        }
        return imageLists;
    }

    private List<PImage> loadList(File dir) {
        List<PImage> images = Lists.newArrayList();
        for (File image : dir.listFiles()) {
            images.add(parent.loadImage(image.getAbsolutePath()));
            parent.println("loaded " + image.getName());
        }
        return images;
    }
}
