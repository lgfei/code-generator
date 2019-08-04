/**
 * 用户管理
 */
layui.define(['layer','jquery','element','table','form','common'], function(exports){
	var $ = layui.$;
    var layer = layui.layer;
    var element = layui.element;
 	var table = layui.table;
 	var form = layui.form;
 	var formSelects = layui.formSelects;
 	var common = layui.common;
 	
 	var AppSetting = common.AppSetting;
    // 用户管理表格
    table.render({
      elem: '#tbSysUser',
      cellMinWidth: 100,
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
    
    // 监听表格行工具条
    table.on('tool(sysUser)', function(obj){
    	var data = obj.data;
    	var userNo = data.userNo;
    	
    	var layEvent = obj.event;
    	if(layEvent === 'btnAssignmentDatasource'){ // 分配数据源
    		layer.open({
			  type: 1,
			  title: '分配数据源',
			  area: ['500px', '300px'],
			  content: `
			  <form class="layui-form" style="padding:10px;">
				  <div class="layui-form-item">
				    <label class="layui-form-label">用户编码</label>
				    <div class="layui-input-block">
				      <input type="text" name="userNo" autocomplete="off" class="layui-input layui-bg-gray" value="${data.userNo}" readonly="readonly"/>
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label">姓名</label>
				    <div class="layui-input-block">
				      <input type="text" name="name" autocomplete="off" class="layui-input layui-bg-gray" value="${data.name}" readonly="readonly"/>
				    </div>
				  </div>
				  <div class="layui-form-item">
                    <label class="layui-form-label">数据源</label>
                    <div class="layui-input-block">
                      <select name="datasourceNos" xm-select="datasourceNos" xm-select-direction="down"></select>
                    </div>
                  </div>				  
			  </form>`,
			  btn: ['确定','取消'],
			  yes: function(index, layero){
				  var datasourceNos = [];
				  var selectedList = formSelects.value('datasourceNos');
				  $.each(selectedList,function(i,item){
					  datasourceNos.push(item.value);
      			  });
				  $.ajax({
	        		type: 'POST',
	        		url: AppSetting.rootUrl + '/user-datasource/assignmentDatasource.json',
	        		async: false,
	        		data: {'userNo':userNo,'datasourceNos':datasourceNos.join(',')},
	        		dataType:'json',
	        		beforeSend: function(){
	          			// 打开加载框
	          			loadIndex = layer.load();
	          		},
	        		success: function(resp){
	        			if(resp.code === "0"){
	        				layer.alert('保存成功！');
	        			}
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
			});
    		
    		form.render();
    		
    		$.ajax({
        		type: 'POST',
        		url: AppSetting.rootUrl + '/datasource/findDatasourceByUser.json',
        		async: false,
        		data: {'userNo':userNo},
        		// dataType:'application/json',
        		success: function(resp){
        			var selectData = [];
        			$.each(resp['items'],function(i,item){
        				let obj = {"name": item.name, "value": item.datasourceNo};
       	    			selectData.push(obj);
        			});
        			formSelects.data('datasourceNos', 'local', {
        			    arr: selectData
        			});
        			
        			var selectedItems = [];
        			$.each(resp['selected'],function(i,item){
        				selectedItems.push(item.datasourceNo);
        			});
        			formSelects.value('datasourceNos', selectedItems);
        		},
        		error: function(e){
        			console.log(e);
        		}
        	});
    	}
    });
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
    
    exports('sys-user.view');
});