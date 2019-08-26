/**
 * 数据源管理列表
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
   	// 数据源管理表格
   	table.render({
   	    elem: '#tbDatasource',
   	    cellMinWidth: 100,
   	    url: AppSetting.rootUrl + '/datasource/page.json',
   	    method: 'post',
   	    contentType: 'application/json;charset-UTF-8',
   	    page: true,
   	    toolbar: '#barDatasource',
   	    defaultToolbar: ['filter', 'exports'],
   	    cols: [
   	      [
   	    	{field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
   	    	{field: 'id', title: 'ID', hide: true, fixed: 'left'},
   	    	{field: 'datasourceNo', title: '编码', fixed: 'left'},
   	        {field: 'name', title: '名称', fixed: 'left'},
   	        {field: 'dbType', title: '数据库类型'},
   	        {field: 'type', title: '数据源类型'},
   	        {field: 'driver', title: '数据驱动'},
   	        {field: 'server', title: 'IP'},
   	        {field: 'port', title: '端口'},
   	        {field: 'username', title: '用户名'},
   	        {field: 'password', title: '密码'},
   	        {field: 'createUser', title: '创建人'},
   	        {field: 'createTime', title: '创建时间', sort: true},
   	        {field: 'updateUser', title: '修改人'},
   	        {field: 'updateTime', title: '修改时间', sort: true},
   	        {field: 'remark', title: '备注'},
   	        {field: 'opt', title: '操作', toolbar: '#barDatasourceCols', fixed: 'right', width: 250}
   	      ]
   	    ]
    }); 
   	  
    // 监听表格工具条
    table.on('toolbar(datasource)', function(obj){
      	var layEvent = obj.event;
      	// 刷新
    	if(layEvent === 'btnRefresh'){
    		table.reload('tbDatasource');
    	}
    	// 新增
    	if(layEvent === 'btnAdd'){
    		layer.open({
    			title: '新增',
    			type: 2,
    			area: ['600px','400px'],
    			content: [AppSetting.rootUrl + '/datasource/add.htm','no']
			});
    	}
    	// 批量删除
    	if(layEvent === 'btnBatchDelete'){
    		layer.confirm('确定要删除吗?', function(index){
    			var checkStatus = table.checkStatus(obj.config.id);
    			var entityList = checkStatus.data;
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/datasource/removeList.json',{'entityList': entityList}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbDatasource');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    	// 切换到列表tab
        element.tabChange('tabMain', 'main_list');
    });
   	  
    // 监听表格行工具条
    table.on('tool(datasource)', function(obj){
    	var data = obj.data;
    	var dataId = data.id;
    	var datasourceNo = data.datasourceNo;
    	
    	var layEvent = obj.event;
    	var layId = "main_list";
    	// 查看
    	if(layEvent === 'btnView'){
    		layId = 'btnView_'+datasourceNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '查看('+datasourceNo+')',
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
			   		       		<td>数据源编码</td>
			   		       		<td>${data.datasourceNo}</td>
			   		       		<td>名称</td>
			   		       		<td>${data.name}</td>
			   		     	</tr>
			   		     	<tr>
			   		       		<td>数据库类型</td>
		  		           		<td>${data.dbType}</td>
		  		           		<td>数据驱动</td>
				           		<td>${data.driver}</td>
			   		     	</tr>
			   		     	<tr>
			   		       		<td>IP</td>
				           		<td>${data.server}</td>
				           		<td>端口</td>
				           		<td>${data.port}</td>
		  		         	</tr>
		  		         	<tr>
				           		<td>用户名</td>
			               		<td>${data.username}</td>
			               		<td>密码</td>
			               		<td>${data.password}</td>
				         	</tr>
				         	<tr>
				           		<td>数据源类型</td>
		  		           		<td>${data.type}</td>
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
    		layId = 'btnEdit_'+datasourceNo;
    		var exist = $("li[lay-id='"+layId+"']").length;
    		if(exist === 0){
    			element.tabAdd('tabMain', {
    				id: layId,
    				title: '编辑('+datasourceNo+')',
    				content: `
    				<form class="layui-form" lay-filter="${layId}_formEdit">
    				    <div class="layui-row">
    				        <div class="layui-col-md4">
    				        	<div class="layui-inline">
        	  	          			<label class="layui-form-label">数据源编码<span style="color:red;">*</span></label>
        	  	          			<div class="layui-input-inline">
                            			<input type="text" name="datasourceNo" value="${data.datasourceNo}" lay-verify="required" autocomplete="off" class="layui-input layui-bg-gray" readonly="readonly"/>
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
        	  	          			<label class="layui-form-label">数据库类型<span style="color:red;">*</span></label>
        	  	          			<div class="layui-input-inline">
                            			<select name="dbType" lay-verify="required">
    										<option value="mysql">mysql</option>
    										<option value="oralce" disabled>oracle</option>
    									</select>
                          			</div>
        	  	        		</div>
        	  	      		</div>
    				    </div>
    				    
        	        	<br/>
        	  
	        	    	<div class="layui-row">
	        	  	  		<div class="layui-col-md4">
	        	  	    		<div class="layui-inline">
	        	  	      			<label class="layui-form-label">数据库驱动<span style="color:red;">*</span></label>
	        	  	      			<div class="layui-input-inline">
	                        			<input type="text" name="driver" lay-verify="required" autocomplete="off" class="layui-input"/>
	                      			</div>
	        	  	    		</div>
	        	  	  		</div>
	        	  	  		<div class="layui-col-md4">
	        	  	    		<div class="layui-inline">
	        	  	      			<label class="layui-form-label">IP<span style="color:red;">*</span></label>
	        	  	      			<div class="layui-input-inline">
	                        			<input type="text" name="server" lay-verify="required" autocomplete="off" class="layui-input"/>
	                      			</div>
	        	  	    		</div>
	        	  	  		</div>
	        	  	  		<div class="layui-col-md4">
	        	  	    		<div class="layui-inline">
	        	  	      			<label class="layui-form-label">端口<span style="color:red;">*</span></label>
	        	  	      			<div class="layui-input-inline">
	                        			<input type="text" name="port" lay-verify="required|number" autocomplete="off" class="layui-input"/>
	                      			</div>
	        	  	    		</div>
	        	  	  		</div>
	        	    	</div>
	        	  
	        	    	<br/>
	        	  	
	        	    	<div class="layui-row">
	        	  	  		<div class="layui-col-md4">
	        	  	    		<div class="layui-inline">
	        	  	      			<label class="layui-form-label">用户名<span style="color:red;">*</span></label>
	        	  	      			<div class="layui-input-inline">
	                        			<input type="text" name="username" lay-verify="required" autocomplete="off" class="layui-input"/>
	                      			</div>
	        	  	    		</div>
	        	  	  		</div>
	        	  	  		<div class="layui-col-md4">
	        	  	    		<div class="layui-inline">
	        	  	      			<label class="layui-form-label">密码</label>
	        	  	      			<div class="layui-input-inline">
	                        			<input type="text" name="password" autocomplete="off" class="layui-input"/>
	                      			</div>
	        	  	    		</div>
	        	  	  		</div>
	        	  	  		<div class="layui-col-md4">
	        	  	    		<div class="layui-inline">
	        	  	      			<label class="layui-form-label">数据源类型<span style="color:red;">*</span></label>
	        	  	      			<div class="layui-input-inline">
	                        			<input type="text" name="type" lay-verify="required" autocomplete="off" class="layui-input"/>
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
        	        		'dbType': data.field.dbType,
        	        		'driver': data.field.driver,
        	        		'server': data.field.server,
        	        		'port': data.field.port,
        	        		'username': data.field.username,
        	        		'password': data.field.password,
        	        		'type': data.field.type
                		}
                	common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/datasource/update.json',{'entity': entity}).then(function(resp){
                		if(resp.code === "0"){
                			table.reload('tbDatasource');
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
        		common.mySyncAjaxWithEntity('POST',AppSetting.rootUrl + '/datasource/remove.json',{'entity': entity}).then(function(resp){
            		if(resp.code === "0"){
            			table.reload('tbDatasource');
            			layer.msg('删除成功！');
            		}
            	});
    		    layer.close(index);
			});
    	}
    	// 生成代码
        if(layEvent === 'btnGenerate'){
    	    layId = 'btnGenerate_'+datasourceNo;
            var exist = $("li[lay-id='"+layId+"']").length;
            if(exist === 0){
            	element.tabAdd('tabMain', {
            		id: layId,
                    title: '生成代码('+datasourceNo+')',
                    content: `
                    <form class="layui-form" lay-filter="${layId}_formGenerate">
                    	<fieldset class="layui-elem-field">
            	        	<legend>数据源</legend>
            	  
            	        	<div class="layui-row">
            	  	      		<div class="layui-col-md4">
            	  	       			<div class="layui-inline">
            	  	          			<label class="layui-form-label">数据源编码</label>
            	  	          			<div class="layui-input-inline">
                                			<input type="text" name="datasourceNo" autocomplete="off" class="layui-input layui-bg-gray" readonly="readonly"/>
                              			</div>
            	  	        		</div>
            	  	      		</div>
            	  	      		<div class="layui-col-md4">
            	  	        		<div class="layui-inline">
            	  	          			<label class="layui-form-label">名称</label>
            	  	          			<div class="layui-input-inline">
                    						<input type="text" name="name" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
                              			</div>
            	  	        		</div>
            	  	      		</div>
            	  	      		<div class="layui-col-md4">
            	  	        		<div class="layui-inline">
            	  	          			<label class="layui-form-label">数据库类型</label>
            	  	          			<div class="layui-input-inline">
                                			<input type="text" name="dbType" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
                              			</div>
            	  	        		</div>
            	  	      		</div>
            	        	</div>
            	  
            	        	<br/>
            	  
		        	    	<div class="layui-row">
		        	  	  		<div class="layui-col-md4">
		        	  	    		<div class="layui-inline">
		        	  	      			<label class="layui-form-label">数据库驱动</label>
		        	  	      			<div class="layui-input-inline">
		                        			<input type="text" name="driver" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
		                      			</div>
		        	  	    		</div>
		        	  	  		</div>
		        	  	  		<div class="layui-col-md4">
		        	  	    		<div class="layui-inline">
		        	  	      			<label class="layui-form-label">IP</label>
		        	  	      			<div class="layui-input-inline">
		                        			<input type="text" name="server" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
		                      			</div>
		        	  	    		</div>
		        	  	  		</div>
		        	  	  		<div class="layui-col-md4">
		        	  	    		<div class="layui-inline">
		        	  	      			<label class="layui-form-label">端口</label>
		        	  	      			<div class="layui-input-inline">
		                        			<input type="text" name="port" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
		                      			</div>
		        	  	    		</div>
		        	  	  		</div>
		        	    	</div>
		        	  
		        	    	<br/>
		        	  	
		        	    	<div class="layui-row">
		        	  	  		<div class="layui-col-md4">
		        	  	    		<div class="layui-inline">
		        	  	      			<label class="layui-form-label">用户名</label>
		        	  	      			<div class="layui-input-inline">
		                        			<input type="text" name="username" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
		                      			</div>
		        	  	    		</div>
		        	  	  		</div>
		        	  	  		<div class="layui-col-md4">
		        	  	    		<div class="layui-inline">
		        	  	      			<label class="layui-form-label">密码</label>
		        	  	      			<div class="layui-input-inline">
		                        			<input type="text" name="password" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
		                      			</div>
		        	  	    		</div>
		        	  	  		</div>
		        	  	  		<div class="layui-col-md4">
		        	  	    		<div class="layui-inline">
		        	  	      			<label class="layui-form-label">数据源类型</label>
		        	  	      			<div class="layui-input-inline">
		                        			<input type="text" name="type" autocomplete="off" class="layui-input layui-bg-gray" disabled="disabled"/>
		                      			</div>
                    				</div>
		        	  	  		</div>
                    		</div>
		        	    	
                    	</fieldset>
		           
                    	<fieldset class="layui-elem-field">
                    		<legend>数据表</legend>
		           	
		           	  		<div class="layui-row">
		                		<div class="layui-col-md3">
		                  			<div class="layui-inline">
		                     			<label class="layui-form-label">isInit</label>
		                     			<div class="layui-input-inline">
		                       				<input type="checkbox" name="isInit" lay-skin="switch"/>
		                     			</div>
		                   			</div>
		                 		</div>  
		                 		<div class="layui-col-md4">
		                   			<div class="layui-inline">
		                     			<label class="layui-form-label">groupId<span style="color:red;">*</span></label>
                    					<div class="layui-input-inline">
		                       				<input type="text" name="groupId" autocomplete="off" class="layui-input layui-bg-gray" value="com.lgfei" disabled="disabled"/>
		                     			</div>
		                   			</div>
		                 		</div>
		                 		<div class="layui-col-md5">
		                   			<div class="layui-inline">
		                     			<label class="layui-form-label">artifactId<span style="color:red;">*</span></label>
		                      			<div class="layui-input-inline">
		                       				<input type="text" name="artifactId" lay-verify="required" placeholder="请输入artifactId" autocomplete="off" class="layui-input"/>
		                     			</div>
		                   			</div>
		                 		</div>
                    		</div>
		             
                    		<br/>
		             
                    		<div class="layui-row">
		                 		<div class="layui-col-md12">
		                   			<div class="layui-form-item">
		                     			<label class="layui-form-label">tableSchema<span style="color:red;">*</span></label>
		                     			<div class="layui-input-block">
		                       				<select name="schemaName" lay-verify="required" xm-select="${layId}_schemaName" xm-select-radio></select>
		                     			</div>
		                   			</div>
		                 		</div>
                    		</div> 
		           
                    		<br/>
		           
                    		<div class="layui-row">
                    			<div class="layui-col-md12">
		                			<div class="layui-form-item">
		                  				<label class="layui-form-label">tableNames<span style="color:red;">*</span></label>
		                  				<div class="layui-input-block">
		                    				<select name="tableNames" lay-verify="required" xm-select="${layId}_tableNames"></select>
		                  				</div>
                    				</div>
                    			</div>  
                    		</div>
		       
                    		<br/>
		         
                    		<div class="layui-row">
                    			<div class="layui-col-md12">
                    				<div class="layui-form-item">
		                 				<label class="layui-form-label">projectPath<span style="color:red;">*</span></label>
		                 				<div class="layui-input-block">
		                   					<input type="text" name="projectPath" lay-verify="required" placeholder="请输入projectPath" autocomplete="off" class="layui-input"/>
		                 				</div>
                    				</div>
                    			</div>           
                    		</div>
                    		
                    	</fieldset>
		         
                    	<div class="layui-row">
                    		<div class="layui-form-item">
                    			<div class="layui-input-block">
                    				<button class="layui-btn" lay-filter="${layId}_btnSubmitGenerate" lay-submit>生成</button>
                    				<button class="layui-btn layui-btn-primary" type="reset">重置</button>
                    			</div>
                    		</div>
                    	</div>
                    </form>`
                });
            	// 初始渲染
            	form.render(null, layId+'_formGenerate');
            	form.val(layId+'_formGenerate', data);
       	    
            	common.mySyncAjax('POST',AppSetting.rootUrl + '/getDatabase.json',{'datasourceNo':datasourceNo}).then(function(resp){
            		var selectData = [];
            		$.each(resp,function(i,item){
            			let obj = {"name": item.dbDesc, "value": item.dbName};
            			selectData.push(obj);
            		});
            		formSelects.data(layId+'_schemaName', 'local', {
            			arr: selectData
            		});
            	});
       	    
            	formSelects.on(layId+'_schemaName', function(id, vals, val, isAdd, isDisabled){
            		if(!isAdd){
            			formSelects.data('tableNames', 'local', {
            				arr: []
            			});
            		}else{
            			common.mySyncAjax('POST',AppSetting.rootUrl + '/getMysqlTables.json',{'datasourceNo': datasourceNo,'schemaName': val.value}).then(function(resp){
            				var selectData = [];
            				$.each(resp,function(i,item){ 
            					let obj = {"name": item.tableComment, "value": item.tableName};
            					selectData.push(obj);
            				}); 
            				formSelects.data(layId+'_tableNames', 'local', {
            					arr: selectData
            				});
            			});
            		}
            		return true;   
            	});
            }
          
            //监听提交
            form.on('submit('+layId+'_btnSubmitGenerate)', function(data){
            	var params = {
            			'datasourceNo':data.field.datasourceNo,
            			'isInit':data.field.isInit,
            			'groupId':data.field.groupId,
            			'artifactId':data.field.artifactId,
            			'schemaName':data.field.schemaName,
            			'tableNames':data.field.tableNames,
            			'projectPath':data.field.projectPath
        	  		};
            	common.myAsyncAjax('POST',AppSetting.rootUrl + '/generateApiCode.json',params).then(function(resp){
            		layer.msg('生成完成！');
            	});
            	// 阻止表单跳转
            	return false;
            });
        }
    	// 切换到当前tab
        element.tabChange('tabMain', layId);
    });
      
    // 隐藏第一个页签的关闭按钮
    $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
      
    exports('datasource.view');
}); 