/**
 * 首页
 * @returns
 */
layui.use(['layer','jquery','element','table','form'], function(){
	 var $ = layui.$;
	 var element = layui.element;
	 
	 var loadedTabs = new Array(); 
	  // 监听tab加载
	  element.on('tab(tabMain)', function(data){
		  var layId = data.elem.context.attributes['lay-id'].value;

		  if(!loadedTabs.includes(layId)){
			  if(layId === "2"){
				  $("#tabContent2").html(`<iframe src="datasource/index.htm" frameborder="0" style="width: 100%;height: 100%;"></iframe>`);
			  }else if(layId === "3"){
				  $("#tabContent3").html(`<iframe src="sys-user/index.htm" frameborder="0" style="width: 100%;height: 100%;"></iframe>`);
			  }else if(layId === "4"){
				  $("#tabContent4").html(`<iframe src="module/index.htm" frameborder="0" style="width: 100%;height: 100%;"></iframe>`);
			  }else if(layId === "5"){
				  $("#tabContent5").html(`<iframe src="operation/index.htm" frameborder="0" style="width: 100%;height: 100%;"></iframe>`);
			  }
			  loadedTabs.push(layId);
		  }
	  });
}); 