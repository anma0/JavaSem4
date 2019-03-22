package patterns.adapter;

public class Car {
    private String name;

    public Car(String name){
        this.name = name;
    }

    public String go() {
        return "brrrrrrrrr, I'm " + name;
    }

    //Alt + Ins
    public String getName() {
        return name;
    }


}
