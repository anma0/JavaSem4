package svg;

import java.util.Collections;
import java.util.List;

public class RedCircle implements Shape {
    private List<Tag> tagList;

    @Override
    public List<Tag> getTags() {
        Tag circle = new Tag("circle");
        circle.set("cx", "20");
        circle.set("cy", "20");
        circle.set("r", "10");
        circle.set("style", "fill: #ff0000");
        tagList = Collections.singletonList(circle);
        return tagList;
    }
}
