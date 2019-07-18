/**
 * 首页
 * @returns
 */
layui.use(['layer','jquery','element','table','form'], function(){
      var $ = layui.$;
      var layer = layui.layer;
      var element = layui.element;
   	  var table = layui.table;
   	  var form = layui.form;
   	  var formSelects = layui.formSelects;
   	  // 一些事件监听
   	  element.on('tab(mainTab)', function(data){
   	    console.log(data);
   	  });
   	  element.on('tabDelete(mainTab)', function(data){
   	    console.log(this); // 当前Tab标题所在的原始DOM元素
   	    console.log(data.index); // 得到当前Tab的所在下标
   	    console.log(data.elem); // 得到当前的Tab大容器
   	  });
   	  
   	  // 数据源管理表格
   	  table.render({
   	    elem: '#tbDatasource',
   	    cellMinWidth: 100,
   	    height: 312,
   	    url: AppSetting.rootUrl + '/datasource/page/',
   	    page: true,
   	    toolbar: '#barDatasource',
   	    defaultToolbar: ['filter', 'exports'],
   	    cols: [
   	      [
   	    	{field: 'ck', title: '', type: 'radio'},
   	    	{field: 'dsNo', title: '编码', sort: true},
   	        {field: 'name', title: '名称', sort: true},
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
   	        {field: 'remark', title: '备注'}
   	      ]
   	    ]
   	  }); 
   	  
      // 监听行双击事件
      table.on('rowDouble(datasource)', function(obj){
    	  var data = obj.data;
    	  layer.open({
   		    type: 1,
   		    area: ['700px', '300px'],
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
	   		       <td>${data.dsNo}</td>
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
      });
   	  
      // 监听工具条
      table.on('toolbar(datasource)', function(obj){
    	var checkStatus = table.checkStatus(obj.config.id);
    	var data = checkStatus.data[0];
    	var dsNo = data.dsNo;
    	
    	var layEvent = obj.event;
        if(layEvent === 'generate'){ // 生成代码
          var exist = $("li[lay-id='"+data.dsNo+"']").length;
          if(exist === 0){
       	    element.tabAdd('mainTab', {
           	  id: data.dsNo,
              title: data.name,
              content: `
              <form class="layui-form">
                <fieldset class="layui-elem-field">
            	  <legend>数据源</legend>
            	  
            	  <div class="layui-row">
            	  	<div class="layui-col-md4">
            	  	  <div class="layui-inline">
            	  	    <label class="layui-form-label">数据源编码</label>
            	  	    <div class="layui-input-inline">
                          <input type="text" name="dsNo" autocomplete="off" class="layui-input layui-bg-gray" value="${data.dsNo}" readonly="readonly"/>
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
                         <input type="text" name="groupId" lay-verify="required" placeholder="请输入groupId" autocomplete="off" class="layui-input"/>
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
                   <button class="layui-btn" lay-filter="formGenerate" lay-submit>生成</button>
                   <button class="layui-btn layui-btn-primary" type="reset">重置</button>
                 </div>
                </div>
              </div>
            </form>`});
       	    
       	    form.render();
       	    
        	$.ajax({
        		type: 'POST',
        		url: AppSetting.rootUrl + '/common/getDatabase',
        		async: false,
        		data: {'dsNo':dsNo},
        		// dataType:'application/json',
        		success: function(resp){
        			//$.each(resp,function(i,item){
        			//	selectOps += `<option value="${item.dbName}">${item.dbName}</option>`;
        			//});
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
           	    		url: AppSetting.rootUrl + '/common/getMysqlTables', 
           	    		async: false, 
           	    		data: {'dsNo': dsNo,'schemaName': val.value},
    				    //dataType:'application/json', 
           	    		success: function(resp){
           	    			//$('#selectTableNames').empty();
           	    			//$('#selectTableNames').append(`<option value="">空</option>`);
           	    			//$.each(resp,function(i,item){ 
           	    			//	$('#selectTableNames').append(`<option value="${item.tableName}">${item.tableComment}</option`);
           	    			//}); 
           	    			//form.render('select');
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
       	    
       	    element.tabChange('mainTab', data.dsNo);
          }else{
        	element.tabChange('mainTab', data.dsNo);
          }
        }
      });
      
      //监听提交
      form.on('submit(formGenerate)', function(data){
    	  var params = {
    			  'dsNo':data.field.dsNo,
    			  'isInit':data.field.isInit,
    			  'groupId':data.field.groupId,
    			  'artifactId':data.field.artifactId,
    			  'schemaName':data.field.schemaName,
    			  'tableNames':data.field.tableNames,
    			  'projectPath':data.field.projectPath
    	  		};
    	  $.ajax({
      		type:'POST',
      		url: AppSetting.rootUrl + '/common/generateApiCode',
      		data:params,
      		dataType:"json",
      		success:function(resp){
      			debugger
      		},
      		error: function(e){
      			console.log(e);
      		}
      	});
      });
}); 