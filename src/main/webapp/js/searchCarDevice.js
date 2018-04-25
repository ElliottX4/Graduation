$(function(){
	searchButton.click();
})

var save = $("#save");
var search = $("#search");
var saveButton = $("#saveButton");
var searchButton = $("#searchButton");
var isOnline = $("#isOnline");
save.click(function(){
	save.css("background-color","#24AFB2");
	search.css("background-color","");
	saveButton.css("display","");
	searchButton.css("display","none");
	isOnline.attr("disabled","disabled");
	isOnline.css("color","");
});
search.click(function(){
	save.css("background-color","");
	search.css("background-color","#24AFB2");
	saveButton.css("display","none");
	searchButton.css("display","");
	isOnline.removeAttr("disabled");
	isOnline.css("color","white");
});
saveButton.click(function(){
	var name = $("#name").val();
	var ip = $("#ip").val();
	if(name==""||ip==""){
		alert("name和ip不能为空");
	}else{
		var data = {
			"name" : name,
			"ip" : ip
		};
		$.ajax({
			url : "/GraduationProject/searchCarDevice/save",
			type : "POST",
			dataType : "json",
			data : data,
			success : function(result){
				if(result.result=="success"){
					alert("保存成功");
					
				}else{
					alert("保存失败");
				}
				$("#name").val("");
				$("#ip").val("");
				searchButton.click();
			}
		});
	}
});
searchButton.click(function(){
	var name = $("#name").val();
	var ip = $("#ip").val();
	var isOnlineVal = isOnline.val();
	var data = {
		"name" : name,
		"ip" : ip,
		"isOnline" : isOnlineVal
	};
	$.ajax({
		url : "/GraduationProject/searchCarDevice/search",
		type : "POST",
		dataType : "json",
		data : data,
		success : function(result){
			if(result.result=="success"){
				var list = result.data;
				$("#showLable>table>tbody").html("<tr><th>名称</th><th>IP</th><th>在离线</th></tr>");
				list.forEach(function(val){
					var isOnlineString=val.isOnline==1?"在线":"离线";
					$("#showLable>table>tbody").append(
						"<tr val="+"\""+val.id+"\""+"><td>"+val.name+"</td><td>"+val.ip+"</td><td>"+isOnlineString
						+"<img src=\"..\/pic\/delete.jpg\" style=\"float: right;cursor: pointer;\">"+"</td></tr>");
				});
				$("#showLable>table>tbody>tr>td>img").on("click",function(){
					var id = $(this).parent().parent().attr("val");
					var data = {
						id:id
					};
					$.ajax({
						url : "/GraduationProject/searchCarDevice/delete",
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
		}
	});
});


