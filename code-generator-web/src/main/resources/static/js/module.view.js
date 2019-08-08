/**
 * 首页
 * @returns
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
      elem: '#tbModule',
      cellMinWidth: 100,
      url: AppSetting.rootUrl + '/module/page.json',
      page: true,
      toolbar: '#barModule',
      defaultToolbar: ['filter', 'exports'],
      cols: [
        [
    	  {field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
    	  {field: 'moduleNo', title: '模块编码', sort: true, fixed: 'left'},
    	  {field: 'name', title: '名称', sort: true, fixed: 'left'},
    	  {field: 'url', title: '地址'},
          {field: 'createUser', title: '创建人'},
          {field: 'createTime', title: '创建时间', sort: true},
          {field: 'updateUser', title: '修改人'},
          {field: 'updateTime', title: '修改时间', sort: true},
          {field: 'remark', title: '备注'},
 	      {field: 'opt', title: '操作', toolbar: '#barModuleCols', fixed: 'right', width: 250}
        ]
      ]
    }); 
    
    // 监听表格工具条
    table.on('toolbar(module)', function(obj){
  	  var checkStatus = table.checkStatus(obj.config.id);
    	  var data = checkStatus.data;
    });
    
    // 监听表格行工具条
    table.on('tool(module)', function(obj){
    	var data = obj.data;
    	var moduleNo = data.moduleNo;
    	
    	var layEvent = obj.event;
    	// 查看
    	if(layEvent === 'btnAssignmentUser'){
    		var layId = 'btnAssignmentUser-'+moduleNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '分配用户('+moduleNo+')',
    				content: `
    				<form class="layui-form" lay-filter="formAssignmentUser">
    					<div class="layui-row">
						  	<div class="layui-col-md6">
						  		<div class="layui-form-item">
							    	<label class="layui-form-label">模块编码</label>
							    	<div class="layui-input-block">
							      		<input type="text" name="moduleNo" autocomplete="off" class="layui-input layui-bg-gray" value="${data.moduleNo}" readonly="readonly"/>
							    	</div>
							  	</div>
						  	</div>
						  	<div class="layui-col-md6">
						  		<div class="layui-form-item">
							    	<label class="layui-form-label">名称</label>
							    	<div class="layui-input-block">
							      		<input type="text" name="name" autocomplete="off" class="layui-input layui-bg-gray" value="${data.name}" readonly="readonly"/>
							    	</div>
							  	</div>
						  	</div>    						
    					</div>
    					
    					<div class="layui-row">
						  	<div class="layui-col-md12">
						  		<div class="layui-form-item">
				                    <label class="layui-form-label">用户</label>
				                    <div class="layui-input-block">
				                      <select name="userNos" xm-select="userNos" xm-select-direction="down"></select>
				                    </div>
				                </div>
						  	</div>    						
    					</div>    					
    					
    					<div class="layui-row">
						  	<div class="layui-col-md12">
						  		<div class="layui-form-item">
				                    <label class="layui-form-label">功能权限</label>
				                    <div class="layui-input-block">
				                      <select name="operations" xm-select="operations" xm-select-direction="down"></select>
				                    </div>
				                </div>
						  	</div>    						
    					</div>
    				</form>`
    			});
    			
    			form.render(null, 'formAssignmentUser');
    			
        		$.ajax({
            		type: 'POST',
            		url: AppSetting.rootUrl + '/sys-user/findModuleUsers.json',
            		async: false,
            		data: {'moduleNo': moduleNo},
            		dataType:'json',
            		success: function(resp){
            			var selectData = [];
            			$.each(resp['items'],function(i,item){
            				let obj = {"name": item.name, "value": item.userNo};
           	    			selectData.push(obj);
            			});
            			formSelects.data('userNos', 'local', {
            			    arr: selectData
            			});
            			
            			var selectedItems = [];
            			$.each(resp['selected'],function(i,item){
            				selectedItems.push(item.userNo);
            			});
            			formSelects.value('userNos', selectedItems);
            		},
            		error: function(e){
            			console.log(e);
            		}
            	});
        		
        		$.ajax({
            		type: 'POST',
            		url: AppSetting.rootUrl + '/operation/findModuleOperations.json',
            		async: false,
            		data: {'moduleNo': moduleNo},
            		dataType:'json',
            		success: function(resp){
            			var selectData = [];
            			var selectedItems = [];
            			$.each(resp,function(i,item){
            				let obj = {"name": item.name, "value": item.value};
           	    			selectData.push(obj);
           	    			selectedItems.push(item.value);
            			});
            			formSelects.data('operations', 'local', {
            			    arr: selectData
            			});
            			formSelects.value('operations', selectedItems);
            		},
            		error: function(e){
            			console.log(e);
            		}
            	});  
    		}
    		// 切换到当前tab
	        element.tabChange('tabMain', layId);
    	}
    });
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
    
    exports('module.view');
}); 