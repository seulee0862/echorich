package echorich.hr.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import echorich.hr.jobs.domain.JobSalaryHistory;

public interface JobSalaryHistoryRepository extends JpaRepository<JobSalaryHistory, Long> {
}
