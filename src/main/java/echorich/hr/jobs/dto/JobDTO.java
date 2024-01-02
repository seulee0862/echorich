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
		return new JobDTO(
			job.getId(),
			job.getJobTitle(),
			job.getMinSalary(),
			job.getMaxSalary()
		);
	}

	@Builder
	public JobDTO(String jobId, String jobTitle, BigDecimal minSalary, BigDecimal maxSalary) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}
}
