$(function() {
	var E = window.wangEditor
    var editor = new E('#editor')
    // 或者 var editor = new E( document.getElementById('#editor') )
	// 配置服务器端地址
    editor.customConfig.uploadImgServer = 'http://localhost:8081/qianyue/information/uploadForWang'
    // 将图片大小限制为 3M
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
    // 限制一次最多上传 5 张图片
    editor.customConfig.uploadImgMaxLength = 5
    //上传图片名
    editor.customConfig.uploadFileName = 'imgFile'
    //开启DEBUG模式
    editor.customConfig.debug = true
    
    editor.customConfig.uploadImgHeaders = {
    	'contentType': "application/json;charset=utf-8",
    	'type' : "POST",
    	'dataType' : "jsonp",
    	}
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        $("#text1").val(html)
    }
    
    editor.customConfig.uploadImgHooks = {
    		success: function (xhr, editor, result) {
    	        alert("上传成功");
    	        console.log(xhr);
    	    },
    	    fail: function (xhr, editor, result) {
    	        alert("图片插入失败");
    	        console.log(xhr);
    	    },
    	    error: function (xhr, editor) {
    	    	alert("图片上传失败");
    	    	console.log(xhr);
    	    },
    	    timeout: function (xhr, editor) {
    	    	alert("图片上传超时");
    	    },
		    customInsert: function (insertImg, result, editor) {
		    	
		    	for(var i=0;i<result.data.length;i++){
		    		var url = result.data[i];
			        insertImg(url);
		    	}
		        

		        // result 必须是一个 JSON 格式字符串！！！否则报错
		    }
	}

    editor.create()
});
var imageValue=null;

//提交表单
function submitInf(){
	
	
	
	if($("#id").val()){
		$("#informationForm").attr("action",$("#basePath").val() + "/information/updateInformation/"+$("#id").val());
		$("#informationForm").submit();
	//新写的文章
	}else{
		//文章已写完
		if($("#titleId").val()&&imageValue!=null&&$("#text1").val()){
			
			$("#informationForm").submit();
		//文章未写完
		}else{
			alert("请完善文章内容");
		}
	}

	
}

//选定图片】
function selectIma(){
	$("#imageFile").bind("change",function(e){
		imageValue=$("#imageFile").val();
		alert(imageValue);
	});
}

