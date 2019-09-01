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
 	var common = layui.common;
 	
 	var AppSetting = common.AppSetting;
    // 用户管理表格
    table.render({
    	elem: '#tbOperation',
    	cellMinWidth: 100,
    	url: AppSetting.rootUrl + '/operation/page.json',
    	method: 'post',
    	contentType: 'application/json;charset-UTF-8',
    	page: true,
    	toolbar: '#barOperation',
    	defaultToolbar: ['filter', 'exports'],
    	cols: [
    		[
    			{field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
    			{field: 'id', title: 'ID', hide: true, fixed: 'left'},
    			{field: 'operationNo', title: '按钮编码', fixed: 'left'},
    			{field: 'name', title: '名称', fixed: 'left'},
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
    	var layEvent = obj.event;
      	// 刷新
    	if(layEvent === 'btnRefresh'){
    		table.reload('tbOperation');
    	}
    	// 新增
    	if(layEvent === 'btnAdd'){
    		layer.open({
    			title: '新增',
    			type: 2,
    			area: ['600px','250px'],
    			content: [AppSetting.rootUrl + '/operation/add.htm','no']
			});
    	}
    	// 批量删除
    	if(layEvent === 'btnBatchDelete'){
    		layer.confirm('确定要删除吗?', function(index){
    			var checkStatus = table.checkStatus(obj.config.id);
    			var entityList = checkStatus.data;
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/operation/removeList.json',{'entityList': entityList}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbOperation');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    });
    
    // 监听表格行工具条
    table.on('tool(operation)', function(obj){
    	var data = obj.data;
    	var dataId = data.id;
    	var operationNo = data.operationNo;
    	
    	var layEvent = obj.event;
    	var layId = 'main_list';
    	// 查看
    	if(layEvent === 'btnView'){
    		layId = 'btnView_'+operationNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '查看('+operationNo+')',
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
			   		       		<td>按钮编码</td>
			   		       		<td>${data.operationNo}</td>
			   		       		<td>名称</td>
			   		       		<td>${data.name}</td>
			   		     	</tr>
			   		     	<tr>
			   		       		<td>权限值</td>
			   		       		<td>${data.value}</td>
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
    		layId = 'btnEdit_'+operationNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '编辑('+operationNo+')',
    				content: `
    				<form class="layui-form" lay-filter="${layId}_formEdit">
    					<div class="layui-row">
    						<div class="layui-col-md4">
    							<div class="layui-inline">
    								<label class="layui-form-label"><span style="color:red;">*</span>按钮编码</label>
    								<div class="layui-input-inline">
                            			<input type="text" name="operationNo" value="${data.moduleNo}" lay-verify="required" autocomplete="off" class="layui-input layui-bg-gray" readonly="readonly"/>
                          			</div>
    							</div>
    						</div>
    						<div class="layui-col-md4">
        	  	        		<div class="layui-inline">
        	  	          			<label class="layui-form-label"><span style="color:red;">*</span>名称</label>
        	  	          			<div class="layui-input-inline">
                						<input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"/>
                          			</div>
        	  	        		</div>
        	  	      		</div>
        	  	      		<div class="layui-col-md4">
        	  	        		<div class="layui-inline">
        	  	          			<label class="layui-form-label"><span style="color:red;">*</span>权限值</label>
        	  	          			<div class="layui-input-inline">
                						<input type="text" name="value" lay-verify="required" autocomplete="off" class="layui-input"/>
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
        	        		'name': data.field.name,
        	        		'value': data.field.value
                		}
                	common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/operation/update.json',{'entity': entity}).then(function(resp){
                		if(resp.code === "0"){
                			table.reload('tbOperation');
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
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/operation/remove.json',{'entity': entity}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbOperation');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    	// 切换到当前tab
        element.tabChange('tabMain', layId);
    });    
    
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
    
    exports('operation.view');
}); 