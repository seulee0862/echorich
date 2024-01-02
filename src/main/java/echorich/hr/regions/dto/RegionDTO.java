package echorich.hr.regions.dto;

import echorich.hr.regions.domain.Region;
import lombok.Getter;

@Getter
public class RegionDTO {

	private Long regionId;
	private String regionName;

	protected RegionDTO(Region region) {
		this.regionId = region.getId();
		this.regionName = region.getRegionName();
	}
}