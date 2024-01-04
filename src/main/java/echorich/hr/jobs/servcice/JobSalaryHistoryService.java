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
		BigDecimal nowMinSalary = job.getMinSalary();
		BigDecimal nowMaxSalary = job.getMaxSalary();
		BigDecimal minSalaryIncrease = nowMinSalary.multiply(increasePct);
		BigDecimal maxSalaryIncrease = nowMaxSalary.multiply(increasePct);

		job.increseMinSalry(nowMinSalary.add(minSalaryIncrease));
		job.increseMaxSalry(nowMaxSalary.add(maxSalaryIncrease));

		JobSalaryHistory jobSalaryHistory = JobSalaryHistory.of(
			job,
			nowMinSalary,
			nowMaxSalary
		);

		save(jobSalaryHistory);
	}

	@Transactional
	public JobSalaryHistory save(JobSalaryHistory jobSalaryHistory) {
		return jobSalaryHistoryRepository.save(jobSalaryHistory);
	}
}
