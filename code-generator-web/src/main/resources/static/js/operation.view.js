/**
 * 首页
 * @returns
 */
layui.define(['layer','jquery','element','table','form','common'], function(exports){
	var $ = layui.$;
    var layer = layui.layer;
    var element = layui.element;
 	var table = layui.table;
 	var common = layui.common;
 	
 	var AppSetting = common.AppSetting;
    // 用户管理表格
    table.render({
      elem: '#tbOperation',
      cellMinWidth: 100,
      url: AppSetting.rootUrl + '/operation/page.json',
      page: true,
      toolbar: '#barOperation',
      defaultToolbar: ['filter', 'exports'],
      cols: [
        [
    	  {field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
    	  {field: 'operationNo', title: '按钮编码', sort: true, fixed: 'left'},
    	  {field: 'name', title: '名称', sort: true, fixed: 'left'},
    	  {field: 'value', title: '权限值'},
          {field: 'createUser', title: '创建人'},
          {field: 'createTime', title: '创建时间', sort: true},
          {field: 'updateUser', title: '修改人'},
          {field: 'updateTime', title: '修改时间', sort: true},
          {field: 'remark', title: '备注'},
          {field: 'opt', title: '操作', toolbar: '#barOperationCols', fixed: 'right', width: 180}
        ]
      ]
    });
    
    // 监听表格工具条
    table.on('toolbar(operation)', function(obj){
  	  var checkStatus = table.checkStatus(obj.config.id);
    	  var data = checkStatus.data;
    });
    
    // 监听表格行工具条
    table.on('tool(operation)', function(obj){
    	var data = obj.data;
    	var moduleNo = data.moduleNo;
    });    
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
    
    exports('operation.view');
}); 