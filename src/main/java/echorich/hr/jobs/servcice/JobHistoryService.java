package echorich.hr.jobs.servcice;

import java.util.List;

import org.springframework.stereotype.Service;

import echorich.hr.jobs.domain.JobHistory;
import echorich.hr.jobs.repository.JobHistoryRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JobHistoryService {

	private final JobHistoryRespository jobHistoryRespository;

	public List<JobHistory> findByEmployeeId (Long employeeId) {
		return jobHistoryRespository.findByEmployeeId(employeeId);
	}
}
