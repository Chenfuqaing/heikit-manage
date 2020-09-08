layui.use('laydate', function(){
	  var laydate = layui.laydate;
	//日期时间选择器
	  laydate.render({
	    elem: '#signDate'
	    ,range: true
	    ,trigger:'click'
	  });
});

layui.use('table', function() {
    var table = layui.table;
    
    table.render({
    	elem: '#data-table',
    	height: 'full-75',
    	url: 'get_json.json',
    	page: true,
    	cols: [[
    		{field:'id', title:'序号', width: 80,fixed:'left'},
    		{field:'companyName', title:'签订单位', width: 100},
           	{field:'projectName',title:'项目名称', width:100},
           	{field:'projectName',title:'合同编号', width:100},
           	{field:'signDate',title:'合同签订时间', width:170, sort: true},
           	{field:'userId',title:'业务员', width:150},
           	{field:'content',title:'合同内容', width:90},
           	{field:'price',title:'合同金额', width:90},
           	{field:'payMethod',title:'付款方式', width:150},
    		{field:'receivePrice', title:'已收款', width: 170},
           	{field:'receivablePrice',title:'应收款', width:150},
        	{field:'status',title:'状态', width:80, sort: true, templet: function(d){
        		var text = '';
        		if(d.status == 0){
        			text = '未完成';
        			return '<span >'+ text +'</span>'
        		}
        		if(d.status == 1){
        			text = '执行中';
        			return '<span style="color: #ff0000;">'+ text +'</span>'
        		}
        		if(d.status == 2){
        			text = '已完成';
        			return '<span >'+ text +'</span>'
        		}
        		return '<span>'+ text +'</span>'
            }},
        	{field:'type',title:'合同类型', width:80, sort: true, templet: function(d){
        		var text = '';
        		if(d.type == 0){
        			text = '销售';
        			return '<span >'+ text +'</span>'
        		}
        		if(d.type == 1){
        			text = '售后';
        			return '<span >'+ text +'</span>'
        		}
        		if(d.type == 2){
        			text = '改造';
        			return '<span >'+ text +'</span>'
        		}
        		if(d.type == 3){
        			text = '配件';
        			return '<span >'+ text +'</span>'
        		}
        		if(d.type == 4){
        			text = '其它';
        			return '<span >'+ text +'</span>'
        		}
        		return '<span>'+ text +'</span>'
            }},
            {field:'7', title:'操作', width: 180, toolbar:'#toolbar-de'}
    	]]
    });
    
table.on('tool(table-init)', function(obj){
  var data = obj.data;
  if(obj.event === 'edit'){
      layer.open({
    	    type: 2,
    	    title: '修改合同信息',
    	    shadeClose: true,
    	    shade: 0.8,
    	    area: ['85%', '85%'],
    	    content: 'pre_edit.json?id='+data.id,
    	    end : function() {
				table.reload('data-table',{});
			}
    	});
    }
  });
    
$('#add').on('click', function(){
  layer.open({
	    type: 2,
	    title: '新增合同信息',
	    shadeClose: true,
	    shade: 0.8,
	    area: ['85%', '85%'],
	    content: 'pre_add.json', //iframe��url
		success:function(layero,index){
	    },
	    end : function() {
			table.reload('data-table',{});
		}
	}); 
});


     $('#search').on('click', function(){
    	 var signDates  = $('#signDate').val().split(' - ');
		 var signDateStart='';
		 var signDateEnd='';
  	     if(signDates.length>1){
  	    	signDateStart = signDates[0];
  	    	signDateEnd = signDates[1];
  	     }
    	table.reload('data-table',{
    		  where: { //设定异步数据接口的额外参数，任意设
    			 project_name: $("#projectName").val(),
    			 sign_date_start: signDateStart,
    			 sign_date_end: signDateEnd
    			 
    		  }, page: {
    		    curr: 1 //重新从第 1 页开始
    		  }
    		});
    });
});