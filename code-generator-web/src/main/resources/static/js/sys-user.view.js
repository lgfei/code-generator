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
        method: 'post',
 	    contentType: 'application/json;charset-UTF-8',
        page: true,
        toolbar: '#barSysUser',
        defaultToolbar: ['filter', 'exports'],
        cols: [
        	[
        		{field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
        		{field: 'id', title: 'ID', hide: true, fixed: 'left'},
        		{field: 'userNo', title: '用户编码', fixed: 'left'},
        		{field: 'name', title: '名称', fixed: 'left'},
        		{field: 'account', title: '帐号'},
        		{field: 'password', title: '密码', hide: true},
        		{field: 'createUser', title: '创建人'},
        		{field: 'createTime', title: '创建时间', sort: true},
        		{field: 'updateUser', title: '修改人'},
        		{field: 'updateTime', title: '修改时间', sort: true},
        		{field: 'remark', title: '备注'},
        		{field: 'opt', title: '操作', toolbar: '#barSysUserCols', fixed: 'right', width: 350}
        	]
        ]
    }); 
    
    // 监听表格工具条
    table.on('toolbar(sysUser)', function(obj){
    	var layEvent = obj.event;
      	// 刷新
    	if(layEvent === 'btnRefresh'){
    		table.reload('tbSysUser');
    	}
    	// 新增
    	if(layEvent === 'btnAdd'){
    		layer.open({
    			title: '新增',
    			type: 2,
    			area: ['600px','250px'],
    			content: [AppSetting.rootUrl + '/sys-user/add.htm','no']
			});
    	}
    	// 批量删除
    	if(layEvent === 'btnBatchDelete'){
    		layer.confirm('确定要删除吗?', function(index){
    			var checkStatus = table.checkStatus(obj.config.id);
    			var entityList = checkStatus.data;
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/sys-user/removeList.json',{'entityList': entityList}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbSysUser');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    });
    
    // 监听表格行工具条
    table.on('tool(sysUser)', function(obj){
    	var data = obj.data;
    	var dataId = data.id;
    	var userNo = data.userNo;
    	
    	var layEvent = obj.event;
    	var layId = 'main_list';
    	// 查看
    	if(layEvent === 'btnView'){
    		layId = 'btnView_'+userNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '查看('+userNo+')',
    				content: `
	    			<table class="layui-table" lay-skin="row">
    					<colgroup>
			   		    	<col width="100">
			   		     	<col width="200">
			   		     	<col width="100">
			   		     	<col width="200">
    					</colgroup>
			   		   	<tbody>
			   		    	<tr>
			   		       		<td>用户编码</td>
			   		       		<td>${data.userNo}</td>
			   		       		<td>账号</td>
			   		       		<td>${data.account}</td>
			   		     	</tr>
			   		     	<tr>
			   		       		<td>用户名</td>
			   		       		<td>${data.name}</td>
			   		       		<td></td>
			   		       		<td></td>
			   		     	</tr>
			   		   	</tbody>
					</table>`
    			});
    		}
    	}
    	// 编辑
    	if(layEvent === 'btnEdit'){
    		layId = 'btnEdit_'+userNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '编辑('+userNo+')',
    				content: `
    				<form class="layui-form" lay-filter="${layId}_formEdit">
    					<div class="layui-row">
    						<div class="layui-col-md4">
    							<div class="layui-inline">
    								<label class="layui-form-label"><span style="color:red;">*</span>用户编码</label>
    								<div class="layui-input-inline">
                            			<input type="text" name="userNo" value="${data.userNo}" lay-verify="required" autocomplete="off" class="layui-input layui-bg-gray" readonly="readonly"/>
                          			</div>
    							</div>
    						</div>
    						<div class="layui-col-md4">
        	  	        		<div class="layui-inline">
        	  	          			<label class="layui-form-label"><span style="color:red;">*</span>账号</label>
        	  	          			<div class="layui-input-inline">
                						<input type="text" name="account" lay-verify="required" autocomplete="off" class="layui-input"/>
                          			</div>
        	  	        		</div>
        	  	      		</div>
        	  	      		<div class="layui-col-md4">
        	  	        		<div class="layui-inline">
        	  	          			<label class="layui-form-label"><span style="color:red;">*</span>用户名</label>
        	  	          			<div class="layui-input-inline">
                						<input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"/>
                          			</div>
        	  	        		</div>
        	  	      		</div>
    					</div>
    					
    					<br/>
                		
                    	<div class="layui-row">
                    		<div class="layui-form-item">
                    			<div class="layui-input-block">
                    				<button class="layui-btn" lay-filter="${layId}_btnSubmitEdit" lay-submit>保存</button>
                    				<button class="layui-btn layui-btn-primary" type="reset">重置</button>
                    			</div>
                    		</div>
                    	</div>
    				</form>`
    			});
    			// 初始渲染
    			form.render(null, layId+'_formEdit');
    			form.val(layId+'_formEdit', data);
    			
    			//监听保存
                form.on('submit('+layId+'_btnSubmitEdit)', function(data){
                	var entity = {
	        				'id': dataId,
	        				'account': data.field.account,
        	        		'name': data.field.name
                		}
                	common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/sys-user/update.json',{'entity': entity}).then(function(resp){
                		if(resp.code === "0"){
                			table.reload('tbSysUser');
                			layer.msg('保存成功！');
                		}
                	});
                	// 阻止表单跳转
                	return false;
                });
    		}
    	}
    	// 删除
    	if(layEvent === 'btnDelete'){
    		layer.confirm('确定要删除吗?', function(index){
    			var entity = {
        				'id': dataId
            		}
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/sys-user/remove.json',{'entity': entity}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbSysUser');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    	// 分配数据源
    	if(layEvent === 'btnAssignmentDatasource'){ 
    		layId = 'btnAssignmentDatasource_' + userNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    			  id: layId,
	              title: '分配数据源('+userNo+')',
	              content: `
	              <form class="layui-form" lay-filter="${layId}_formAssignmentDatasource">
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
			                      <select name="datasourceNos" xm-select="${layId}_datasourceNos" xm-select-direction="down"></select>
			                    </div>
			                </div>
					  	</div>
					  </div>
	                  
	                  <div class="layui-row">
	                  	<div class="layui-form-item">
		                  <div class="layui-input-block">
		                    <button class="layui-btn" lay-filter="${layId}_btnSubmitAssignmentDatasource" lay-submit>保存</button>
		                    <button class="layui-btn layui-btn-primary" type="reset">重置</button>
		                  </div>
		                </div>
	                  </div>
				  </form>`
    			});
    			
        		form.render(null, layId+'_formAssignmentDatasource');
        		
        		common.mySyncAjax('POST',AppSetting.rootUrl + '/datasource/findDatasourceByUser.json',{'userNo':userNo}).then(function(resp){
        			var selectData = [];
        			$.each(resp['items'],function(i,item){
        				let obj = {"name": item.name, "value": item.datasourceNo};
       	    			selectData.push(obj);
        			});
        			formSelects.data(layId+'_datasourceNos', 'local', {
        			    arr: selectData
        			});
        			
        			var selectedItems = [];
        			$.each(resp['selected'],function(i,item){
        				selectedItems.push(item.datasourceNo);
        			});
        			formSelects.value(layId+'_datasourceNos', selectedItems);
        		});
        		
    	        //监听提交
    	        form.on('submit('+layId+'_btnSubmitAssignmentDatasource)', function(data){
    	        	var datasourceNos = [];
  				  	var selectedList = formSelects.value(layId+'_datasourceNos');
  				  	$.each(selectedList,function(i,item){
  				  		datasourceNos.push(item.value);
        			});
    	        	common.mySyncAjax('POST',AppSetting.rootUrl + '/user-datasource/saveUserDatasources.json',{'userNo':userNo,'datasourceNos':datasourceNos.join(',')}).then(function(resp){
    	        		if(resp.code === "0"){
	        				layer.alert('保存成功！');
	        			}
    	        	}); 
    	        	// 阻止表单跳转
    	        	return false;
    	        });
    		}
    	}
    	
    	// 分配模块权限
    	if(layEvent === 'btnAssignmentModule'){ 
    		layId = 'btnAssignmentModule_' + userNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
      			  id: layId,
  	              title: '分配模块权限('+userNo+')',
  	              content: `
  	              <form class="layui-form" lay-filter="${layId}_formAssignmentModule">
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
			                      <div id="${layId}_treeModuleOperation"></div>
			                    </div>
			                </div>
					  	</div>
					  </div>
	                  
	                  <div class="layui-row">
	                  	<div class="layui-form-item">
		                  <div class="layui-input-block">
		                    <button class="layui-btn" lay-filter="${layId}_btnSubmitAssignmentModule" lay-submit>保存</button>
		                    <button class="layui-btn layui-btn-primary" type="reset">重置</button>
		                  </div>
		                </div>
	                  </div>
				  </form>`
    			});
    			
    			form.render(null, layId+'_formAssignmentModule');
    			
    			$.ajax({
            		type: 'POST',
            		url: AppSetting.rootUrl + '/user-module-operation/findUserModuleOperations.json',
            		async: false,
            		data: {'userNo':userNo},
            		dataType:'json',
            		success: function(resp){
            			let treeData = [];
            			let checkedIds = [];
            			$.each(resp,function(i,node){
            				let children = node.children;
            				$.each(children,function(i,childNode){
            					childId = node.id + '-' + childNode.id;
            					childNode.id = childId;
            					if(childNode.checked){
            						checkedIds.push(childId);
            					}
            				});
            				node.children = children;
            				treeData.push(node);
            			});
            			// 加载树形数据
            			tree.render({
            		      elem: '#'+layId+'_treeModuleOperation',
            		      showCheckbox: true,
            		      data: treeData,
            		      id: layId+'_idTreeModuleOperation'
            		    });
            			tree.setChecked(layId+'_idTreeModuleOperation', checkedIds)
            		},
            		error: function(e){
            			console.log(e);
            		}
            	});
    			
    	        //监听提交
    	        form.on('submit('+layId+'_btnSubmitAssignmentModule)', function(data){
    	        	var rows = [];
    	        	var checkData = tree.getChecked(layId+'_idTreeModuleOperation');
    	        	$.each(checkData,function(i,item){
    	        		var operations = [];
    	        		var children = item.children;
    	        		$.each(children,function(j,child){
    	        			let operationVal = child.id.split('-')[1];
    	        			operations.push(operationVal);
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
    	}
    	// 切换到当前tab
        element.tabChange('tabMain', layId);
    });
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
    
    exports('sys-user.view');
});