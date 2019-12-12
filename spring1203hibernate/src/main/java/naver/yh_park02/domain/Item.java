package naver.yh_park02.domain;

import lombok.Data;
//getter, setter, toString, equals, hashcode 메소드 생성

public class Item {
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	private int price;
	private String manufacture;
	
	@Override
	public String toString() {
		return "Item [code=" + code + ", name=" + name + ", price=" + price + ", manufacture=" + manufacture + "]";
	}
}
