# Рисование SVG

>>> \\ARTSRVBIG\disk_o\Java\Programming Tech II\src\ru\spbu\arts\semester\iv\patterns

В настройках программы будет фиксированный набор элементов.
Вспомогательные классы для рисования SVG
1. Класс SVG, содержит открытый PrintStream для рисования:
    
    ```
    SVG svg = new SVG("a.svg", 300, 300);
    svg.addTags(rect1);
    svg.addTags(rect2);
    svg.close();
    ```
    addTags - список тегов.  
    В конце обязательно будет вызвано закрытие, чтобы закрыть PrintStream()
    
2. Класс Tag - это описание одного тега

    - String name
    - attributesMap<String, String>
    - Tag() /конструктор/
    - set(String, String) /void/
    
    ```
    Tag rect1 = new Tag("rect");
    rect1.set("x", "200");
    rect1.set("y", "200");
    rect1.set("width", "10");
    rect1.set("height", "20");
    rect1.set("style", "stroke:#ff0000; fill: #0000ff");
    ```
    с помощью HashMap
    
3. Когда сделаем классы Tag и SVG, необходимо проверить, что можно создавать картинки с их помощью.
    
4. Класс Shape. Фигура. Изображение будет рисоваться из большого числа фигур. Какие именно фигуры будут использоваться для рисования, будет задаваться в настройках.  
 Этот класс должен иметь конструктор без аргументов.
 Он имеен метод getTags(), который возвращает массив тегов для рисования этой фигуры.
    
5. Подумаем над расположением фигур. Они должны быть случайным образом разбросаны по рисунку.  
Создадим класс PositionedShape - это фигура + её положение. При это он сам должен являться фигурой (extends Shape).  
Он имеет поля Shape shape, int x и int y. Для рисования он добавляет каждому тегу фигуры дополнительный параметр `transform` со значением `translate(30, 30)`. Где 30, 30 это х и у.
    
6. Добавим файл properties, где указывается
    * размер картинки
    * количество фигурок, которые надо нарисовать
    * набор фигурок для рисования
    * ...
    
7. Понадобится фабрика, чтобы создать фигурки по имени из описания в файле properties.
    
8. Понадобится адаптер, чтобы превращать png картинки в фигуры.
    
9. Понадобятся декораторы, чтобы рисовать рамки вокруг фигурок или как-то иначе их модифицировать.
    
10. ДАЛЕЕ у нас будет файл настроек, из которого программа будет читать данные. Сначала фон, размеры, что нарисовать.

11. (встроенный класс Properties, он сам будет разделять параметры и "вытаскивать" их) Затем там же в настройках фигуры можно задавать. Программа посмотрит на shape.red_circle=имя.класса.RedCircle

    Дальше можно будет в классы добавлять параметры, вроде класс Круг, а там уже задаются цвет, размер и т.д. Также повороты фигур. 

    Программа минимум: рисовать фигурками.
    
    