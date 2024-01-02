package echorich.hr.employees.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import echorich.common.response.ApiResponse;
import echorich.hr.employees.dto.response.EmployeeInfoResponse;
import echorich.hr.employees.service.EmployeeService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("/{id}")
	public ApiResponse<EmployeeInfoResponse> employeeInfo(@NotNull @PathVariable("id") Long employeeId) {
		return ApiResponse.success(employeeService.employeeInfo(employeeId));
	}

}
