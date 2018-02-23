$(function(){
	
	$(".jobupdate").css("display","none");
	$(".houseupdate").css("display","none");
	$(".weatherupdate").css("display","none");
	$(".aqiupdate").css("display","none");
	$(".viewupdate").css("display","none");
	$(".foodupdate").css("display","none");
	
	//工作数据更新---------------------------------
	$("#jobupdate").click(function(){
		var cityname=$("#city").val();
		
		var jobname=$("#position").val();
		
		if(cityname==""){
			 alert("请选择更新的城市");
			 return ;
		 }
		 if(jobname==""){
			 alert("输入您要更新的职位");
             return ;
		 }
		 $("#jobupdate").css("display","none");
		 $(".jobupdate").css("display","block");
		 
		 $.ajax({
				type:'post',
				url:'job/insertJob',
				contentType:'application/json;charset=utf-8',
				//数据格式是json串，商品信息
				data:'{"cityname":"'+cityname+'","name":"'+jobname+'"}',
				success:function(data){
					alert(data);
					$("#jobupdate").css("display","block");
					$(".jobupdate").css("display","none");
				}
			})
	})
})