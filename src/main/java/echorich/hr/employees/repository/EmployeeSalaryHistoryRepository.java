package echorich.hr.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import echorich.hr.employees.domain.EmployeeSalaryHistory;

public interface EmployeeSalaryHistoryRepository extends JpaRepository<EmployeeSalaryHistory, Long> {
}
