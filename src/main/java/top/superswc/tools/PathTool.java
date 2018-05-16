package top.superswc.tools;

import top.superswc.bean.PointBean;

public class PathTool {
	public static double getLength(PointBean a,PointBean b){
		double x2 = Math.pow(a.getX()-b.getX(), 2);
		double y2 = Math.pow(a.getY()-b.getY(), 2);
		return Math.sqrt(Math.abs(x2+y2));	
	}
}
