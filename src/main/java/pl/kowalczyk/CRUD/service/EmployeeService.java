package pl.kowalczyk.CRUD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kowalczyk.CRUD.model.Employee;
import pl.kowalczyk.CRUD.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        employeeRepository.save(new Employee("Jan", "Kowalski", "jan.kowalski@gmail.com"));
        employeeRepository.save(new Employee("Dorota", "Welman", "dorotkaaa@interia.pl"));
        employeeRepository.save(new Employee("Michał", "Brodnicki", "michalo22@poczta.fm"));
        employeeRepository.save(new Employee("Mariusz", "Majka", "mariuszmajka@wp.pl"));
        employeeRepository.save(new Employee("Maksymilian", "Riznyk", "maks2233@poczta.onet.pl"));
    }

    public List<Employee> getAllEmployees() {
        List<Employee> allEmployeesList = employeeRepository.findAll();

        if(allEmployeesList.size() > 0) {
            return allEmployeesList;
        } else {
            return new ArrayList<>();
        }
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            return new Employee("NULL","NULL","NULL");
        }
    }

    public Employee createOrUpdateEmployee(Employee entity) {
        if(entity.getId()  == null) {
            entity = employeeRepository.save(entity);
            return entity;
        } else {
            Optional<Employee> employee = employeeRepository.findById(entity.getId());

            if(employee.isPresent()) {
                Employee newEntity = employee.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());

                newEntity = employeeRepository.save(newEntity);

                return newEntity;
            } else {
                entity = employeeRepository.save(entity);

                return entity;
            }
        }
    }

    public void deleteEmployeeById(Long id)
    {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()) {
            employeeRepository.deleteById(id);
        }
    }
}
