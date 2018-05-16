package top.superswc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import top.superswc.bean.SearchCarDeviceBean;
import top.superswc.service.SearchCarDeviceService;

@Controller
@Scope("prototype")
@RequestMapping("/searchCarDevice")
public class SearchCarDeviceAction {
	
	@Autowired
	private SearchCarDeviceService searchCarDeviceService;
	private Gson gson = new Gson();
	private Map<String, Object> result = new HashMap<String, Object>();
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(SearchCarDeviceBean searchCarDeviceBean){
		try{
			searchCarDeviceService.save(searchCarDeviceBean);
			result.put("result", "success");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
	@RequestMapping("/search")
	@ResponseBody
	public String searchForDevice(SearchCarDeviceBean searchCarDeviceBean){
		try {
			List<SearchCarDeviceBean> list = searchCarDeviceService.searchForDevice(
					searchCarDeviceBean.getName(),
					searchCarDeviceBean.getIp(),
					searchCarDeviceBean.getIsOnline());
			result.put("result", "success");
			result.put("data", list);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(SearchCarDeviceBean searchCarDeviceBean){
		try {
			searchCarDeviceService.delete(searchCarDeviceBean.getId());
			result.put("result", "success");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
	@RequestMapping("/saveSearchCarPosition")
	@ResponseBody
	public String saveSearchCarPosition(SearchCarDeviceBean searchCarDeviceBean){
		try {
			searchCarDeviceService.update(searchCarDeviceBean);
			result.put("result", "success");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
}
