package echorich.hr.jobs.domain;

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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class JobSalaryHistory extends CreateTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	@Column(precision = 8, scale = 0)
	private BigDecimal beforeMinSalary;

	@Column(precision = 8, scale = 0)
	private BigDecimal beforeMaxSalary;

	public static JobSalaryHistory of (Job job, BigDecimal beforeMinSalary, BigDecimal beforeMaxSalary) {
		return new JobSalaryHistory(job, beforeMinSalary, beforeMaxSalary);
	}

	@Builder
	private JobSalaryHistory(Job job, BigDecimal beforeMinSalary, BigDecimal beforeMaxSalary) {
		this.job = job;
		this.beforeMinSalary = beforeMinSalary;
		this.beforeMaxSalary = beforeMaxSalary;
	}
}
