package top.superswc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.superswc.DAO.CarportDeviceDao;
import top.superswc.bean.CarportDeviceBean;
import top.superswc.bean.SearchCarDeviceBean;

@Service
@Transactional
public class CarportDeviceService {
	
	@Autowired
	private CarportDeviceDao carportDeviceDao;
	
	public void save(CarportDeviceBean carportDeviceBean,int startNum,int endNum){
		String carportNumber = carportDeviceBean.getCarportNumber();
		for(int i = startNum;i<=endNum;i++){
			CarportDeviceBean carportDevice = new CarportDeviceBean();
			carportDevice.setCarportNumber(carportNumber+i);
			carportDeviceDao.save(carportDevice);
		}
	}
	public List<CarportDeviceBean> searchForDevice(String carportNumber,int isOnline){
		String isOnlineString;
		if(isOnline==2){
			isOnlineString = "%";
		}else{
			isOnlineString = String.valueOf(isOnline);
		}
		if(carportNumber==null){
			carportNumber="";
		}
		return carportDeviceDao.searchForDevice(carportNumber+"%",isOnlineString);
	}
	public List<CarportDeviceBean> searchByNum(String carNumber){
		if(carNumber==null){
			carNumber="";
		}
		return carportDeviceDao.searchByNum("%"+carNumber+"%");
	}
	public void delete(int id){
		carportDeviceDao.deleteById(id);
	}
	public void update(CarportDeviceBean carportDeviceBean){
		carportDeviceDao.update(carportDeviceBean);
	}
	public void updateCarNumber(CarportDeviceBean carportDeviceBean){
		carportDeviceDao.updateCarNumber(carportDeviceBean);
	}
}
