package com.example.demo.task2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Имеется интерфейс:
 * public interface NamedObject {
 * public String getName();
 * }
 * Необходимо написать класс Grouper с одним методом groupByName, который на входе получает коллекцию объектов,
 * реализующих интерфейс NamedObject и преобразует ее в Map с ключом – имя объекта, и значением – коллекция объектов с таким именем.
 */
class Task2Test {

    @Test
    void testGrouper() {
        Grouper grouper = new Grouper();
        List<NamedObject> request = List.of(
                namedObjectGenerate("qwe"),
                namedObjectGenerate("asd"),
                namedObjectGenerate("zxc"),
                namedObjectGenerate("qwe"),
                namedObjectGenerate("qwe")
        );

        // test
        Map<String, List<NamedObject>> response = grouper.groupByName(request);

        response.forEach((key, value) -> {
            System.out.println(key + value.size());
        });

        assertEquals(response.size(), 3);
        assertEquals(response.get("qwe").size(), 3);
        assertEquals(response.get("asd").size(), 1);
        assertEquals(response.get("zxc").size(), 1);
    }

    private NamedObject namedObjectGenerate(String name) {
        return () -> name;
    }
}
