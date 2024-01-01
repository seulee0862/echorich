package echorich.hr.countries.domain;

import echorich.hr.regions.domain.Region;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "countries")
public class Country {

	@Id
	@Column(name = "country_id", length = 2)
	private String id;

	@Column(name = "country_name", length = 40)
	private String countryName;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
}
