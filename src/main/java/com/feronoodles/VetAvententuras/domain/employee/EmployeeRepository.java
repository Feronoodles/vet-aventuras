package com.feronoodles.VetAvententuras.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {


}
