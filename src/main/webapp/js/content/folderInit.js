$(function() {
//初始化ztree目录	
initTree();
//判断视频上传是否成功
JudgeVideoUpload();
//初始化页面加载图片列表
QianYueListForPic();
//点击图片列表或视频按钮事件监听
clickPicOrVid();
parseInt($("#totalPage").val())
                $("tbody a").lightBox({
                    overlayBgColor: "#666", //图片浏览时的背景色
                    overlayOpacity: 0.5, //背景色的透明度
                    containerResizeSpeed: 600 //图片切换时的速度
                })


});

//下一页点击事件
function nextPage(currentPage){
	
	if($("#kind").val()=="Image"){
		if($("#isAll").val()=="YES"){
			$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/picture/selectPicture/"+currentPage);
			$("#listForm").submit();
	    }else{
	    	var folderId=parseInt($("#folderId").val());
	    	$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/picture/"+folderId+"/"+currentPage);
			$("#listForm").submit();
	    }
		
	}else if($("#kind").val()=="Video"){
		if($("#isAll").val()=="YES"){
			$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/video/selectVideo/"+currentPage);
			$("#listForm").submit();
		}else{
			var folderId=parseInt($("#folderId").val());
	    	$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/video/"+folderId+"/"+currentPage);
			$("#listForm").submit();
		}
	}else if($("#kind").val()=="Directory"){
		
			$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/directory/selectDir/"+currentPage);
			$("#listForm").submit();
		
			
		}
	
}


//上一页点击事件
function lastPage(currentPage){
	//判断为图片列表
	if($("#kind").val()=="Image"){
		//判断为图片全文搜索
		if($("#isAll").val()=="YES"){
			$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/picture/selectPicture/"+currentPage);
			$("#listForm").submit();
		//判断为非全文搜索
		}else{
			var folderId=parseInt($("#folderId").val());
	    	$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/picture/"+folderId+"/"+currentPage);
			$("#listForm").submit();
		}
	//判断为视频列表
	}else if($("#kind").val()=="Video"){
		//判断为视频全文搜索
		if($("#isAll").val()=="YES"){
			$("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/video/selectVideo/"+currentPage);
			$("#listForm").submit();
	     }else{
	    	 var folderId=parseInt($("#folderId").val());
		    $("#listForm").attr("method","GET");
			$("#listForm").attr("action",$("#basePath").val()+"/picture/"+folderId+"/"+currentPage);
			$("#listForm").submit();
	     }
		
	}else if($("#kind").val()=="Directory"){
		
		$("#listForm").attr("method","GET");
		$("#listForm").attr("action",$("#basePath").val()+"/directory/selectDir/"+currentPage);
		$("#listForm").submit();
	
		
	}

}

//加载图片列表
function ImageList(){
	$("#listForm").attr("method","GET");
	$("#listForm").attr("action",$("#basePath").val()+"/picture/selectPicture");
	$("#listForm").submit();
}

//加载视频列表
function VideoList(){
	$("#listForm").attr("method","GET");
	$("#listForm").attr("action",$("#basePath").val()+"/video/selectVideo");
	$("#listForm").submit();
}

//加载目录列表
function DirList(){
	$("#listForm").attr("method","GET");
	$("#listForm").attr("action",$("#basePath").val()+"/directory/selectAllDir");
	$("#listForm").submit();
}

//删除图片或者视频
function remove(id){
	if($("#kind").val()=="Image"){
		
	}else if($("#kind").val()=="Video"){
		
	}
}



