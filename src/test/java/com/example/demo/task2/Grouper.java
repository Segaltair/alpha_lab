package com.example.demo.task2;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Grouper {
    public Map<String, List<NamedObject>> groupByName(Collection<NamedObject> objects) {
        return objects.stream()
                .collect(groupingBy(NamedObject::getName));
    }
}
