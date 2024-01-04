package echorich.hr.department.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IncreseDepartmentSalaryRequest {

	@NotNull
	@DecimalMin("0.00")
	@DecimalMax("1.00")
	private BigDecimal increasePct;
}
