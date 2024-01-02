package echorich.hr.department.dto;

import echorich.hr.department.domain.Department;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DepartmentDTO {

	private Long departmentId;
	private String departmentName;

	public static DepartmentDTO from (Department department) {
		return new DepartmentDTO(department.getId(), department.getDepartmentName());
	}

	@Builder
	private DepartmentDTO(Long departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
}
