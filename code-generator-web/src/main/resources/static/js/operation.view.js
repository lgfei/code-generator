/**
 * 首页
 * @returns
 */
layui.use(['layer','jquery','element','table','form'], function(){
	var $ = layui.$;
    var layer = layui.layer;
    var element = layui.element;
 	var table = layui.table;
 	
    // 用户管理表格
    table.render({
      elem: '#tbOperation',
      cellMinWidth: 100,
      height: 312,
      url: AppSetting.rootUrl + '/operation/page.json',
      page: true,
      toolbar: '#barOperation',
      defaultToolbar: ['filter', 'exports'],
      cols: [
        [
    	  {field: 'ck', title: '', type: 'radio'},
    	  {field: 'operationNo', title: '按钮编码', sort: true},
    	  {field: 'name', title: '名称', sort: true},
    	  {field: 'value', title: '权限值'},
          {field: 'createUser', title: '创建人'},
          {field: 'createTime', title: '创建时间', sort: true},
          {field: 'updateUser', title: '修改人'},
          {field: 'updateTime', title: '修改时间', sort: true},
          {field: 'remark', title: '备注'}
        ]
      ]
    }); 
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
}); 