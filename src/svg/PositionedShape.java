package svg;

import java.util.ArrayList;
import java.util.List;

public class PositionedShape implements Shape {
    private Shape shape;
    private int x;
    private int y;

    public PositionedShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    //При реализации метода getTags необходимо вернуть закрывающий и открывающий теги <g> c атрибутом transform, а между ними вставить те теги, которые возвращает метод getTags для исходной фигуры. Другими словами, PositionedShape оборачивает теги исходной фигуры в тег g (в группу) и сдвигает эту группу.
    @Override
    public List<Tag> getTags() {
        List<Tag> tagList = new ArrayList<>();
        Tag gOpen = new Tag("g", TagType.OPEN);
        gOpen.set("transform", "translate("+x+","+y+")");
        return tagList;
    }
}
