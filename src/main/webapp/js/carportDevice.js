$(function(){
	searchButton.click();
})

var save = $("#save");
var search = $("#search");
var saveButton = $("#saveButton");
var searchButton = $("#searchButton");
var isOnline = $("#isOnline");
var startNum = $("#startNum");
var endNum = $("#endNum");
save.click(function(){
	save.css("background-color","#24AFB2");
	search.css("background-color","");
	saveButton.css("display","");
	searchButton.css("display","none");
	isOnline.attr("disabled","disabled");
	startNum.removeAttr("disabled")
	endNum.removeAttr("disabled")
});
search.click(function(){
	save.css("background-color","");
	search.css("background-color","#24AFB2");
	saveButton.css("display","none");
	searchButton.css("display","");
	isOnline.removeAttr("disabled");
	startNum.val("");
	endNum.val("");
	startNum.attr("disabled","disabled");
	endNum.attr("disabled","disabled");
});
saveButton.click(function(){
	var carportNumber = $("#carportNumber").val();
	var startNum = $("#startNum").val();
	var endNum = $("#endNum").val();
	if(carportNumber==""||!/^[A-Z]+$/.test(carportNumber)){
		alert("车位号不能为空且必须是大写英文");
	}else{
		if(startNum==""||!/^[1-9]\d*$/.test(startNum)||(endNum!=""&&!/^[1-9]\d*$/.test(endNum))){
			alert("编号不能为空且必须为正整数，结束编号可以不填")
		}else{
			if(endNum==""){
				endNum = startNum;
			}
			var data = {
				"carportNumber" : carportNumber,
				"startNum" : startNum,
				"endNum" : endNum
			};
			$.ajax({
				url : "/GraduationProject/carportDevice/save",
				type : "POST",
				dataType : "json",
				data : data,
				success : function(result){
					if(result.result=="success"){
						alert("保存成功");
						$("#carportNumber").val("");
						$("#startNum").val("");
						$("#endNum").val("");
						$("#searchButton").click();
					}else{
						alert("保存失败");
					}
				}
			});
		}
	}
});
searchButton.click(function(){
	var carportNumber = $("#carportNumber").val();
	var isOnline = $("#isOnline").val();
	var data = {
		"carportNumber" : carportNumber,
		"isOnline" : isOnline
	};
	$.ajax({
		url : "/GraduationProject/carportDevice/search",
		type : "POST",
		dataType : "json",
		data : data,
		success : function(result){
			if(result.result=="success"){
				var list = result.data;
				$("#showLable>table>tbody").html("<tr><th>车位号</th><th>车辆车牌</th><th>在离线</th></tr>");
				list.forEach(function(val){
					var isOnlineString=val.isOnline==1?"在线":"离线";
					$("#showLable>table>tbody").append(
						"<tr val="+"\""+val.id+"\""+"><td>"+val.carportNumber+"</td><td>"+val.isHaveCar+"</td><td>"+isOnlineString
						+"<img src=\"..\/pic\/delete.jpg\" style=\"float: right;cursor: pointer;\">"+"</td></tr>");
				});
				$("#showLable>table>tbody>tr>td>img").on("click",function(){
					var id = $(this).parent().parent().attr("val");
					var data = {
						id:id
					};
					$.ajax({
						url : "/GraduationProject/carportDevice/delete",
						type : "POST",
						dataType : "json",
						data : data,
						success : function(result){
							if(result.result=="success"){
								alert("删除成功");
							}else{
								alert("删除失败");
							}
							searchButton.click();
						}
					});
				});
			}else{
				alert("search失败");
			}
			$("#carportNumber").val("");
		}
	});
});

