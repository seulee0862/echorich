package echorich.hr.jobs.dto;

import java.math.BigDecimal;

import echorich.hr.jobs.domain.Job;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobDTO {

	private String jobId;
	private String jobTitle;
	private BigDecimal minSalary;
	private BigDecimal maxSalary;

	public static JobDTO from (Job job) {
		return new JobDTO(job);
	}

	@Builder
	protected JobDTO(Job job) {
		this.jobId = job.getId();
		this.jobTitle = job.getJobTitle();
		this.minSalary = job.getMinSalary();
		this.maxSalary = job.getMaxSalary();
	}
}
