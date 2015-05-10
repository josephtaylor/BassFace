package jto.bassface.filter;

import processing.core.PImage;

public interface Filter {
    void filter(PImage dest, PImage source);
}
