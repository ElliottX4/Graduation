$(function(){
	var map = new swcMap();
	var isImageExit = map.isImageExit("/GraduationProject/pic/mainPic.jpg");
	if(isImageExit==true){
		$("#listMap").click();
		var options = {
			sizeW: "500",
			sizeH: "500",//静态图片的长和高
			mapUrl: "../pic/mainPic.jpg",//图片的路径
			domId: "map"//div的id
		};
		map.initMap(options);//初始化地图
	}else{
		$("#map").html("<span style=\"font-size: 111px;\">请先上传主图</span>");
		$("#map").css("text-align","center");
	}
	searchDevice();
});
function searchDevice(){
	var data ={isOnline:"2"};
	$.ajax({
		url: "/GraduationProject/searchCarDevice/search",
		type: "POST",
		dataType: "json",
		data: data,
		success: function(result){
			if(result.result=="success"){
				var list = result.data;
				list.forEach(function(val){
					$("#list>#showCarPort").append("<div class=\"searchDevice\" val=\""+val.id+"\">"+val.name+"</div>")
				});
				
			}
		}
	})
}