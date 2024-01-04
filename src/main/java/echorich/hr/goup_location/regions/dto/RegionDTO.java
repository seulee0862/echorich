package echorich.hr.goup_location.regions.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import echorich.hr.goup_location.regions.domain.Region;
import lombok.Getter;

@Getter
public class RegionDTO {

	@JsonIgnore
	private Long regionId;
	private String regionName;

	protected RegionDTO(Region region) {
		this.regionId = region.getId();
		this.regionName = region.getRegionName();
	}
}