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
	}else{
		$("#map").html("<span style=\"font-size: 111px;\">请先上传主图</span>");
		$("#map").css("text-align","center");
	}
	map._map.on("pointermove",function(e){
		position = e.coordinate;
	});
	searchDevice(map);
});
var position;
function searchDevice(map){
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
					$("#list>#showCarPort").append("<div class=\"searchDevice\" val=\""+val.id+"\">"+val.name+"</div>");
					
				});
				$("#list>#showCarPort>.searchDevice").draggable({
					zIndex: 999,
					helper: function(){
						var t = "<div style=\"height:30px; width:30px; background-color:green;text-align:center;font-size24px;color:white;\">S</div>";
						return $(t);
					},
					cursor: "default",
					cursorAt: {left:-15,top:0},
					containment: 'body',
					stop: function(ev){
						map.drawDevice(position);
						saveSearchCar($(this).attr("val"),position);
					}
				});
			}
		}
	})
}
function saveSearchCar(id,position){
	var data = {
		id: id,
		x: position[0],
	 	y: position[1],
	 	isOnMap: 1
	 }
	 $.ajax({
	 	url: "/GraduationProject/searchCarDevice/saveSearchCarPosition",
		type: "POST",
		dataType: "json",
		data: data,
		success: function(result){
		}
	 })
}