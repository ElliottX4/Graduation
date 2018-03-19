var searchCarDeviceTitle = $("#searchCarDeviceTitle");
var carportDeviceTitle = $("#carportDeviceTitle");
var mainPicDeployTitle = $("#mainPicDeployTitle");
var carportDeployTitle = $("#carportDeployTitle");
var searchCarDeployTitle = $("#searchCarDeployTitle");
var pathDeployTitle = $("#pathDeployTitle");
var temp = searchCarDeviceTitle;
var iframe = $("#main>iframe");
searchCarDeviceTitle.click(function(){
	temp.css("background-color","");
	searchCarDeviceTitle.css("background-color","#24AFB2");
	temp = searchCarDeviceTitle;
	iframe.attr("src","searchCarDevice.html");
	}
);	
carportDeviceTitle.click(function(){
	temp.css("background-color","");
	carportDeviceTitle.css("background-color","#24AFB2");
	temp = carportDeviceTitle;
	iframe.attr("src","carportDevice.html");
	}
);	
mainPicDeployTitle.click(function(){
	temp.css("background-color","");
	mainPicDeployTitle.css("background-color","#24AFB2");
	temp = mainPicDeployTitle;
	iframe.attr("src","mainPicDeploy.html");
	}
);	
carportDeployTitle.click(function(){
	temp.css("background-color","");
	carportDeployTitle.css("background-color","#24AFB2");
	temp = carportDeployTitle;
	iframe.attr("src","carportDeploy.html");
	}
);	
searchCarDeployTitle.click(function(){
	temp.css("background-color","");
	searchCarDeployTitle.css("background-color","#24AFB2");
	temp = searchCarDeployTitle;
	iframe.attr("src","searchCarDeploy.html");
	}
);	
pathDeployTitle.click(function(){
	temp.css("background-color","");
	pathDeployTitle.css("background-color","#24AFB2");
	temp = pathDeployTitle;
	iframe.attr("src","pathDeploy.html");
	}
);