//初始化树
function initTree() {
	
	$.ajax({
		url:"/qianyue/directory/getInitTree", 
		success: function(data) {
	    
			zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);	
        }
		
	}); 
	
	var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {
     view: {  
        addHoverDom: addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件  
        removeHoverDom: removeHoverDom, //离开节点时的操作  
        dblClickExpand : false,  
        selectedMulti : false 
           },

   	 edit: {  
                enable: true, //zTree是否可编辑  
                showRemoveBtn: true,//是否显示删除节点按钮  
                showRenameBtn: true,//是否显示编辑按钮  
                removeTitle: "删除",  
                drag: {  
                      prev: false, //拖拽节点，是否允许放在目标节点前  
                      next: false, //拖拽节点，是否允许放在目标节点后  
                      inner: false //拖拽节点，是否允许放在目标节点里  
                },  
                //editNameSelectAll: true  
            },
   	  data: {  
   		         key:  
                 {  
                    name: "folderName"  //界面显示的名称参数  
                 },
   		  
                simpleData: {  
                    enable: true ,
                    idKey: "id",
                    pIdKey: "parentId",

                }  
            },
      callback : { 
    	  //右击显示菜单
    	  onRightClick : userRightClick,
          //重命名
    	  beforeRename: beforeRename,
    	  //删除节点前确认
    	  beforeRemove: zTreeBeforeRemove,
    	  //删除节点
    	  onRemove: zTreeOnRemove,
  
      }
            
      }
}

//展示文件夹视频
function showVideo(){
	var nodes = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes();
	$("#listForm").attr("method","GET");
	$("#listForm").attr("action",$("#basePath").val()+"/video/"+nodes[0].id);
	$("#listForm").submit();
}

//展示文件夹图片
function showImage(){
	var nodes = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes();
	$("#listForm").attr("method","GET");
	$("#listForm").attr("action",$("#basePath").val()+"/picture/"+nodes[0].id);
	$("#listForm").submit();
}


//上传图片窗口
function addImage(){
	var nodes = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes();
	$("#mymodal-data-pic").modal("toggle");
	initFileInput("file-pic", "/qianyue/picture/addPicture/",nodes[0].id);
}
//上传视频窗口
function addVideo(){
	var nodes = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes();
	var videotitle=null;
	
	$("#mymodal-data-video").modal("toggle");
	$("#message-folder-id-vid").val(nodes[0].id);
	$(".success-file-upload").click(function(){
		if($("#mainForm").valid()){
		   if($("#videoTitle").val()&&$("#videoFile").val()){
			   alert("请稍等,现在开始上传");
	            $("#mainForm").submit();
			}else{
				alert("标题或文件不能为空");
			}
		}
	});
}

//添加首图
function addHeadImage(){
	var nodes = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes();
	$("#mymodal-data-headImage").modal("toggle");
	$("#message-folder-id-headImage").val(nodes[0].id);
	$(".success-file-upload").click(function(){
		if($("#mainForm").valid()){
		   if($("#headImage").val()){
			  
	            $("#mainFormForHeadImage").submit();
	            $("body").unbind("click");
			}else{
				alert("文件不能为空");
			}
		}
	});
	
}
//按钮测试
function test(url){
	alert(url);
}
//通过列表添加首图
function addHeadImageForList(id){
	
	$("#mymodal-data-headImage").modal("toggle");
	$("#message-folder-id-headImage").val(id);
	$(".success-file-upload").click(function(){
		if($("#mainForm").valid()){
		   if($("#headImage").val()){
			  
	            $("#mainFormForHeadImage").submit();
	            $("body").unbind("click");
			}else{
				alert("文件不能为空");
			}
		}
	});
}

/**
 * 右击时定位右键菜单展示的位置并显示
 */
function rightClick(event,rMenuId) {
	$("#" + rMenuId).css({
		"top" : event.clientY + "px",
		"left" : event.clientX + "px",
		"visibility" : "visible"
	});
}


/**
 * 单击鼠标事件：
 * 在页面任意地方单击鼠标，关闭右键弹出的菜单
 */
function mousedown() {
	$("#rMenu").css({
		"visibility" : "hidden"
	});
}

/**
 * 鼠标划出右键菜单层时，去除“鼠标经过菜单时”的样式。
 */
