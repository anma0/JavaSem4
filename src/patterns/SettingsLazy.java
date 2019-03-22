package patterns;

public class SettingsLazy {
    //пример ленивого синглетона
    //это синглетон, который создаёт инстанс(единственный объект класса) только при первом запросе на этот объект.
    //Это нужно, если объект дорого создавать, а нужен он далеко не всегда.

    private static SettingsLazy instance = null;

    private static SettingsLazy getInstance() {
        //создать объект, если он ещё не создан
        if (instance == null)
            instance = new SettingsLazy();
        return instance;
    }

    private SettingsLazy(){
        //дорогое и долгое создание объекта
    }

    //SettingsLazy в использовании аналогичен Settings. Но первый не создаёт объект, если объект не нужен.

    //такая реализация не потокобезопасна
    //для безопасности можно сделать synchronize и двойную проверку, как была в обезьянках.
    //либо можно сделать enum версию (см.интернет)

}
