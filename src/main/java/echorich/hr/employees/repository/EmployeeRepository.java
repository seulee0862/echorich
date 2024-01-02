package echorich.hr.employees.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import echorich.hr.employees.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e " +
		"JOIN FETCH e.department d " +
		"JOIN FETCH e.job j " +
		"JOIN FETCH d.location l " +
		"JOIN FETCH l.country c " +
		"JOIN FETCH c.region r " +
		"WHERE e.id = :employeeId")
	Optional<Employee> findEmployeeWithDetailsById(@Param("employeeId") Long employeeId);


}
