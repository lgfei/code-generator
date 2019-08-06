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
   	    page: true,
   	    toolbar: '#barDatasource',
   	    defaultToolbar: ['filter', 'exports'],
   	    cols: [
   	      [
   	    	{field: 'ck', title: '', type: 'checkbox', fixed: 'left'},
   	    	{field: 'datasourceNo', title: '编码', sort: true, fixed: 'left'},
   	        {field: 'name', title: '名称', sort: true, fixed: 'left'},
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
    	  var checkStatus = table.checkStatus(obj.config.id);
      	  var data = checkStatus.data;
      });
   	  
      // 监听表格行工具条
      table.on('tool(datasource)', function(obj){
    	var data = obj.data;
    	var datasourceNo = data.datasourceNo;
    	
    	var layEvent = obj.event;
    	// 查看
    	if(layEvent === 'btnView'){
    		var layId = 'btnView-'+datasourceNo;
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
    		// 切换到当前tab
	        element.tabChange('tabMain', layId);
    	}
    	// 生成代码
    	else if(layEvent === 'btnGenerate'){ 
          var layId = 'btnGenerate-'+datasourceNo;
          var exist = $("li[lay-id='"+layId+"']").length;
          if(exist === 0){
       	    element.tabAdd('tabMain', {
           	  id: layId,
              title: '生成代码('+datasourceNo+')',
              content: `
              <form class="layui-form" lay-filter="formGenerate">
                <fieldset class="layui-elem-field">
            	  <legend>数据源</legend>
            	  
            	  <div class="layui-row">
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">数据源编码</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="datasourceNo" autocomplete="off" class="layui-input layui-bg-gray" value="${data.datasourceNo}" readonly="readonly"/>
                        </div>
            	  	  </div>
            	  	</div>
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">名称</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="name" autocomplete="off" class="layui-input layui-bg-gray" value="${data.name}" disabled="disabled"/>
                        </div>
            	  	  </div>
            	  	</div>
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">数据库类型</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="dbType" autocomplete="off" class="layui-input layui-bg-gray" value="${data.dbType}" disabled="disabled"/>
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
                          <input type="text" name="driver" autocomplete="off" class="layui-input layui-bg-gray" value="${data.driver}" disabled="disabled"/>
                        </div>
            	  	  </div>
            	  	</div>
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">IP</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="server" autocomplete="off" class="layui-input layui-bg-gray" value="${data.server}" disabled="disabled"/>
                        </div>
            	  	  </div>
            	  	</div>
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">端口</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="port" autocomplete="off" class="layui-input layui-bg-gray" value="${data.port}" disabled="disabled"/>
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
                          <input type="text" name="username" autocomplete="off" class="layui-input layui-bg-gray" value="${data.username}" disabled="disabled"/>
                        </div>
            	  	  </div>
            	  	</div>
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">密码</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="password" autocomplete="off" class="layui-input layui-bg-gray" value="${data.password}" disabled="disabled"/>
                        </div>
            	  	  </div>
            	  	</div>
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">数据源类型</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="type" autocomplete="off" class="layui-input layui-bg-gray" value="${data.type}" disabled="disabled"/>
                        </div>
            	  	  </div>
            	  	</div>
            	  </div>
            	  
            	  <br/>
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
                         <select name="schemaName" lay-verify="required" xm-select="schemaName" xm-select-radio></select>
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
                      <select name="tableNames" lay-verify="required" xm-select="tableNames"></select>
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
                   <button class="layui-btn" lay-filter="btnSubmitGenerate" lay-submit>生成</button>
                   <button class="layui-btn layui-btn-primary" type="reset">重置</button>
                 </div>
                </div>
              </div>
            </form>`});
       	    
       	    form.render(null, 'formGenerate');
       	    
        	$.ajax({
        		type: 'POST',
        		url: AppSetting.rootUrl + '/getDatabase.json',
        		async: false,
        		data: {'datasourceNo':datasourceNo},
        		dataType:'json',
        		success: function(resp){
        			var selectData = [];
        			$.each(resp,function(i,item){
        				let obj = {"name": item.dbDesc, "value": item.dbName};
       	    			selectData.push(obj);
        			});
        			formSelects.data('schemaName', 'local', {
        			    arr: selectData
        			});
        		},
        		error: function(e){
        			console.log(e);
        		}
        	});      	    
       	    
        	formSelects.on('schemaName', function(id, vals, val, isAdd, isDisabled){
        		if(!isAdd){
        			formSelects.data('tableNames', 'local', {
   	    			    arr: []
   	    			});
        		}else{
           	    	$.ajax({ 
           	    		type: 'POST', 
           	    		url: AppSetting.rootUrl + '/getMysqlTables.json', 
           	    		async: false, 
           	    		data: {'datasourceNo': datasourceNo,'schemaName': val.value},
           	    		dataType:'json', 
           	    		success: function(resp){
           	    			var selectData = [];
           	    			$.each(resp,function(i,item){ 
               	    			let obj = {"name": item.tableComment, "value": item.tableName};
               	    			selectData.push(obj);
           	    			}); 
           	    			formSelects.data('tableNames', 'local', {
           	    			    arr: selectData
           	    			});
           	    		}, 
           	    		error: function(e){ 
           	    			console.log(e); 
           	    		} 
           	    	});        			
        		}
        	    return true;   
        	});
          }
          // 切换到当前tab
          element.tabChange('tabMain', layId);
        }
      });
      
      //监听提交
      form.on('submit(btnSubmitGenerate)', function(data){
    	  var params = {
    			  'datasourceNo':data.field.datasourceNo,
    			  'isInit':data.field.isInit,
    			  'groupId':data.field.groupId,
    			  'artifactId':data.field.artifactId,
    			  'schemaName':data.field.schemaName,
    			  'tableNames':data.field.tableNames,
    			  'projectPath':data.field.projectPath
    	  		};
    	  var loadIndex;
    	  $.ajax({
      		type:'POST',
      		url: AppSetting.rootUrl + '/generateApiCode.json',
      		data: params,
      		dataType: "json",
      		beforeSend: function(){
      			// 打开加载框
      			loadIndex = layer.load();
      		},
      		success:function(resp){
      			layer.alert('生成完成！');
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
      
      // 隐藏第一个页签的关闭按钮
      $(".layui-tab ul").children('li').first().children('.layui-tab-close').css("display",'none');
      
      exports('datasource.view');
}); 