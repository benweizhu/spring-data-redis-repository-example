package me.zeph.spring.data.redis.repo;

import me.zeph.spring.data.redis.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static me.zeph.spring.data.redis.model.Student.Gender;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByName(String name);

    List<Student> findByGender(Gender gender);

    List<Student> findByNameAndGender(String name, String gender);
}
