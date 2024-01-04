package echorich.hr.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import echorich.hr.jobs.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
