package echorich.hr.employees.domain;

import java.math.BigDecimal;

import echorich.common.domain.CreateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class EmployeeSalaryHistory extends CreateTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 8, scale = 2, nullable = false)
	private BigDecimal beforeSalary;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public static EmployeeSalaryHistory of(BigDecimal beforeSalary, Employee employee) {
		return new EmployeeSalaryHistory(beforeSalary, employee);
	}

	@Builder
	private EmployeeSalaryHistory(BigDecimal beforeSalary, Employee employee) {
		this.beforeSalary = beforeSalary;
		this.employee = employee;
	}
}
