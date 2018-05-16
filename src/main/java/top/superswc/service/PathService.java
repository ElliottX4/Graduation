package top.superswc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.functors.WhileClosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.superswc.DAO.CarportDeviceDao;
import top.superswc.DAO.PointDao;
import top.superswc.DAO.SearchCarDeviceDao;
import top.superswc.bean.CarportDeviceBean;
import top.superswc.bean.PointBean;
import top.superswc.bean.SearchCarDeviceBean;
import top.superswc.tools.PathTool;

@Service
public class PathService {

	@Autowired
	PointDao pointDao;
	@Autowired
	SearchCarDeviceDao searchCarDeviceDao;
	@Autowired
	CarportDeviceDao carportDeviceDao;
	
	public void savePath(List<Double[]> list){
		//点位列表最后两个由于双击是重复的
		int id = -1;
		//每次画线的起点连入原有的无向图
		Double[] pointFirst = list.get(list.size()-2);
		PointBean pointBeanFirst = new PointBean(pointFirst[0], pointFirst[1]);
			List<PointBean> listAll = pointDao.getAllPoint();
			if(listAll!=null){
				double length = Double.MAX_VALUE;
				for(PointBean t : listAll){
					double tempFirstLength = PathTool.getLength(t, pointBeanFirst);
					if(tempFirstLength < length){
						length = tempFirstLength;
						id = t.getId();
					}
				}
			}
		for(int i = list.size()-2;i>=0;i--){
			Double[] point = list.get(i);
			PointBean pointBean = pointDao.getPointByXY(point[0], point[1]);
			if(pointBean != null){
				if(id!=-1){
					pointBean.setNextIds((pointBean.getNextIds()==null?"":pointBean.getNextIds()+",")+id);
				}
				pointDao.UpdatePoint(pointBean);
			}else{
				pointBean = new PointBean(point[0], point[1]);
				if(id!=-1){
					pointBean.setNextIds(String.valueOf(id));
				}
				pointDao.savePint(pointBean);
			}
			id = pointBean.getId();
		}
	}
	
	public List<PointBean> getPath(int id,String ip){
		List<PointBean> list = pointDao.getAllPoint();
		SearchCarDeviceBean searchCarDeviceBean = searchCarDeviceDao.searchByIp(ip);
		CarportDeviceBean carportDeviceBean = carportDeviceDao.searchById(id);
		//构造寻车机和车位对点
		PointBean startPointBean = new PointBean(searchCarDeviceBean.getX(),searchCarDeviceBean.getY());
		PointBean endPointBean = new PointBean(carportDeviceBean.getX(),carportDeviceBean.getY());
		//构造起点和终点
		int start = 0;
		int end = 0;
		double startLength = Double.MAX_VALUE;
		double endLength = Double.MAX_VALUE;
		//找到距离车位和寻车机最近的两个点作为起点和终点
		for(PointBean t : list){
			double tempStartLength = PathTool.getLength(t, startPointBean);
			double tempEndLength = PathTool.getLength(t, endPointBean);
			if(tempStartLength < startLength){
				startLength = tempStartLength;
				start = t.getId();
			}
			if(tempEndLength < endLength){
				endLength = tempEndLength;
				end = t.getId();
			}
		}
		List<PointBean> resultList = new ArrayList<PointBean>();
		resultList.add(endPointBean);
		getPathByDijkstra(resultList,start,end,list);
		resultList.add(startPointBean);
		return resultList;
	}
	public List<PointBean> getPathByDijkstra(List<PointBean> resultList,int startId,int endId,List<PointBean> list){
 		//对list中的点进行标号
		Map<Integer,PointBean> pointMap = new HashMap<Integer, PointBean>();
		Map<Integer,Integer> i2idMap = new HashMap<Integer, Integer>();
		Map<Integer,Integer> id2iMap = new HashMap<Integer, Integer>();
		for(int i = 0;i<list.size();i++){
			pointMap.put(list.get(i).getId(), list.get(i));
			i2idMap.put(i, list.get(i).getId());
			id2iMap.put(list.get(i).getId(),i);
		}
		int starti = id2iMap.get(startId);
		int endi = id2iMap.get(endId);
		//[x下标][y下标]=长度
		double[][] w = new double[list.size()][list.size()];
		//是否标号
		boolean[] flag = new boolean[list.size()];
		//点的顺序
		int[] queue = new int[list.size()];
		//初始化为-1
		for(int i = 0;i<list.size();i++){
			for(int j = 0;j<list.size();j++){
				w[i][j] = Double.MAX_VALUE;
				if(i==j){
					w[i][j] = 0;
				}
			}
			flag[i] = false;
			queue[i] = starti;
		}
		//填入长度数据
		for(PointBean pointX : list){
			int idx = pointX.getId();
			if(pointX.getNextIds()!=null){
				String[] strs = pointX.getNextIds().split(",");
				for(String s : strs){
					int idy = Integer.valueOf(s);
					w[id2iMap.get(idx)][id2iMap.get(idy)] = PathTool.getLength(pointX,pointMap.get(idy));
					w[id2iMap.get(idy)][id2iMap.get(idx)] = PathTool.getLength(pointX,pointMap.get(idy));
				}
			}
		}
		//遍历更新
		int lastIndex = starti;
		int index = starti;
		flag[starti] = true;
		//更新那一行的值
		for(int i = 0;i<list.size()-1;i++){
			double min = Double.MAX_VALUE;
			//找到行中最小的没有被标记过的值
			for(int j = 0;j<list.size();j++){
				if(flag[j]==false&&w[lastIndex][j]!=0&&w[lastIndex][j]<min){
					min = w[lastIndex][j];
					index = j;
				}
			}
			for(int j = 0;j<list.size();j++){
				w[index][j] = w[index][j]==Double.MAX_VALUE?Double.MAX_VALUE:w[index][j]+min;
				if(w[index][j]<w[lastIndex][j]){
					queue[j]=index;
				}else{
					w[index][j] = w[lastIndex][j];
				}
			}
			flag[index]=true;
			lastIndex = index;
		}
		int i = endi;
		while(i!=starti){
			resultList.add(pointMap.get(i2idMap.get(i)));
			i=queue[i];
		}
		resultList.add(pointMap.get(i2idMap.get(starti)));
		return resultList;
	}
}
