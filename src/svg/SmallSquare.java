package svg;

import java.util.Collections;
import java.util.List;

public class SmallSquare implements Shape{

    private List<Tag> tagList;

    @Override
    public List<Tag> getTags() {
        Tag rect = new Tag("rect");
        rect.set("x", "50");
        rect.set("y", "50");
        rect.set("width", "20");
        rect.set("height", "20");
        rect.set("style", "stroke:#0000ff; fill: #000000");
        tagList = Collections.singletonList(rect);
        return tagList;
    }
}
