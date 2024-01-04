package echorich.hr.department.dto.request;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IncreseDepartmentSalaryRequest {

	@Parameter(description = "인상할 % 값, 범위(1.00 ~ 0.00)", example = "0.14")
	@NotNull
	@DecimalMin("0.00")
	@DecimalMax("1.00")
	private BigDecimal increasePct;
}
