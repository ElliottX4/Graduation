package top.superswc.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.superswc.bean.CarportDeviceBean;
import top.superswc.bean.SearchCarDeviceBean;

@Repository
public interface CarportDeviceDao {
	public void save(CarportDeviceBean carportDeviceBean);
	public List<SearchCarDeviceBean> searchForDevice(String carportNumber,String inOnline);
	public List<SearchCarDeviceBean> searchByNum(String carportNumber);
	public void deleteById(int id);
}
