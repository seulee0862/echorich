package echorich.hr.employees.service;

import org.springframework.stereotype.Service;

import echorich.common.ErrorCode;
import echorich.common.exception.customException.BusinessException;
import echorich.hr.countries.domain.Country;
import echorich.hr.department.dto.DepartmentDTO;
import echorich.hr.employees.domain.Employee;
import echorich.hr.employees.dto.EmployeeDTO;
import echorich.hr.employees.dto.response.EmployeeInfoResponse;
import echorich.hr.employees.repository.EmployeeRepository;
import echorich.hr.jobs.dto.JobDTO;
import echorich.hr.locations.domain.Location;
import echorich.hr.locations.dto.LocationDTO;
import echorich.hr.regions.domain.Region;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

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

	private Employee findEmployeeWithDetailsById(Long employeeId) {
		return employeeRepository.findEmployeeWithDetailsById(employeeId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EMPLOYEE));
	}
}
