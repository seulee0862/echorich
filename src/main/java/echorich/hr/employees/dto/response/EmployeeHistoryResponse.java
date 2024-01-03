package echorich.hr.employees.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import echorich.hr.jobs.dto.JobHistoryDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmployeeHistoryResponse {

	private final Long employeeId;
	@JsonProperty("JobHistories")
	private final List<JobHistoryDTO> jobHistoryDTOS;

	public static EmployeeHistoryResponse of(Long employeeId, List<JobHistoryDTO> jobHistoryDTOS) {
		return new EmployeeHistoryResponse(employeeId, jobHistoryDTOS);
	}

	@Builder
	private EmployeeHistoryResponse(Long employeeId, List<JobHistoryDTO> jobHistoryDTOS) {
		this.employeeId = employeeId;
		this.jobHistoryDTOS = jobHistoryDTOS;
	}
}
