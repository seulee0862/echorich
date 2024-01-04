package echorich.hr.department.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import echorich.common.ErrorCode;
import echorich.common.exception.customException.BusinessException;
import echorich.hr.department.domain.Department;
import echorich.hr.department.dto.DepartmentDTO;
import echorich.hr.department.dto.request.IncreseDepartmentSalaryRequest;
import echorich.hr.department.dto.response.DepartmentLocationResponse;
import echorich.hr.department.respository.DepartmentRepository;
import echorich.hr.employees.domain.Employee;
import echorich.hr.employees.service.EmployeeSalaryHistoryService;
import echorich.hr.employees.service.EmployeeService;
import echorich.hr.goup_location.countries.domain.Country;
import echorich.hr.goup_location.locations.domain.Location;
import echorich.hr.goup_location.locations.dto.LocationDTO;
import echorich.hr.goup_location.regions.domain.Region;
import echorich.hr.jobs.domain.Job;
import echorich.hr.jobs.servcice.JobHistoryService;
import echorich.hr.jobs.servcice.JobSalaryHistoryService;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeService employeeService;
	private final JobHistoryService jobHistoryService;
	private final JobSalaryHistoryService jobSalaryHistoryService;
	private final EmployeeSalaryHistoryService employeeSalaryHistoryService;

	public DepartmentLocationResponse departmentLocationInfo(Long departmentId) {
		Department department = findbyId(departmentId);
		Location location = department.getLocation();
		Country country = department.getLocation().getCountry();
		Region region = department.getLocation().getCountry().getRegion();

		return DepartmentLocationResponse.of(
			DepartmentDTO.from(department),
			LocationDTO.of(location, country, region)
		);
	}

	@Transactional
	public Long increaseDepartmentSalary(Long departmentId, IncreseDepartmentSalaryRequest request) {
		BigDecimal increasePct = request.getIncreasePct();
		Job job = jobHistoryService.findOneJobHistoryByDepartmentId(departmentId)
			.getJob();
		List<Employee> employees = employeeService.findByDepartmentId(departmentId);

		jobSalaryHistoryService.increaseJobSalary(job, increasePct);
		employeeSalaryHistoryService.increaseEmployeesSalary(employees, increasePct);

		return departmentId;
	}

	private Department findbyId(Long departmentId) {
		return departmentRepository.findById(departmentId)
			.orElseThrow(() -> new BusinessException(ErrorCode.INVALID_DEPARTMENT_ID));
	}
}
