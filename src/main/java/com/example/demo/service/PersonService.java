package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<Person> searchTask1() {
        List<Person> personList = personRepository.findTask1(true);
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(personList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
