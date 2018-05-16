package top.superswc.DAO;

import java.util.List;

import top.superswc.bean.PointBean;

public interface PointDao {
	public List<PointBean> getAllPoint();
	public PointBean getPointById(int id);
	public PointBean getPointByXY(double x,double y);
	public void savePint(PointBean pointBean);
	public void UpdatePoint(PointBean pointBean);
}
