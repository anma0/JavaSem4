package patterns.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        String className = "patterns.reflection.A";
        Class<?> aClass = Class.forName(className);

        //ещё один способ получить ссылку на класс: String.class, это аналогично с Class.forName("java.lang.String")
        Class<String> stringClass = String.class;
        //То есть тип Class параметризован тем классом, который он представляет. Но при использовании Class.forName мы, когда пишем программу, не знаем, какой там будет тип, поэтому приходится писать Class<?>
        //или Class<? extends Object>

        //Итак, в переменной aClass мы храним информацию о классе A
        Object a1 = aClass.newInstance(); //outputs 'constructor 1'
        System.out.println(a1 instanceof  A); //true

        //вызовем другой конструктор
        Constructor<?>[] aConstructors = aClass.getConstructors();
        Constructor<?> secondConstructor = aConstructors[1];
        Object a2 = secondConstructor.newInstance("Nana");

        //System.out.println(((A) a2).getName()); //здесь надо заранее знать А
        Method getName = aClass.getMethod("getName");
        System.out.println(getName.invoke(a2)); //"Nana" :))

        Method sayHello = aClass.getMethod("sayHello", int.class); //метод sayHello с int аргументом
        sayHello.invoke(a2, 21); //a2.sayHello(21)

    }
}
