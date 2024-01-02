package echorich.hr.department.domain;

import echorich.hr.locations.domain.Location;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "departments")
public class Department {

	@Id
	@Column(name = "department_id")
	private Long id;

	@Column(name = "manager_id")
	private Long employeeId;

	@Column(length = 30, nullable = false)
	private String departmentName;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
}
