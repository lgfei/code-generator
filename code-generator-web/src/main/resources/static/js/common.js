/**
 * 
 */
layui.define(['layer'],function(exports){
	var $ = layui.$;
	var layer = layui.layer;
	
	function myAjax(type,url,data,async){
		var loadIndex;
		return $.ajax({
      		type: type,
      		url: url,
      		async: async,
      		data: data,
      		dataType: "json",
      		beforeSend: function(){
      			// 打开加载框
      			loadIndex = layer.load();
      		},
      		success: function(resp){
      			return resp;
      		},
      		error: function(e){
      			console.log(e);
      		},
      		complete: function(){
      			// 关闭加载框
      			layer.close(loadIndex);
      		}
		});
	}
	
	function myAjaxWithEntity(type,url,data,async){
		var loadIndex;
		return $.ajax({
      		type: type,
      		url: url,
      		async: async,
      		data: JSON.stringify(data),
      		contentType: "application/json;charset-UTF-8",
      		beforeSend: function(){
      			// 打开加载框
      			loadIndex = layer.load();
      		},
      		success: function(resp){
      			return resp;
      		},
      		error: function(e){
      			console.log(e);
      		},
      		complete: function(){
      			// 关闭加载框
      			layer.close(loadIndex);
      		}
		});
	}
	
	function myAsyncAjax(type,url,data){
		return myAjax(type,url,data,true);
	}
	
	function mySyncAjax(type,url,data){
		return myAjax(type,url,data,false);
	}
	
	function myAsyncAjaxWithEntity(type,url,data){
		return myAjaxWithEntity(type,url,data,true);
	}
	
	function mySyncAjaxWithEntity(type,url,data){
		return myAjaxWithEntity(type,url,data,false);
	}
	
	// 公开全局变量
	exports('common',{
		AppSetting : AppSetting,
		myAsyncAjax : myAsyncAjax,
		mySyncAjax : mySyncAjax,
		myAsyncAjaxWithEntity : myAsyncAjaxWithEntity,
		mySyncAjaxWithEntity : mySyncAjaxWithEntity
	});
});