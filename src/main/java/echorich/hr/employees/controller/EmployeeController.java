package echorich.hr.employees.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import echorich.common.response.ApiResponse;
import echorich.hr.employees.dto.response.EmployeeHistoryResponse;
import echorich.hr.employees.dto.response.EmployeeInfoResponse;
import echorich.hr.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Tag(name = "사원")
	@Operation(summary = "특정 사원 정보 조회 API",
	description = "존재하지 않는 사원 조회 불가")
	@GetMapping("/{id}")
	public ApiResponse<EmployeeInfoResponse> employeeInfo(@NotNull @PathVariable("id") @Parameter(description = "사원ID", example = "205") Long employeeId) {
		return ApiResponse.success(employeeService.employeeInfo(employeeId));
	}

	@Tag(name = "사원")
	@Operation(summary = "특정 사원 이력 조회 API",
		description = "존재하지 않는 사원 조회 불가")
	@GetMapping("/{id}/history")
	public ApiResponse<EmployeeHistoryResponse> employeeHistoryInfo(@NotNull @PathVariable("id") @Parameter(description = "사원ID", example = "101")Long employeeId) {
		return ApiResponse.success(employeeService.employeeHistoryInfo(employeeId));
	}
}
