package top.superswc.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.superswc.bean.CarportDeviceBean;
import top.superswc.bean.SearchCarDeviceBean;
/*
 * 车位探测器Dao
 */
@Repository
public interface CarportDeviceDao {
	public void save(CarportDeviceBean carportDeviceBean);
	public List<CarportDeviceBean> searchForDevice(String carportNumber,String inOnline);
	public List<CarportDeviceBean> searchByNum(String carportNumber);
	public CarportDeviceBean searchById(int id);
	public void deleteById(int id);
	public void update(CarportDeviceBean carportDeviceBean);
	public void updateCarNumber(CarportDeviceBean carportDeviceBean);
}
