package svg;

import java.io.PrintStream;
import java.util.Map;

public class SVG implements AutoCloseable{
    private String name;
    private PrintStream stream;

    public SVG(String name, int width, int height) throws Exception{
        this.name = name;
        stream = new PrintStream(name, "UTF-8");
        stream.println("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + width + "\" height=\"" + height + "\">");
    }

    public void close() {
        stream.println("</svg>");
        stream.close();
    }

    public void addTag(Tag attribute){
//        TagType type = attribute.getType();
        switch (attribute.getType()){
            case OPEN:
                stream.print("<" + attribute.getName() + " ");
                for (Map.Entry entry : attribute.getEntrySet())
                    stream.print(entry.getKey() + "=\"" + entry.getValue() + "\" ");
                stream.println(">");
                break;
            case CLOSE:
                stream.println("</" + attribute.getName() + ">");
                break;
            case OPEN_AND_CLOSE:
                stream.print("<" + attribute.getName() + " ");
                for (Map.Entry entry : attribute.getEntrySet())
                    stream.print(entry.getKey() + "=\"" + entry.getValue() + "\" ");
                stream.println("/>");
                break;
        }
    }
}
