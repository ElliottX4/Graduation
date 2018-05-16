$(function(){
	var map = new swcMap();
	var isImageExit = map.isImageExit("/GraduationProject/pic/mainPic.jpg");
	if(isImageExit==true){
		$("#listMap").click();
		var options = {
			sizeW: "2000",
			sizeH: "2000",//静态图片的长和高
			mapUrl: "../pic/mainPic.jpg",//图片的路径
			domId: "map"//div的id
		};
		map.initMap(options);//初始化地图
		map.drawPoint();
	}else{
		$("#map").html("<span style=\"font-size: 111px;\">请先上传主图</span>");
		$("#map").css("text-align","center");
	}
	var list=[]
	map._map.on("click",function(e){
		var position = e.coordinate;
		position = position[0]+";"+position[1];
		list.push(position);
	})
	map._map.on("dblclick",function(e){
		saveLine(list);
		list = [];
	})
})
function saveLine(list){
	var data = {
		list: JSON.stringify(list)
	};
	$.ajax({
	 	url: "/GraduationProject/path/savePath",
		type: "POST",
		dataType: "json",
		data: data,
		success: function(result){


		}
	})
}