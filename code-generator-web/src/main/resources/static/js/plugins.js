//子页面不用iframe，用div展示
var closableTab = {
	
    //添加tab
	addTab:function(tabItem){ //tabItem = {id,name,url,closable}

		var id = "tab_seed_" + tabItem.id;
		var container ="tab_container_" + tabItem.id;

		$("li[id^=tab_seed_]").removeClass("active");
		$("div[id^=tab_container_]").removeClass("active");

		if(!$('#'+id)[0]){
			var li_tab = '<li role="presentation" class="" id="'+id+'"><a href="#'+container+'"  role="tab" data-toggle="tab">'+tabItem.name;
			if(tabItem.closable){
				li_tab = li_tab + '<i class="glyphicon glyphicon-remove small" tabclose="'+id+'"  onclick="closableTab.closeTab(this)"></i></a></li> ';
			}else{
				li_tab = li_tab + '</a></li>';
			}
		
		 	var tabpanel = '<div role="tabpanel" class="tab-pane" id="'+container+'" style="width: 100%;">'+
	    					  '正在加载...'+
	    				   '</div>';


			$('.nav-tabs').append(li_tab);
			$('.tab-content').append(tabpanel);
			$('#'+container).load(tabItem.url,function(response,status,xhr){
				if(status=='error'){//status的值为success和error，如果error则显示一个错误页面
					$(this).html(response);
				}
			});
		}
		$("#"+id).addClass("active");
		$("#"+container).addClass("active");
	},

	//关闭tab
	closeTab:function(item){
		var val = $(item).attr('tabclose');
		var containerId = "tab_container_"+val.substring(9);
   	    
   	    if($('#'+containerId).hasClass('active')){
   	    	$('#'+val).prev().addClass('active');
   	    	$('#'+containerId).prev().addClass('active');
   	    }


		$("#"+val).remove();
		$("#"+containerId).remove();
	}
};

(function ($) {
   /*//初始化
   $(".selectpicker").each(function () {
       var target = $(this);
       target.attr("title", $.fn.select.defaults.placeholder);
       target.selectpicker();
       var multiple = target.attr('multiple');
       if(multiple && multiple === 'multiple'){
    	   var option = $('<option></option>');
           option.attr('value', '');
           option.text('请选择');
           target.append(option);
       }
   });*/
	
    //1.定义jquery的扩展方法bootstrapSelect
   $.fn.bootstrapSelect = function (options, param) {
       if (typeof options == 'string') {
           return $.fn.bootstrapSelect.methods[options](this, param);
       }
       //2.将调用时候传过来的参数和default参数合并
       options = $.extend({}, $.fn.bootstrapSelect.defaults, options || {});
       //3.添加默认值
       var target = $(this);
       if (!target.hasClass("selectpicker")) target.addClass("selectpicker");
       target.attr('valuefield', options.valueField);
       target.attr('textfield', options.textField);
       //target.empty();
       //4.判断用户传过来的参数列表里面是否包含数据data数据集，如果包含，不用发ajax从后台取，否则否送ajax从后台取数据
       if (options.data) {
           init(target, options.data);
       }
       else {
    	   if(typeof(options.onBeforeLoad) === "function"){
    		   options.onBeforeLoad.call(target, options.param);
    	   }
           if (!options.url) return;
           $.ajaxSettings.async = false;
           $.getJSON(options.url, options.param, function (data) {
               init(target, data);
           });
       }
       
       function init(target, data) {
    	   target.empty();
    	   target.selectpicker('destroy');
    	   var multiple = target.attr('multiple');
    	   if(!multiple){
    		   var option = $('<option></option>');
               option.attr('value', '');
               option.text('请选择');
               target.append(option); 
    	   }
           $.each(data, function (i, item) {
               var option = $('<option></option>');
               option.attr('value', item[options.valueField]);
               option.text(item[options.textField]);
               target.append(option);
           });
           if(typeof(options.onLoadSuccess) === "function"){
        	   options.onLoadSuccess.call(target);
           }
       }
       
       target.unbind("change");
       target.on("change", function (e) {
           if (typeof(options.onChange) === "function"){
        	   return options.onChange(target.val());
           }
       });
       // 集成selectpicker的options
       if(options.options){
    	   target.selectpicker(options.options);
       }else{
    	   target.selectpicker();
       }
   }

   //5.如果传过来的是字符串，代表调用方法。
   $.fn.bootstrapSelect.methods = {
       getValue: function (jq) {
           return jq.val();
       },
       setValue: function (jq, param) {
           jq.val(param);
       },
       load: function (jq, url) {
           $.getJSON(url, function (data) {
               //jq.empty();
               $.each(data, function (i, item) {
                   var option = $('<option></option>');
                   option.attr('value', item[jq.attr('valuefield')]);
                   option.text(item[jq.attr('textfield')]);
                   jq.append(option);
               });
           });
       }
   };

   //6.默认参数列表
   $.fn.bootstrapSelect.defaults = {
       url: null,
       param: null,
       data: null,
       valueField: 'value',
       textField: 'text',
       placeholder: '请选择',
   };
   
})(jQuery);