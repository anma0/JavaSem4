package svg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tag {
    private String name;
    private Map<String, String> attributes = new HashMap<>();
    private TagType type;


    public Tag(String name){
        this(name, TagType.OPEN_AND_CLOSE);
    }

    public Tag(String name, TagType type){
        this.name = name;
        this.type = type;
    }

    public void set(String attributeName, String attributeValue){
        attributes.put(attributeName, attributeValue);
    }

    public void get(String attributeName) {
        attributes.get(attributeName);
    }

    public TagType getType() {
        return type;
    }

    public Set<Map.Entry<String, String>> getEntrySet() {
        return attributes.entrySet();
    }

//    public Set<String> getAttributes() {
//        return attributes.keySet();
//    }

    public String getName() {
        return name;
    }
}
