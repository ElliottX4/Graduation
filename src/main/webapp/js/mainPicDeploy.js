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
})
$("#uploadPic").click(function(){
	$("#getPic").click();
});
function changePic(){	
	var formData = new FormData();
	formData.append("imgFile",$("#getPic")[0].files[0]);
	$.ajax({
		url : "/GraduationProject/mainPicDeploy/save",
		type : "POST",
		dataType : "json",
		data : formData,
		cache:false,         //不设置缓存
		processData: false,  // 不处理数据
		contentType: false,  // 不设置内容类型
		success : function(result){
			if(result.result=="success"){
				alert("保存成功");
				window.location.reload();
			}else{
				alert("保存失败");
			}
		}
	});
}