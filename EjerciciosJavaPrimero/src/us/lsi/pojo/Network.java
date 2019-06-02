package us.lsi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Network {
	private License license;

	private String name;

	private Location location;

	private String href;

	private String id;

	private String source;

	private Station[] stations;

	public Network() {
		super();
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Station[] getStations() {
		return stations;
	}

	public void setStations(Station[] stations) {
		this.stations = stations;
	}

	@Override
	public String toString() {
		return "Network [license = " + license + ", name = " + name +  ", location = "
				+ location + ", href = " + href + ", id = " + id + ", source = " + source + ", stations = " + stations
				+ "]";
	}
}
