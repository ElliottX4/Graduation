package top.superswc.action;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@Controller
@Scope("prototype")
@RequestMapping("/mainPicDeploy")
public class MainPicAction {
	
	private Gson gson = new Gson();
	private Map<String, Object> result = new HashMap<String, Object>();
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(@RequestParam(value = "imgFile",required = false)MultipartFile file) throws Exception{
		InputStream is = null;
		OutputStream os = null;
		byte[] temp = new byte[1024];
		try {
			is = file.getInputStream();
			int a = is.available();
			os = new FileOutputStream(new File(System.getProperty("project.url")+"\\pic\\mainPic.jpg"));
			int t;
			while((t = is.read(temp))!=-1){
				os.write(temp);
			}
			result.put("result", "success");
		} catch (IOException e) {
			e.printStackTrace();
			result.put("result", "fail");
		}finally{
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
		}
		return gson.toJson(result); 
	}
}
