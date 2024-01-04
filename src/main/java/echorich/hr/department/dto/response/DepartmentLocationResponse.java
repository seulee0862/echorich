package echorich.hr.department.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import echorich.hr.department.dto.DepartmentDTO;
import echorich.hr.goup_location.locations.dto.LocationDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DepartmentLocationResponse {

	@JsonProperty("department")
	private DepartmentDTO departmentDTO;
	@JsonProperty("departmentLocation")
	private LocationDTO locationDTO;

	public static DepartmentLocationResponse of(DepartmentDTO departmentDTO, LocationDTO locationDTO) {
		return new DepartmentLocationResponse(departmentDTO, locationDTO);
	}

	@Builder
	protected DepartmentLocationResponse(DepartmentDTO departmentDTO, LocationDTO locationDTO) {
		this.departmentDTO = departmentDTO;
		this.locationDTO = locationDTO;
	}
}
