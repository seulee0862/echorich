package echorich.hr.jobs.dto;

import java.util.Date;

import echorich.hr.jobs.domain.Job;
import echorich.hr.jobs.domain.JobHistory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobHistoryDTO extends JobDTO{

	private Date startDate;
	private Date endDate;

	public static JobHistoryDTO of(Job job, JobHistory jobHistory) {
		return jobHistoryDTOBuilder()
			.job(job)
			.jobHistory(jobHistory)
			.build();
	}

	@Builder(builderMethodName = "jobHistoryDTOBuilder")
	private JobHistoryDTO(Job job, JobHistory jobHistory) {
		super(job);
		this.startDate = jobHistory.getStartDate();
		this.endDate = jobHistory.getEndDate();
	}
}
