package echorich.hr.goup_location.locations.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import echorich.hr.goup_location.countries.domain.Country;
import echorich.hr.goup_location.countries.dto.CountryDTO;
import echorich.hr.goup_location.locations.domain.Location;
import echorich.hr.goup_location.regions.domain.Region;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LocationDTO extends CountryDTO {

	@JsonIgnore
	private Long locationId;
	private String streetAddress;
	private String postalCode;
	private String city;
	private String stateProvince;

	public static LocationDTO of(Location location, Country country, Region region) {
		return new LocationDTO(location, country, region);
	}

	@Builder
	private LocationDTO(Location location, Country country, Region region) {
		super(country, region);
		this.locationId = location.getId();
		this.streetAddress = location.getStreetAddress();
		this.postalCode = location.getPostalCode();
		this.city = location.getCity();
		this.stateProvince = location.getStateProvince();
	}
}
