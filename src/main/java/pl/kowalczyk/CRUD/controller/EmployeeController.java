package pl.kowalczyk.CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kowalczyk.CRUD.model.Employee;
import pl.kowalczyk.CRUD.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllEmployees(Model model)
    {
        List<Employee> list = service.getAllEmployees();

        model.addAttribute("employees", list);
        return "list-employees";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Employee entity = service.getEmployeeById(id.get());
            model.addAttribute("employee", entity);
        } else {
            model.addAttribute("employee", new Employee());
        }
        return "add-update-employees";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) {
        service.deleteEmployeeById(id);
        return "redirect:/";
    }

    @PostMapping(path = "/createEmployee")
    public String createOrUpdateEmployee(Employee employee) {
        service.createOrUpdateEmployee(employee);
        return "redirect:/";
    }
}
