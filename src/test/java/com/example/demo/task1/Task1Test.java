package com.example.demo.task1;

import com.example.demo.entity.Document;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

/**
 * Напишите программу, которая находит и печатает в консоли данные (ФИО гражданина, тип документа и номер документа)
 * всех граждан и их документов, у которых в номере документа есть «777». Должна выводиться информация только по активным документам.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
public class Task1Test {
    @Container
    private static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withInitScript("task1/init_script.sql");

    @DynamicPropertySource
    public static void overrideContainerProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.schema", () -> "test");
        dynamicPropertyRegistry.add("spring.jpa.hibernate.naming.physical-strategy", () -> "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
    }

    @Autowired
    private PersonService personService;

    @Test
    public void test() {
        List<Person> personList = personService.searchTask1();

        Assertions.assertEquals(personList.size(), 2);
        List<Document> documents = personList.stream().flatMap(p -> p.getDocuments().stream()).toList();
        Assertions.assertEquals(documents.size(), 3);
    }
}
