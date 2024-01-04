package echorich.hr.department.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import echorich.hr.department.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
