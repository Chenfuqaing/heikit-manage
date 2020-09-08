layui.use('laydate', function(){
	  var laydate = layui.laydate;
	//日期时间选择器
	  laydate.render({
	    elem: '#applyDate'
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
    		{field:'applyUserName', title:'报销人', width: 100},
    		{field:'price', title:'报销金额', width: 100},
           	{field:'applyContent',title:'报销内容', width:150},
           	{field:'costUser',title:'费用归属', width:90},
           	{field:'projectName',title:'项目名称', width:100},
        	{field:'status',title:'状态', width:80, sort: true, templet: function(d){
        		var text = '未报销';
        		if(d.status == 0){
        			text = '待审核';
        			return '<span style="color: #ff0000;">'+ text +'</span>'
        		}
        		if(d.status == 1){
        			text = '已审核';
        			return '<span >'+ text +'</span>'
        		}
        		if(d.status == 2){
        			text = '拒绝';
        			return '<span >'+ text +'</span>'
        		}
        		if(d.status == 0){
        			text = '待审核';
        			return '<span style="color: #ff0000;">'+ text +'</span>'
        		}
        		return '<span>'+ text +'</span>'
            }},
           	{field:'number',title:'票据数量', width:90},
    		{field:'applyDate', title:'报销日期', width: 170},
           	{field:'message',title:'备注', width:150},
           	{field:'createDate',title:'创建时间', width:170, sort: true},
            {field:'7', title:'操作', width: 180, toolbar:'#toolbar-de'}
    	]]
    });
    
table.on('tool(table-init)', function(obj){
  var data = obj.data;
  if(obj.event === 'edit'){
      layer.open({
    	    type: 2,
    	    title: '修改报销信息',
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
	    title: '新增报销信息',
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
    	 var applyDates  = $('#applyDate').val().split(' - ');
		 var applyDateStart='';
		 var applyDateEnd='';
  	     if(applyDates.length>1){
  	    	applyDateStart = applyDates[0];
  	    	applyDateEnd = applyDates[1];
  	     }
    	table.reload('data-table',{
    		  where: { //设定异步数据接口的额外参数，任意设
    			 apply_user_name: $("#applyUserName").val(),
    			 cost_user: $("#costUser").val(),
    			 apply_date_start: applyDateStart,
    			 apply_date_end: applyDateEnd
    			 
    		  }, page: {
    		    curr: 1 //重新从第 1 页开始
    		  }
    		});
    });
});