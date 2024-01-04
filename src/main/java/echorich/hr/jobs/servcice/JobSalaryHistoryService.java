package echorich.hr.jobs.servcice;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import echorich.hr.jobs.domain.Job;
import echorich.hr.jobs.domain.JobSalaryHistory;
import echorich.hr.jobs.repository.JobSalaryHistoryRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class JobSalaryHistoryService {

	private final JobSalaryHistoryRepository jobSalaryHistoryRepository;

	@Transactional
	public void increaseJobSalary(Job job, BigDecimal increasePct) {
		BigDecimal minSalaryIncrease = job.getMinSalary().multiply(increasePct);
		BigDecimal maxSalaryIncrease = job.getMaxSalary().multiply(increasePct);
		BigDecimal newMinSalary = job.getMinSalary().add(minSalaryIncrease);
		BigDecimal newMaxSalary = job.getMaxSalary().add(maxSalaryIncrease);

		job.increseMinSalry(newMinSalary);
		job.increseMaxSalry(newMaxSalary);

		JobSalaryHistory jobSalaryHistory = JobSalaryHistory.of(
			job,
			job.getMinSalary().subtract(minSalaryIncrease),
			job.getMaxSalary().subtract(maxSalaryIncrease)
		);

		save(jobSalaryHistory);
	}

	@Transactional
	public JobSalaryHistory save(JobSalaryHistory jobSalaryHistory) {
		return jobSalaryHistoryRepository.save(jobSalaryHistory);
	}
}
