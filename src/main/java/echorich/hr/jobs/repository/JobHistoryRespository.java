package echorich.hr.jobs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import echorich.hr.jobs.domain.JobHistory;

public interface JobHistoryRespository extends JpaRepository<JobHistory, Long> {

	@Query(""
		+ "SELECT jh "
		+ "FROM JobHistory jh "
		+ "JOIN FETCH jh.job "
		+ "WHERE jh.employee.id = :employeeId")
	List<JobHistory> findByEmployeeId(Long employeeId);
}
