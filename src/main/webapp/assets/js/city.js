$(function(){
	$.ajax({
		type:'post',
		url:'city/province',
		contentType:'application/json;charset=utf-8',
		//数据格式是json串
		success:function(data){//返回json结果
			for(var i=0;i<data.length;i++){
				$("#provincelist").append("<div class='item' data-value='"+data[i].id+"'>"+data[i].provinceName+"</div>");
			}
		}
	});
	
	 $("#province").change(function(){
		 $("#city").val('');
		 $("#city").next(".text").text('选择城市');
		 $("#citylist").empty();
		 var provinceId=$("#province").val();
		 //alert(provinceId);
		 $.ajax({
				type:'post',
				url:'city/city',
				contentType:'application/json;charset=utf-8',
				//数据格式是json串，市区
				data:provinceId,
				success:function(data){//返回json结果
					for(var i=0;i<data.length;i++){
						$("#citylist").append("<div class='item' data-value='"+data[i].cityName+"'>"+data[i].cityName+"</div>");
					}
				}
			});
	 });
})