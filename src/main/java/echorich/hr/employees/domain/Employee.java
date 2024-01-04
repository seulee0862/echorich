package echorich.hr.employees.domain;

import java.math.BigDecimal;
import java.util.Date;

import echorich.hr.department.domain.Department;
import echorich.hr.jobs.domain.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

@Getter
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column(name = "employee_id")
	private Long id;

	@Column(length = 20)
	private String firstName;

	@Column(length = 25, nullable = false)
	private String lastName;

	@Column(length = 25, nullable = false)
	private String email;

	@Column(length = 20)
	private String phoneNumber;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date hireDate;

	@Column(precision = 8, scale = 2, nullable = false)
	private BigDecimal salary;

	@Column(precision = 2, scale = 2)
	private BigDecimal commissionPct;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public void updateSalary(BigDecimal newSalary) {
		this.salary = newSalary;
	}
}
