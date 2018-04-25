package top.superswc.bean;

public class PointBean {
	private int id;
	private double x;
	private double y;
	private String nextIds;
	
	public PointBean(){
		
	}
	public PointBean(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNextIds() {
		return nextIds;
	}
	public void setNextIds(String nextIds) {
		this.nextIds = nextIds;
	}
}
