package us.lsi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OneNetwork {
	
	private Network network;

	public OneNetwork() {
		super();
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	@Override
	public String toString() {
		return "Sevici [network = " + network + "]";
	}
}
