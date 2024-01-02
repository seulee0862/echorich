package echorich.hr.locations.domain;

import echorich.hr.countries.domain.Country;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "locations")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long id;

	@Column(length = 40)
	private String streetAddress;

	@Column(length = 12)
	private String postalCode;

	@Column(length = 30, nullable = false)
	private String city;

	@Column(length = 25)
	private String stateProvince;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
}
