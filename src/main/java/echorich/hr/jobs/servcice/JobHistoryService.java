package echorich.hr.jobs.servcice;

import java.util.List;

import org.springframework.stereotype.Service;

import echorich.common.ErrorCode;
import echorich.common.exception.customException.BusinessException;
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

	public List<JobHistory> findJobHistoriesByDepartmentId(Long departmentId) {
		return jobHistoryRespository.findJobHistoriesByDepartmentId(departmentId);
	}

	public JobHistory findOneJobHistoryByDepartmentId(Long departmentId) {
		return findJobHistoriesByDepartmentId(departmentId)
			.stream()
			.findFirst()
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_IN_DEPARTMENT));
	}
}