function divOut() {
	$("body").bind("mousedown", mousedown);
	
	$(".rMenuLiMove").addClass("rMenuLi");
	$(".rMenuLiMove").removeClass("rMenuLiMove");
}


/**
 * 鼠标在菜单间移动时样式的切换
 */
function move(element) {
	$(".rMenuLiMove").addClass("rMenuLi");
	$(".rMenuLiMove").removeClass("rMenuLiMove");
	
	$(element).addClass("rMenuLiMove");
	$(element).removeClass("rMenuLi");
}

/**
 * 鼠标在弹出层上方时，解除鼠标按下的事件
 */
function divOver() {
	$("body").unbind("mousedown", mousedown);
}

/**
 * 在目录上右击显示右键菜单同时选中节点
 */
function userRightClick(event, treeId, treeNode) {
	console.log(treeNode);
	if(!treeNode) {
		return;
	}
	$.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
	rightClick(event,"rMenu");
	if(treeNode.isParent===true){
		$(".rMenuUi").hide();
	}else{
		//屏蔽掉平时鼠标右键菜单
		document.oncontextmenu=function(){return false;}
		$(".rMenuUi").show();
		
	}
}



//重命名
function beforeRename(treeId, treeNode, newName, isCancel){
	
	if (newName.length == 0) {  
		alert("节点名称不能为空."); 
		return false;
	}else{
		 var isDeled=false;
		 $.ajax({ 
			 type : "POST",    
	         async : false,  
	         url : "/qianyue/directory/updateTree",
	         data :    
	            {    
	                id : treeNode.id,  
	                folderName : newName  
	            },
	            success:function(result){ 
	            	console.log(result.pageCode)
	            	
	                if (result.pageCode == "UpdateSuccess") {
	                	initTree();
	                    isDeled= true;  
	                } else {  
	                	alert("更新失败");
	                    isDeled= false;  
	                }  
	            }
		 });
		 return isDeled; 
	}
	
	
}
   
   
//添加节点
var newCount = 0;
function addHoverDom(treeId, treeNode){
   var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
      + "' title='添加子节点' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if(btn)btn.bind("click",function(){
    	newCount++;
    	$.ajax({ 
			 type : "POST",    
	         async : false,  
	         url : "/qianyue/directory/updateTreeIsParent",
	         data :    
	            {    
	        	    isParent : treeNode.id,  
	            },
	            success:function(result){ 
	            	
	            	
	            	//插入子节点ajax
	            	$.ajax({ 
	       			 type : "POST",    
	       	         async : false,  
	       	         url : "/qianyue/directory/addChild",
	       	         data :    
	       	            {    
	       	        	    parentId : treeNode.id,  
	       	                name : "新节点"+newCount
	       	            },
	       	            success:function(data){ 
	       	            	
	       	            	if(""!=data){
	       	            	 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	       	            	 zTree.addNodes(treeNode, {id:data.pageCode, parentId:treeNode.id, folderName:"新节点" + (newCount)});
	       	            	 initTree();
	       	            	}
	       	                
	       	            }
	       		 });
	            	
                 
	            }
		 });
    	
    	  	
    });
}
//隐藏添加节点图标
function removeHoverDom(treeId, treeNode) {  
    $("#addBtn_"+treeNode.tId).unbind().remove();  
};  

//删除节点前确认
function zTreeBeforeRemove(treeId, treeNode) {
	console.log(treeId);
	console.log(treeNode);
	if(treeNode.id==1){
		alert("为根父节点,不可以删除");
		return false;		
	}else{
		return confirm(" 删除该节点,节点下所有内容将一并删除，是否确认要删除该节点");  
	}	 
}

//删除该节点及所有孩子节点
function zTreeOnRemove(event, treeId, treeNode) {
		
	
	$.ajax({ 
			 type : "POST",    
	         async : false,  
	         url : "/qianyue/directory/removeAll",
	         data :    
	            {    
	        	    id : treeNode.id,
	        	    parentId : treeNode.parentId,
	        	    
	            },
	            success:function(data){ 
	            	initTree();
	            }
		 });
}


