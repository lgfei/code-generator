/**
 * 用户管理
 */
layui.define(['layer','jquery','element','table','form','tree','common'], function(exports){
	var $ = layui.$;
    var layer = layui.layer;
    var element = layui.element;
 	var table = layui.table;
 	var form = layui.form;
 	var formSelects = layui.formSelects;
 	var tree = layui.tree;
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
    	// 分配数据源
    	if(layEvent === 'btnAssignmentDatasource'){ 
    		var layId = 'btnAssignmentDatasource-' + userNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    			  id: layId,
	              title: '分配数据源('+userNo+')',
	              content: `
	              <form class="layui-form" lay-filter="formAssignmentDatasource" style="padding:10px;">
					  <div class="layui-row">
					  	<div class="layui-col-md6">
					  		<div class="layui-form-item">
						    	<label class="layui-form-label">用户编码</label>
						    	<div class="layui-input-block">
						      		<input type="text" name="userNo" autocomplete="off" class="layui-input layui-bg-gray" value="${data.userNo}" readonly="readonly"/>
						    	</div>
						  	</div>
					  	</div>
					  	<div class="layui-col-md6">
					  		<div class="layui-form-item">
						    	<label class="layui-form-label">姓名</label>
						    	<div class="layui-input-block">
						      		<input type="text" name="name" autocomplete="off" class="layui-input layui-bg-gray" value="${data.name}" readonly="readonly"/>
						    	</div>
						  	</div>
					  	</div>
					  </div>
					  
					  <div class="layui-row">
					  	<div class="layui-col-md12">
					  		<div class="layui-form-item">
			                    <label class="layui-form-label">数据源</label>
			                    <div class="layui-input-block">
			                      <select name="datasourceNos" xm-select="datasourceNos" xm-select-direction="down"></select>
			                    </div>
			                </div>
					  	</div>
					  </div>
	                  
	                  <div class="layui-row">
	                  	<div class="layui-form-item">
		                  <div class="layui-input-block">
		                    <button class="layui-btn" lay-filter="btnSubmitAssignmentDatasource" lay-submit>保存</button>
		                    <button class="layui-btn layui-btn-primary" type="reset">重置</button>
		                  </div>
		                </div>
	                  </div>
				  </form>`
    			});
    			
        		form.render(null, 'formAssignmentDatasource');
        		
        		$.ajax({
            		type: 'POST',
            		url: AppSetting.rootUrl + '/datasource/findDatasourceByUser.json',
            		async: false,
            		data: {'userNo':userNo},
            		dataType:'json',
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
        		
    	        //监听提交
    	        form.on('submit(btnSubmitAssignmentDatasource)', function(data){
    	        	var datasourceNos = [];
  				  	var selectedList = formSelects.value('datasourceNos');
  				  	$.each(selectedList,function(i,item){
  					  datasourceNos.push(item.value);
        			});
    	        	var loadIndex;
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
    	        	// 阻止表单跳转
    	        	return false;
    	        });
    		}
    		// 切换到当前tab
            element.tabChange('tabMain', layId);
    	}
    	
    	// 分配模块权限
    	if(layEvent === 'btnAssignmentModule'){ 
    		var layId = 'btnAssignmentModule-' + userNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
      			  id: layId,
  	              title: '分配模块权限('+userNo+')',
  	              content: `
  	              <form class="layui-form" lay-filter="formAssignmentModule">
					  <div class="layui-row">
					  	<div class="layui-col-md6">
					  		<div class="layui-form-item">
						    	<label class="layui-form-label">用户编码</label>
						    	<div class="layui-input-block">
						      		<input type="text" name="userNo" autocomplete="off" class="layui-input layui-bg-gray" value="${data.userNo}" readonly="readonly"/>
						    	</div>
						  	</div>
					  	</div>
					  	<div class="layui-col-md6">
					  		<div class="layui-form-item">
						    	<label class="layui-form-label">姓名</label>
						    	<div class="layui-input-block">
						      		<input type="text" name="name" autocomplete="off" class="layui-input layui-bg-gray" value="${data.name}" readonly="readonly"/>
						    	</div>
						  	</div>
					  	</div>
					  </div>
					  
					  <div class="layui-row">
					  	<div class="layui-col-md12">
					  		<div class="layui-form-item">
			                    <label class="layui-form-label">模块权限</label>
			                    <div class="layui-input-block">
			                      <div id="treeModuleOperation"></div>
			                    </div>
			                </div>
					  	</div>
					  </div>
	                  
	                  <div class="layui-row">
	                  	<div class="layui-form-item">
		                  <div class="layui-input-block">
		                    <button class="layui-btn" lay-filter="btnSubmitAssignmentModule" lay-submit>保存</button>
		                    <button class="layui-btn layui-btn-primary" type="reset">重置</button>
		                  </div>
		                </div>
	                  </div>
				  </form>`
    			});
    			
    			form.render(null, 'formAssignmentModule');
    			
    			var treeData = [];
    			$.ajax({
            		type: 'POST',
            		url: AppSetting.rootUrl + '/user-module-operation/findUserModuleOperations.json',
            		async: false,
            		data: {'userNo':userNo},
            		dataType:'json',
            		success: function(resp){
            			treeData = resp;
            		},
            		error: function(e){
            			console.log(e);
            		}
            	});
    			
    			tree.render({
      		      elem: '#treeModuleOperation',
      		      showCheckbox: true,
      		      data: treeData,
      		      id: 'idTreeModuleOperation'
      		    });
    			
    			
    	        //监听提交
    	        form.on('submit(btnSubmitAssignmentModule)', function(data){
    	        	debugger 
    	        	var rows = [];
    	        	var checkData = tree.getChecked('idTreeModuleOperation');
    	        	$.each(checkData,function(i,item){
    	        		var operations = [];
    	        		var children = item.children;
    	        		$.each(children,function(j,child){
    	        			operations.push(child.id);
    	        		});
    	        		var operationsStr = '';
    	        		if(operations){
    	        			operationsStr = operations.join(',');
    	        		}
    	        		var nodeData = {'userNo':userNo,'moduleNo':item.id,'operations':operationsStr};
    	        		rows.push(nodeData);
    	        	});
    	        	var loadIndex;
	    	      	$.ajax({
	    	        	type:'POST',
	    	        	url: AppSetting.rootUrl + '/user-module-operation/saveUserModuleOperations.json',
	    	        	data: {'rows':JSON.stringify(rows)},
	    	        	dataType: "json",
	    	        	beforeSend: function(){
	    	        		// 打开加载框
	    	        		loadIndex = layer.load();
	    	        	},
	    	        	success:function(resp){
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
	    	      // 阻止表单跳转
	    	      return false;
    	        });
    		}
    		// 切换到当前tab
            element.tabChange('tabMain', layId);
    	}
    });
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
    
    exports('sys-user.view');
});