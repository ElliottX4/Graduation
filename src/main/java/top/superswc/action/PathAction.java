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

import top.superswc.bean.PointBean;
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
}
