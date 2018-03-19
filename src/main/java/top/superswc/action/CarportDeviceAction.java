package top.superswc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import top.superswc.bean.CarportDeviceBean;
import top.superswc.bean.SearchCarDeviceBean;
import top.superswc.service.CarportDeviceService;
import top.superswc.service.SearchCarDeviceService;

@Controller
@Scope("prototype")
@RequestMapping("/carportDevice")
public class CarportDeviceAction {

	@Autowired
	private CarportDeviceService carportDeviceService;
	private Gson gson = new Gson();
	private Map<String, Object> result = new HashMap<String, Object>();
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(CarportDeviceBean carportDeviceBean,int startNum,int endNum){
		try{
			carportDeviceService.save(carportDeviceBean,startNum,endNum);
			result.put("result", "success");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
	@RequestMapping("/search")
	@ResponseBody
	public String searchForDevice(CarportDeviceBean carportDeviceBean){
		try {
			List<SearchCarDeviceBean> list = carportDeviceService.searchForDevice(
					carportDeviceBean.getCarportNumber(),
					carportDeviceBean.getIsOnline());
			result.put("result", "success");
			result.put("data", list);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
	@RequestMapping("/searchByCarNumber")
	@ResponseBody
	public String searchByCarNumber(CarportDeviceBean carportDeviceBean){
		//输入的可能是车牌也可能是车位号
		try {
			List<SearchCarDeviceBean> list = carportDeviceService.searchByNum(
					carportDeviceBean.getCarNumber());
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
	public String delete(CarportDeviceBean carportDeviceBean){
		try {
			carportDeviceService.delete(carportDeviceBean.getId());
			result.put("result", "success");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
}
