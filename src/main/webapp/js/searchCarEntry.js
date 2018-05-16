$(function(){
	
});
var ip = "172.7.2.102";
$("#searchCarText").click(function(){
	$(this).val("");
	$("#listKeyboard").click();
});
$("#listKeyboard").click(function(){
	$("#listKeyboard").css("background-color","#333A42");
	$("#listOption").css("background-color","");
	$("#listMap").css("background-color","")

	$("#option").attr("hidden","hidden");
	$("#map").attr("hidden","hidden");
	$("#keyboard").removeAttr("hidden");
});
$("#listOption").click(function(){
	$("#listOption").css("background-color","#333A42");
	$("#listKeyboard").css("background-color","");
	$("#listMap").css("background-color","");

	$("#keyboard").attr("hidden","hidden");
	$("#map").attr("hidden","hidden");
	$("#option").removeAttr("hidden");
});
$("#listMap").click(function(){
	$("#listMap").css("background-color","#333A42");
	$("#listOption").css("background-color","");
	$("#listKeyboard").css("background-color","");

	$("#option").attr("hidden","hidden");
	$("#map").attr("hidden","hidden");
	$("#map").removeAttr("hidden");
});
//立即寻车按钮
$("#searchCarButton").click(function(){
	var carNumber = $("#searchCarText").val();
	if(carNumber==""){
		alert("车牌不能为空");
	}else{
		$("#listOption").click();
		var data = {
			carNumber: carNumber
		};
		$.ajax({
			url: "/GraduationProject/carportDevice/searchByCarNumber",
			type: "POST",
			dataType: "json",
			data: data,
			success: function(result){
				if(result.result=="success"){
					$("#option>table>tbody").html("<tr><th>车牌</th><th>车位</th></tr>");
					var list = result.data;
					list.forEach(function(val){
						$("#option>table>tbody").append("<tr val=\""+val.id+"\"><td>"+val.carNumber+"</td><td>"+val.carportNumber+"</td></tr>");
					});
					$("#option>table>tbody>tr>td").click(function(){
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
							var id = $(this).parent().attr("val");
							var pathData = {
								id: id,
								ip: ip
							};
							//渲染得到路径
							var list;
							$.ajax({
								url: "/GraduationProject/path/getPath",
								type: "GET",
								dataType: "json",
								data: pathData,
								success: function(result){
									list = result.list;
									var pointList = [];
									list.forEach(function(val){
										pointList.push([val.x,val.y]);
									});
									map.drawDevice(pointList[pointList.length-1]);
									map.drawCarport(pointList[0]);
									map.draw(pointList);
								}
							});
						}else{
							alert("管理端未配置地图");
							$("#listKeyboard").click();
						}
					});
				}else{
					alert("寻车失败");
				}
				return result;
			}
		});
	}
});