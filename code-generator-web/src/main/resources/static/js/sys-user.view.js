/**
 * 用户管理
 */
layui.use(['layer','jquery','element','table'], function(){
	var $ = layui.$;
    var layer = layui.layer;
    var element = layui.element;
 	var table = layui.table;
 	
    // 用户管理表格
    table.render({
      elem: '#tbSysUser',
      cellMinWidth: 100,
      height: 312,
      url: AppSetting.rootUrl + '/sys-user/page.json',
      page: true,
      toolbar: '#barSysUser',
      defaultToolbar: ['filter', 'exports'],
      cols: [
        [
    	  {field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
    	  {field: 'userNo', title: '用户编码', sort: true, fixed: 'left'},
    	  {field: 'name', title: '名称', sort: true, fixed: 'left'},
    	  {field: 'account', title: '帐号'},
          {field: 'password', title: '密码'},
          {field: 'createUser', title: '创建人'},
          {field: 'createTime', title: '创建时间', sort: true},
          {field: 'updateUser', title: '修改人'},
          {field: 'updateTime', title: '修改时间', sort: true},
          {field: 'remark', title: '备注'},
 	      {field: 'opt', title: '操作', toolbar: '#barSysUserCols', fixed: 'right', width: 200}
        ]
      ]
    }); 
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
});