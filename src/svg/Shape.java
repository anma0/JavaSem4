package svg;

import java.util.List;

public interface Shape {
    List<Tag> getTags();

    default void draw(SVG svg){
        for (Tag tag : getTags()) {
            svg.addTag(tag);
        }
    }

}
