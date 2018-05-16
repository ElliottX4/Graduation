package top.superswc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.superswc.DAO.SearchCarDeviceDao;
import top.superswc.bean.SearchCarDeviceBean;

@Service
@Transactional
public class SearchCarDeviceService {
	
	@Autowired
	private SearchCarDeviceDao searchCarDeviceDao;
	
	public void save(SearchCarDeviceBean searchCarDeviceBean){
		searchCarDeviceDao.save(searchCarDeviceBean);
	}
	public List<SearchCarDeviceBean> searchForDevice(String name,String ip,int isOnline){
		if(name==null){
			name = "";
		}
		name = "%"+name+"%";
		if(ip==null){
			ip="";
		}
		ip = "%"+ip+"%";
		String isOnlineString;
		if(isOnline==2){
			isOnlineString = "%";
		}else{
			isOnlineString = String.valueOf(isOnline);
		}
		return searchCarDeviceDao.searchForDevice(name,ip,isOnlineString);
	}
	public void delete(int id){
		searchCarDeviceDao.deleteById(id);
	}
	public void update(SearchCarDeviceBean searchCarDeviceBean){
		searchCarDeviceDao.update(searchCarDeviceBean);
	}
}
