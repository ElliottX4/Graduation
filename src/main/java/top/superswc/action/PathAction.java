package top.superswc.action;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withUnauthorizedRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import top.superswc.DAO.CarportDeviceDao;
import top.superswc.DAO.SearchCarDeviceDao;
import top.superswc.bean.CarportDeviceBean;
import top.superswc.bean.PointBean;
import top.superswc.bean.SearchCarDeviceBean;
import top.superswc.service.PathService;

@Controller
@Scope("prototype")
@RequestMapping("/path")
public class PathAction {
	
	@Autowired
	private PathService pathService;
	private Gson gson = new Gson();
	private Map<String, Object> result = new HashMap<String, Object>();
	/*
	 * id是车位对应的id
	 * ip是寻车机的ip
	 */
	@RequestMapping("getPath")
	@ResponseBody
	public String getPath(int id,String ip){
		List<PointBean> list = pathService.getPath(id,ip);
		result.put("list", list);
		return gson.toJson(result);
	}
	@RequestMapping("savePath")
	@ResponseBody
	public String savePath(String list){
//		String list = "[\"-74.09667968750001;17.990112304687486\",\"94.84863281250003;28.732299804687493\",\"0.6103515625;-55.74035644531253\",\"0.6103515625;-55.74035644531253\"]";
		List<Double[]> pointList = new ArrayList<Double[]>();
		list = list.substring(1, list.length()-1);
		String[] lists =  list.split(",");
		for (String string : lists) {
			string = string.substring(1,string.length()-1);
			Double[] doubles = new Double[2];
			doubles[0] = Double.valueOf(string.split(";")[0]);
			doubles[1] = Double.valueOf(string.split(";")[1]);
			pointList.add(doubles);
		}
		try{
			pathService.savePath(pointList);
			result.put("result", "success");
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", "fail");
		}
		return gson.toJson(result);
	}
}
