function swcMap(){
	this._map = null;//map对象
	this.layers = [];//基础图层
	this.outterLayers = []//自己添加的图层
	this.eventKey = {};//事件对象
	this.drawTool = {};//画线工具
}
swcMap.prototype.isImageExit = function(url){
	var result;
	$.ajax({
		url: url,
		async: false,
		cache:false,
		success: function(){
			result = true;
		},
		statusCode: {
			404 : function(){
				result = false;
			}
		}
	});	
	return result;
}
	/*var options = {
					sizeW: "500",
					sizeH: "500",//静态图片的长和高
					mapUrl: "../pic/mainPic.jpg",//图片的路径
					domId: "map"//div的id
			};*/
swcMap.prototype.initMap = function(options){
	var extent = [];
	extent.push(options.sizeW / 2 * -1,options.sizeH / 2 * -1,options.sizeW / 2,options.sizeH / 2);
	var projection = new ol.proj.Projection({
		code: 'EPSG:3857',
		extent: extent
		});
	var imageLayer = new ol.layer.Image({
		source: new ol.source.ImageStatic({
			url: options.mapUrl,
			projection: projection,
			imageExtent: extent,
			imageSize:[options.sizeW,options.sizeH]
			})
		});
	this.layers.push(imageLayer);
	if(!options.maxZoom) {
		options.maxZoom = 4;
	}
	if(!options.minZoom) {
		options.minZoom = 0;
	}
	if(!options.zoom) {
		options.zoom = 2;
	}
	this._map = new ol.Map({  //初始化map
		target: options.domId,
		layers: this.layers,
		view: new ol.View({
			projection: projection,//加载静态图片的时候必须要有
			center: [0,0],
			zoom: options.zoom,
			maxZoom : options.maxZoom,
			minZoom : options.minZoom,
			extent: extent
		})
	});
}
/*
 * list是点的集合
 * list=[[0,0],[100,100],[200,200]]
 */
swcMap.prototype.draw = function(list){
	var style = new ol.style.Style({
		image: new ol.style.Circle({
			fill: new ol.style.Fill({
				color: "red"
			}),
			radius: 5
		}),
		fill: new ol.style.Fill({
			color: "red"
		}),
		stroke: new ol.style.Stroke({
			width: 5,
			color: "red"
		})
	});
	var source = new ol.source.Vector({
		features: [] 
		});
	list.forEach(function(val) {
		var pointFeature = new ol.Feature({
			 geometry: new ol.geom.Point(val) 
		});
		source.addFeature(pointFeature);
	});
	var lineFeature = new ol.Feature({
	 geometry: new ol.geom.LineString(list) 
	});
	source.addFeature(lineFeature);
		
	var layer = new ol.layer.Vector({
			source: source,
			style: style
		});
	this._map.addLayer(layer);
}