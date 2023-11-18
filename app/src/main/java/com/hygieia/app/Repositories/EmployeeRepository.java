package com.hygieia.app.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hygieia.app.Models.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Integer>{


    Boolean existsByUserName(String name);

    List<Employee> findEmployeeByDepartmentId(long id);

    @Query(value = "SELECT * FROM employees WHERE department_id = ?1 AND emp_type_id = ?2", nativeQuery = true)
    List<Employee> findAllDoctors(long Departmentid, int roleId);

    
}
