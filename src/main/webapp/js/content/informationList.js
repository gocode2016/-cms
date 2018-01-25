$(function() {
	
	
});



//向编写资讯网页跳转
	


//删除文章
function remove(id){
	$.ajax({ 
		   
	       async : false,  
	       url : "/qianyue/information/removeInformation/",
	       data :    
	          {    
	    	   id :id,  
	          },
	          success:function(result){ 	       	    
	       	     if (result.pageCode="删除文章成功") {//根据返回值进行跳转
	       	    	alert("删除文章成功");     
	             }
	       	  window.location.href = '/qianyue/information/';  
	          }
		 });
}

//修改文章
function modifyInit(id){
	
	
	location.href = $("#basePath").val()+"/information/modifyInformation/"+id;	
}

//根据页码查找 page已经定好
function search(currentPage){
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
	
}