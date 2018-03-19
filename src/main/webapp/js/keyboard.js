$("#keyboard>.row>.num").click(function(){
	var num = $(this).text();
	var val = $("#searchCarText").val();;
	if(num==""){//是删除键
		val=val.substr(0,val.length-1)
	}else{//是字母键
		val = val+num;
	}
	$("#searchCarText").val(val);
});
