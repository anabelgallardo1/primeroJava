package us.lsi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Extra {
	private String uid;

	private String slots;

	private String address;

	private String bonus;

	private String last_update;

	private String banking;

//	private String status;

	public Extra() {
		super();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSlots() {
		return slots;
	}

	public void setSlots(String slots) {
		this.slots = slots;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public String getBanking() {
		return banking;
	}

	public void setBanking(String banking) {
		this.banking = banking;
	}

//	public String getStatus() {
//		return status;
//	}

//	public void setStatus(String status) {
//		this.status = status;
//	}

	@Override
	public String toString() {
		return "Extra [uid = " + uid + ", slots = " + slots + ", address = " + address + ", bonus = " + bonus
				+ ", last_update = " + last_update + ", banking = " + banking +  "]";
	}
}
