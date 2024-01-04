package echorich.hr.goup_location.countries.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import echorich.hr.goup_location.countries.domain.Country;
import echorich.hr.goup_location.regions.domain.Region;
import echorich.hr.goup_location.regions.dto.RegionDTO;
import lombok.Getter;

@Getter
public class CountryDTO extends RegionDTO {

	@JsonIgnore
	private String id;
	private String countryName;

	protected CountryDTO(Country country, Region region) {
		super(region);
		this.id = country.getId();
		this.countryName = country.getCountryName();
	}
}
