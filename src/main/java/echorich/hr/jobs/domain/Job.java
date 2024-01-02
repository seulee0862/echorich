package echorich.hr.jobs.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@Column(name = "job_id", length = 10)
	private String id;

	@Column(nullable = false, length = 35)
	private String jobTitle;

	@Column(precision = 8, scale = 0)
	private BigDecimal minSalary;

	@Column(precision = 8, scale = 0)
	private BigDecimal maxSalary;
}
