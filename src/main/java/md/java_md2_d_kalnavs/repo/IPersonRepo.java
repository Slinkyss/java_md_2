package md.java_md2_d_kalnavs.repo;

import md.java_md2_d_kalnavs.Models.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepo extends CrudRepository<Person, Integer> {
}