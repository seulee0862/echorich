package echorich.hr.employees.dto;

import java.math.BigDecimal;
import java.util.Date;

import echorich.hr.employees.domain.Employee;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmployeeDTO {

	private Long employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private BigDecimal salary;
	private BigDecimal commissionPct;

	public static EmployeeDTO from (Employee employee) {
		return new EmployeeDTO(
			employee.getId(),
			employee.getFirstName(),
			employee.getLastName(),
			employee.getEmail(),
			employee.getPhoneNumber(),
			employee.getHireDate(),
			employee.getSalary(),
			employee.getCommissionPct()
		);
	}

	@Builder
	public EmployeeDTO(Long employeeId, String firstName, String lastName,
		String email, String phoneNumber, Date hireDate, BigDecimal salary, BigDecimal commissionPct) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.salary = salary;
		this.commissionPct = commissionPct;
	}
}
