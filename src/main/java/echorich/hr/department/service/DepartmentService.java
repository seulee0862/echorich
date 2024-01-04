package echorich.hr.department.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import echorich.common.ErrorCode;
import echorich.common.exception.customException.BusinessException;
import echorich.hr.department.domain.Department;
import echorich.hr.department.dto.DepartmentDTO;
import echorich.hr.department.dto.response.DepartmentLocationResponse;
import echorich.hr.department.respository.DepartmentRepository;
import echorich.hr.goup_location.countries.domain.Country;
import echorich.hr.goup_location.locations.domain.Location;
import echorich.hr.goup_location.locations.dto.LocationDTO;
import echorich.hr.goup_location.regions.domain.Region;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentLocationResponse departmentLocationInfo(Long departmentId) {
		Department department = findbyId(departmentId);
		Location location = department.getLocation();
		Country country = department.getLocation().getCountry();
		Region region = department.getLocation().getCountry().getRegion();

		return DepartmentLocationResponse.of(
			DepartmentDTO.from(department),
			LocationDTO.of(location, country, region)
		);
	}

	private Department findbyId(Long departmentId) {
		return departmentRepository.findById(departmentId)
			.orElseThrow(() -> new BusinessException(ErrorCode.INVALID_DEPARTMENT_ID));
	}
}
