package echorich.hr.department.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import echorich.common.response.ApiResponse;
import echorich.hr.department.dto.request.IncreseDepartmentSalaryRequest;
import echorich.hr.department.dto.response.DepartmentLocationResponse;
import echorich.hr.department.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping("/{id}/location")
	public ApiResponse<DepartmentLocationResponse> departmentLocation(@NotNull @PathVariable("id") Long departmentId) {
		return ApiResponse.success(departmentService.departmentLocationInfo(departmentId));
	}

	@PatchMapping("/{id}/increase-salary")
	public ApiResponse<Long> increaseDepartmentSalary(@NotNull @PathVariable("id") Long departmentId,
		@Valid @RequestBody IncreseDepartmentSalaryRequest request) {
		return ApiResponse.success(departmentService.increaseDepartmentSalary(departmentId, request));
	}
}
