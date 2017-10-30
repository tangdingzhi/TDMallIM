package com.td.bean;

public class ServerQuery extends BaseQuery {
	private String acc;

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	@Override
	public String toString() {
		return "ServerQuery [acc=" + acc + "]" + super.toString();
	}

}
