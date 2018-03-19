package top.superswc.bean;

public class SearchCarDeviceBean {
	private int id;
	private String ip; 
	private String name;
	private double x;
	private double y;
	private int isOnline;
	private int isOnMap;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public int getIsOnMap() {
		return isOnMap;
	}
	public void setIsOnMap(int isOnMap) {
		this.isOnMap = isOnMap;
	}
	
}
