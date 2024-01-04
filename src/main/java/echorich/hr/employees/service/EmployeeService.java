package echorich.hr.employees.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import echorich.common.ErrorCode;
import echorich.common.exception.customException.BusinessException;
import echorich.hr.department.dto.DepartmentDTO;
import echorich.hr.employees.domain.Employee;
import echorich.hr.employees.dto.EmployeeDTO;
import echorich.hr.employees.dto.response.EmployeeHistoryResponse;
import echorich.hr.employees.dto.response.EmployeeInfoResponse;
import echorich.hr.employees.repository.EmployeeRepository;
import echorich.hr.goup_location.countries.domain.Country;
import echorich.hr.goup_location.locations.domain.Location;
import echorich.hr.goup_location.locations.dto.LocationDTO;
import echorich.hr.goup_location.regions.domain.Region;
import echorich.hr.jobs.domain.JobHistory;
import echorich.hr.jobs.dto.JobDTO;
import echorich.hr.jobs.dto.JobHistoryDTO;
import echorich.hr.jobs.servcice.JobHistoryService;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final JobHistoryService jobHistoryService;

	public EmployeeInfoResponse employeeInfo (Long employeeId) {
		Employee employee = findEmployeeWithDetailsById(employeeId);
		Location location = employee.getDepartment().getLocation();
		Country country = employee.getDepartment().getLocation().getCountry();
		Region region = employee.getDepartment().getLocation().getCountry().getRegion();

		return EmployeeInfoResponse.of(
			EmployeeDTO.from(employee),
			DepartmentDTO.from(employee.getDepartment()),
			JobDTO.from(employee.getJob()),
			LocationDTO.of(location, country, region)
		);
	}

	public EmployeeHistoryResponse employeeHistoryInfo (Long employeeId) {
		if (!existById(employeeId)) {
			throw new BusinessException(ErrorCode.INVALID_EMPLOYEE_ID);
		}

		List<JobHistory> jobHistories = jobHistoryService.findByEmployeeId(employeeId);
		List<JobHistoryDTO> jobHistoryDTOS = jobHistories
			.stream()
			.map(jobHistory -> {
				return JobHistoryDTO.of(jobHistory.getJob(), jobHistory);
			})
			.toList();

		return EmployeeHistoryResponse.of(employeeId, jobHistoryDTOS);
	}

	private boolean existById(Long employeeId) {
		return employeeRepository.existsById(employeeId);
	}

	public List<Employee> findByDepartmentId(Long departmentId) {
		return employeeRepository.findByDepartmentId(departmentId);
	}

	public Employee findEmployeeWithDetailsById(Long employeeId) {
		return employeeRepository.findEmployeeWithDetailsById(employeeId)
			.orElseThrow(() -> new BusinessException(ErrorCode.INVALID_EMPLOYEE_ID));
	}
}
