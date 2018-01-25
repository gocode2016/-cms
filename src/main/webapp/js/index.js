$(function(){
	common.showMessage($("#message").val());
	$("#modifyWindow").validate({
		rules:{
			"oldPassword" : "required",
			"newPassword" : "required",
			"newPasswordAgain" : "required",
			},
			messages : {
				"oldPassword" : "不能为空 ",
				"newPassword" : "不能为空 ",
				"newPasswordAgain" : "不能为空 ",
				
			}
		
		
	});
});

/**
 * 方法描述:单击子菜单（页面左部菜单），初始化主页面
 */
function clickMenu(element,path) {
	// 将其他有[选中样式]的节点的样式清空
	$("#subMenuDiv").find(".on").attr("class","");
	// 将当前单击的节点置为[选中样式]
	$(element).children().attr("class","on");
	// 按指定地址加载主页面(iframe)
	$("#mainPage").attr("src",path);
}

/**
* 打开密码修改弹出层
*/
function openAddDiv(){
	$("#mengban").css("visibility","visible");
	$(".wishlistBox").show();
	$(".wishlistBox").find(".persongRightTit").html("&nbsp;&nbsp;修改密码");
	$("#submitVal").show();
}

/**
* 关闭密码修改弹出层
*/
function closeDiv(){
	$("#mengban").css("visibility","hidden");
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPasswordAgain").val("");
	$(".wishlistBox").hide();
}

function submitPw(){
	var oldPassword=$("#oldPassword").val();
	alert("原密码:"+oldPassword);
	var newPassword=$("#newPassword").val();
	alert("新密码:"+newPassword);
	var newPasswordAgain=$("#newPasswordAgain").val();
	alert("新密码:"+newPasswordAgain);
	if(oldPassword==""||newPassword==""||newPasswordAgain==""){
        alert("内容不能为空");
    }else if(oldPassword==newPassword||oldPassword==newPasswordAgain){
		alert("原密码与新密码重复");
	}else if(newPassword!=newPasswordAgain){
		alert("两次输入新密码不相同");
	}else{
		
		$("#modifyWindow").submit();
	}
    
   
	
}
function logoutSystem(){
	if(confirm('您确认退出系统?')){
		$.ajax({ 
			 type : "POST",    
	         async : false,  
	         url : "/qianyue/login/quitSystem",
	         
	            success:function(result){ 
	            	window.location.href = '/qianyue/login';
	            }
		 });
	};
}

