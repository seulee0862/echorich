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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	@Tag(name = "부서")
	@Operation(summary = "부서 위치 정보 조회 API")
	@GetMapping("/{id}/location")
	public ApiResponse<DepartmentLocationResponse> departmentLocation(@NotNull @PathVariable("id") @Parameter(description = "부서 ID", example = "10") Long departmentId) {
		return ApiResponse.success(departmentService.departmentLocationInfo(departmentId));
	}

	@Tag(name = "부서")
	@Operation(summary = "부서의 급여 인상 API",
	description = "직급 체계가 만들어지지 않은 부서에 대한 인상 불가"
		+ "인상할 percentage 만큼 부서, 사원의 급여의 인상, 인상되기전 급여에 대한 데이터를 저장")
	@PatchMapping("/{id}/increase-salary")
	public ApiResponse<Long> increaseDepartmentSalary(@NotNull @PathVariable("id") @Parameter(description = "부서 ID", example = "110") Long departmentId,
		@Valid @RequestBody IncreseDepartmentSalaryRequest request) {
		return ApiResponse.success(departmentService.increaseDepartmentSalary(departmentId, request));
	}
}
