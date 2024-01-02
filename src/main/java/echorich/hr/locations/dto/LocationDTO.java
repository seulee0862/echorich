package echorich.hr.locations.dto;

import echorich.hr.countries.domain.Country;
import echorich.hr.countries.dto.CountryDTO;
import echorich.hr.locations.domain.Location;
import echorich.hr.regions.domain.Region;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LocationDTO extends CountryDTO {

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
