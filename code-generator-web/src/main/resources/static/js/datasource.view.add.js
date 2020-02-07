/**
 * 新增数据源
 */
layui.define(['layer','jquery','table','form','common'], function(exports){
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
   	var form = layui.form;
   	var common = layui.common;
   	
   	// 初始渲染
	form.render(null, 'formAdd');
	
	//监听保存
    form.on('submit(btnSubmitAdd)', function(data){
    	var entity = {
        		'name': data.field.name,
        		'dbType': data.field.dbType,
        		'driver': data.field.driver,
        		'server': data.field.server,
        		'port': data.field.port,
        		'username': data.field.username,
        		'password': data.field.password,
        		'type': data.field.type
    		}
    	common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/datasource/save.json',{'entity': entity}).then(function(resp){
    		if(resp.code === "0"){
    			// 刷新列表
    			parent.layui.table.reload('tbDatasource');
    			// 关闭弹层
    			var index = parent.layer.getFrameIndex(window.name); 
    			parent.layer.close(index);
    			layer.msg('保存成功！');
    		}
    	});
    	// 阻止表单跳转
    	return false;
    });
   	
   	exports('datasource.view.add');
});