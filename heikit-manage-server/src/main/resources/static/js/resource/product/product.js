layui.use('laydate', function(){
	  var laydate = layui.laydate;
	//日期时间选择器
	  laydate.render({
	    elem: '#createDate'
	    ,range: true
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
    		{field:'code', title:'产品编号', width: 170},
    		{field:'title', title:'活动标题', width: 200},
           	{field:'size',title:'大小', width:155},
           	{field:'labels',title:'标签', width:100},
        	{field:'gameType',title:'类型', width:80, sort: true, templet: function(d){
        		var text = '是';
        		if(d.isValid == 1){
        			text = '否';
        			return '<span style="color: #ff0000;">'+ text +'</span>'
        		}
        		return '<span>'+ text +'</span>'
            }},
           	{field:'createDate',title:'创建时间', width:170, sort: true},
            {field:'7', title:'操作', width: 180, toolbar:'#toolbar-de'}
    	]]
    });
    
table.on('tool(table-init)', function(obj){
  var data = obj.data;
  if(obj.event === 'edit'){
      layer.open({
    	    type: 2,
    	    title: '修改CPL任务',
    	    shadeClose: true,
    	    shade: 0.8,
    	    area: ['50%', '80%'],
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
	    title: '添加CPL任务',
	    shadeClose: true,
	    shade: 0.8,
	    area: ['65%', '75%'],
	    content: 'pre_add.json', //iframe的url
		success:function(layero,index){
			debugger;
	    },
	    end : function() {
			table.reload('data-table',{});
		}
	}); 
});


     $('#search').on('click', function(){
    	 var time  = $('#createDate').val().split(' - ');
		 var createDateStart='';var createDateEnd='';
  	     if(time.length>1){
  	    	createDateStart = time[0];
  	    	createDateEnd = time[1];
  	     }
    	table.reload('data-table',{
    		  where: { //设定异步数据接口的额外参数，任意设
    			 name: $("#name").val(),
    			 create_date_start: createDateStart,
    			 create_date_end: createDateEnd,
    			 
    		  }, page: {
    		    curr: 1 //重新从第 1 页开始
    		  }
    		});
    });
});