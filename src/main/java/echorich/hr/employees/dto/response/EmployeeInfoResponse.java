package echorich.hr.employees.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import echorich.hr.department.dto.DepartmentDTO;
import echorich.hr.employees.dto.EmployeeDTO;
import echorich.hr.goup_location.locations.dto.LocationDTO;
import echorich.hr.jobs.dto.JobDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmployeeInfoResponse {

	@JsonProperty("employee")
	private EmployeeDTO employeeDTO;
	@JsonProperty("department")
	private DepartmentDTO departmentDTO;
	@JsonProperty("job")
	private JobDTO jobDTO;
	@JsonProperty("location")
	private LocationDTO locationDTO;

	public static EmployeeInfoResponse of(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO, JobDTO jobDTO, LocationDTO locationDTO) {
		return new EmployeeInfoResponse(
			employeeDTO,
			departmentDTO,
			jobDTO,
			locationDTO
		);
	}

	@Builder
	private EmployeeInfoResponse(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO, JobDTO jobDTO,
		LocationDTO locationDTO) {
		this.employeeDTO = employeeDTO;
		this.departmentDTO = departmentDTO;
		this.jobDTO = jobDTO;
		this.locationDTO = locationDTO;
	}
}
