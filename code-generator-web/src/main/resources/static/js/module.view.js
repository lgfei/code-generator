/**
 * 模块管理列表页
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
    // 模块管理表格
    table.render({
    	elem: '#tbModule',
    	cellMinWidth: 100,
    	url: AppSetting.rootUrl + '/module/page.json',
    	method: 'post',
    	contentType: 'application/json;charset-UTF-8',
    	page: true,
    	toolbar: '#barModule',
    	defaultToolbar: ['filter', 'exports'],
    	cols: [
    		[
    			{field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
    			{field: 'id', title: 'ID', hide: true, fixed: 'left'},
    			{field: 'moduleNo', title: '模块编码', fixed: 'left'},
    			{field: 'name', title: '名称', fixed: 'left'},
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
    	var layEvent = obj.event;
      	// 刷新
    	if(layEvent === 'btnRefresh'){
    		table.reload('tbModule');
    	}
    	// 新增
    	if(layEvent === 'btnAdd'){
    		layer.open({
    			title: '新增',
    			type: 2,
    			area: ['600px','250px'],
    			content: [AppSetting.rootUrl + '/module/add.htm','no']
			});
    	}
    	// 批量删除
    	if(layEvent === 'btnBatchDelete'){
    		layer.confirm('确定要删除吗?', function(index){
    			var checkStatus = table.checkStatus(obj.config.id);
    			var entityList = checkStatus.data;
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/module/removeList.json',{'entityList': entityList}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbModule');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    });
    
    // 监听表格行工具条
    table.on('tool(module)', function(obj){
    	var data = obj.data;
    	var dataId = data.id;
    	var moduleNo = data.moduleNo;
    	
    	var layEvent = obj.event;
    	var layId = 'main_list';
    	// 查看
    	if(layEvent === 'btnView'){
    		layId = 'btnView_'+moduleNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '查看('+moduleNo+')',
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
			   		       		<td>模块编码</td>
			   		       		<td>${data.moduleNo}</td>
			   		       		<td>名称</td>
			   		       		<td>${data.name}</td>
			   		     	</tr>
			   		     	<tr>
			   		       		<td>URL</td>
			   		       		<td>${data.url}</td>
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
    		layId = 'btnEdit_'+moduleNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '编辑('+moduleNo+')',
    				content: `
    				<form class="layui-form" lay-filter="${layId}_formEdit">
    					<div class="layui-row">
    						<div class="layui-col-md4">
    							<div class="layui-inline">
    								<label class="layui-form-label">模块编码<span style="color:red;">*</span></label>
    								<div class="layui-input-inline">
                            			<input type="text" name="moduleNo" value="${data.moduleNo}" lay-verify="required" autocomplete="off" class="layui-input layui-bg-gray" readonly="readonly"/>
                          			</div>
    							</div>
    						</div>
    						<div class="layui-col-md4">
        	  	        		<div class="layui-inline">
        	  	          			<label class="layui-form-label">名称<span style="color:red;">*</span></label>
        	  	          			<div class="layui-input-inline">
                						<input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"/>
                          			</div>
        	  	        		</div>
        	  	      		</div>
        	  	      		<div class="layui-col-md4">
        	  	        		<div class="layui-inline">
        	  	          			<label class="layui-form-label">URL<span style="color:red;">*</span></label>
        	  	          			<div class="layui-input-inline">
                						<input type="text" name="url" lay-verify="required" autocomplete="off" class="layui-input"/>
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
        	        		'name': data.field.name
                		}
                	common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/module/update.json',{'entity': entity}).then(function(resp){
                		if(resp.code === "0"){
                			table.reload('tbModule');
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
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/module/remove.json',{'entity': entity}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbModule');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    	// 分配用户
    	if(layEvent === 'btnAssignmentUser'){
    		layId = 'btnAssignmentUser_'+moduleNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '分配用户('+moduleNo+')',
    				content: `
    				<form class="layui-form" lay-filter="${layId}_formAssignmentUser">
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
				                      <select name="userNos" xm-select="${layId}_userNos" xm-select-direction="down"></select>
				                    </div>
				                </div>
						  	</div>    						
    					</div>    					
    					
    					<div class="layui-row">
						  	<div class="layui-col-md12">
						  		<div class="layui-form-item">
				                    <label class="layui-form-label">功能权限</label>
				                    <div class="layui-input-block">
				                      <select name="operations" xm-select="${layId}_operations" xm-select-direction="down"></select>
				                    </div>
				                </div>
						  	</div>    						
    					</div>
    					
    					<div class="layui-row">
	                  		<div class="layui-form-item">
		                  		<div class="layui-input-block">
		                    		<button class="layui-btn" lay-filter="${layId}_btnSubmitAssignmentUser" lay-submit>保存</button>
		                    		<button class="layui-btn layui-btn-primary" type="reset">重置</button>
		                  		</div>
		                	</div>
    					</div>
    				</form>`
    			});
    			
    			form.render(null, layId+'_formAssignmentUser');
    			
        		common.mySyncAjax('POST',AppSetting.rootUrl + '/sys-user/findModuleUsers.json',{'moduleNo': moduleNo}).then(function(resp){
        			var selectData = [];
        			$.each(resp['items'],function(i,item){
        				let obj = {"name": item.name, "value": item.userNo};
       	    			selectData.push(obj);
        			});
        			formSelects.data(layId+'_userNos', 'local', {
        			    arr: selectData
        			});
        			
        			var selectedItems = [];
        			$.each(resp['selected'],function(i,item){
        				selectedItems.push(item.userNo);
        			});
        			formSelects.value(layId+'_userNos', selectedItems);
        		});
        		
        		common.mySyncAjax('POST',AppSetting.rootUrl + '/operation/findModuleOperations.json',{'moduleNo': moduleNo}).then(function(resp){
        			var selectData = [];
        			var selectedItems = [];
        			$.each(resp,function(i,item){
        				let obj = {"name": item.name, "value": item.value};
       	    			selectData.push(obj);
       	    			selectedItems.push(item.value);
        			});
        			formSelects.data(layId+'_operations', 'local', {
        			    arr: selectData
        			});
        			formSelects.value(layId+'_operations', selectedItems);
        		});
        		
        		//监听提交
    	        form.on('submit('+layId+'_btnSubmitAssignmentUser)', function(data){
    	        	var entity = {
    	        				'moduleNo': data.field.moduleNo,
            	        		'userNo': data.field.userNos,
            	        		'operations': data.field.operations
    	        		}
    	        	common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/user-module-operation/saveModuleUserOperations.json',{"entity":entity}).then(function(resp){
    	        		layer.msg('保存成功！');
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
    
    exports('module.view');
}); 