package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query(value = "SELECT p " +
            "FROM Person p " +
            "join fetch p.documents pd " +
            "where pd.documentNumber like '%777%' and pd.isActive = :isActive")
    List<Person> findTask1(@Param("isActive") Boolean isActive);

    @Query(value = "SELECT p FROM Person p " +
            "where p.lastName like :name% and p.id = :id")
    List<Person> findAllByIdAndName(@Param("id") Integer id, @Param("name") String name);
}
