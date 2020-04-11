package pl.kowalczyk.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kowalczyk.CRUD.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
