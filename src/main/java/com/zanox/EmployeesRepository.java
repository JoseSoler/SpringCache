package com.zanox;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jsoler on 11.05.15.
 */
@Repository
public class EmployeesRepository {


    /**
     * Let's pretend that employees Map is our System of Records, that is,
     * the source of truth for the data.
     */
    private final Map<Integer, Employee> employees = new ConcurrentHashMap<>();


    @PostConstruct
    public void init() {
        saveEmployee(new Employee(1, "Mike", "Zuhlke", "Etwas Strasse, 2", 5));
        saveEmployee(new Employee(2, "Iryna", "Sribna", "Etwas Strasse, 4", 5));
        saveEmployee(new Employee(3, "Magda", "Moeller", "Etwas Strasse, 5", 5));
        saveEmployee(new Employee(4, "Jose", "Soler", "Etwas Strasse, 3", 5));

    }


    @Cacheable("employees")
    public Employee loadEmployee(final Integer id) throws DataAccessException {

        System.out.println("Cache Miss: Getting data from very slow resource (10 sec round-trip).");

        try {
            Thread.sleep(10000); //sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Notice please the stupid mix of optional and throwing exception
        Optional<Employee> retrievedEmployee = Optional.of(employees.get(id));
        if (!retrievedEmployee.isPresent()) {
            throw new DataAccessException("Unable to retrieve Employee with passed Id: " + id);
        } else {
            return retrievedEmployee.get();
        }
    }

    @CacheEvict(value = "employees", key = "#employee.id")
    public void saveEmployee(final Employee employee) throws DataAccessException {
        System.out.println("Saving employee to the SOR: " + employee.toString());
        employees.put(employee.getId(), employee);
    }
}
