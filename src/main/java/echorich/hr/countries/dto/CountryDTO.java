package echorich.hr.countries.dto;

import echorich.hr.countries.domain.Country;
import echorich.hr.regions.domain.Region;
import echorich.hr.regions.dto.RegionDTO;
import lombok.Getter;

@Getter
public class CountryDTO extends RegionDTO {

	private String id;
	private String countryName;

	protected CountryDTO(Country country, Region region) {
		super(region);
		this.id = country.getId();
		this.countryName = country.getCountryName();
	}
}
