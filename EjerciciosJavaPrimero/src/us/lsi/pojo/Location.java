package us.lsi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
	private String country;

	private String city;

	private String latitude;

	private String longitude;

	public String getCountry() {
		return country;
	}

	public Location() {
		super();
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location [country = " + country + ", city = " + city + ", latitude = " + latitude + ", longitude = "
				+ longitude + "]";
	}
}