//上传bootstrap初始化
function initFileInput(ctrlName, uploadUrl,id) {    
  var control = $('#' + ctrlName);  
  
  control.fileinput({
      language: 'zh', //设置语言
      uploadUrl: uploadUrl+id, //上传的地址
      allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
      showUpload: true, //是否显示上传按钮
      showPreview: true,
      showCaption: false,//是否显示标题
      browseClass: "btn btn-primary", //按钮样式             
      previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
      maxFileCount: 10, //表示允许同时上传的最大文件个数
      enctype: 'multipart/form-data',
      validateInitialCount:true,
      previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
      msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
  });
        
  
  $("#file-pic").on("fileuploaded", function (event, data) {
	       
	  });

  
}


//加载列表页forPicture
function QianYueListForPic(){
	$.ajax({ 
	    
       url : "/qianyue/picture/selectPicture",
       
          success:function(data){             		
        	console.log(data);
        	$.each(data, function(idx, obj) {
        		
        		$("tbody").append(
        				'<tr><td>'
        				+obj.path+
        				'</td><td>'
        				+obj.category+
        				'</td><td><a href="'
        				+obj.img+
        				'">查看原图</a></td><td>'
        				+obj.time+
        				'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-sm deletePicture" value="'
        				+obj.id+
        				'" type="button">删除</button></td></tr>'      		
        		)
        	});
        	
        	
          }
	 });
	$("body").unbind("click");
	$("body").one('click','.deletePicture',function(e){
			
		deleteOnePicture(e.target.value);
		
		
	});
	
}


//加载列表页forVideo
function QianYueListForVid(){
	$.ajax({ 
	    
       url : "/qianyue/video/selectVideo",
       
          success:function(data){             		
        	console.log(data);
        	$.each(data, function(idx, obj) {
        		
        		$("tbody").append(
        				'<tr><td>'
        				+obj.path+
        				'</td><td>'
        				+obj.category+
        				'</td><td>'
        				+obj.title+
        				'</td><td>'
        				+obj.time+
        				'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-sm deleteVideo" value="'
        				+obj.id+
        				'" type="button">删除</button></td></tr>'      		
        		)
        	});
        	
        	
          }
	 });
	$("body").unbind("click");
	$("body").one('click','.deleteVideo',function(e){
			
		deleteOneVideo(e.target.value);
		 
		
	});
	
}






//删除某张图片
function deleteOnePicture(id){
	
	$.ajax({ 
		 type : "POST",    
       async : false,  
       url : "/qianyue/picture/deleteOnePicture",
       data :    
          {    
      	    id : id,  
          },
          success:function(result){ 
       	    alert(result.pageCode);
       	 window.location.reload();
          }
	 });
}

//删除某个视频
function deleteOneVideo(id){
	
	$.ajax({ 
		 type : "POST",    
       async : false,  
       url : "/qianyue/video/deleteOneVideo",
       data :    
          {    
      	    id : id,  
          },
          success:function(result){ 
       	    alert(result.pageCode);
       	 window.location.reload();
       	    
          }
	 });
}

function QianYueListForPic(){
	
}

function clickPicOrVid(){
	
	$(".pic-list").unbind("click");
	$(".pic-list").click(function(e){
		
		$(".panel-body").load(location.href+" .panel-body"); 
		QianYueListForPic();
		
	});
	$(".vid-list").unbind("click");
	$(".vid-list").click(function(e){
		
		$(".panel-body").load(location.href+" .panel-body"); 
		QianYueListForVid();
		
	});
	
	
}

function JudgeVideoUpload(){
	if($("#messageUploadVid").val()!=""){
        alert($("#messageUploadVid").val());
	}
}

//查看图片弹出框
function popPictureShow(url){
	
	$("#popPictureShow").modal("toggle");
	$("#picUrl").attr('src',url); 
}


