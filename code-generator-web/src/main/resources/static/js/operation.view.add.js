/**
 * 新增按钮
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
        		'value': data.field.value
    		}
    	common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/operation/save.json',{'entity': entity}).then(function(resp){
    		if(resp.code === "0"){
    			// 刷新列表
    			parent.layui.table.reload('tbOperation');
    			// 关闭弹层
    			var index = parent.layer.getFrameIndex(window.name); 
    			parent.layer.close(index);
    			layer.msg('保存成功！');
    		}
    	});
    	// 阻止表单跳转
    	return false;
    });
   	
   	exports('operation.view.add');
});