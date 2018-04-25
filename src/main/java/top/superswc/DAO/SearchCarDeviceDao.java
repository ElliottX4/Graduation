package top.superswc.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.superswc.bean.SearchCarDeviceBean;
/*
 * 寻车机Dao
 */
@Repository
public interface SearchCarDeviceDao {
	public void save(SearchCarDeviceBean searchCarDeviceBean);
	public List<SearchCarDeviceBean> searchForDevice(String name,String ip,String isOnline);
	public SearchCarDeviceBean searchByIp(String ip);
	public void deleteById(int id);
}
