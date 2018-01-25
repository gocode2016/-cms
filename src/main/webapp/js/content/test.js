$(function() {
	common.showMessage($("#message").val());
	 kedit('textarea[id="editor_id"]');
	 KindEditor.ready(function(K) {
		  var uploadbutton = K.uploadbutton({
		     button : K('#uploadButton')[0],
		     fieldName : 'imgFile',
		     url : 'http://localhost:8081/qianyue/information/upload',
		     afterUpload : function(data) {
		      if (data.error === 0) {
		       var url = K.formatUrl(data.url, 'absolute');
		       K('#url').val(url);
		      } else {
		       alert(data.message);
		      }
		     },
		     afterError : function(str) {
		      alert('error: ' + str);
		     }
		    });
		    uploadbutton.fileBox.change(function(e) {
		     uploadbutton.submit();
		    });
		    
		});
 	 
});
var html;
var editor;
var imageValue=null;

//加载编辑器
function kedit(kedit){	
	
    editor = KindEditor.create(kedit,{  
    	width : '750px',
    	height : '450px',
        resizeType: 0,       
        allowFileManager : true,
        uploadJson : 'http://localhost:8081/qianyue/information/upload',
        imageUploadLimit : 5,
        items: [
            'undo', 'redo', '|', 'preview', 'print', 'code', 'cut', 'copy', 'paste'
            , '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', '|', 'image', 'multiimage',
             'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'link', 'unlink', 
    ],
         
    });
    
   
    
    
}

    
//提交表单
function submitInf(){
	
	if($("#id").val()){
		editor.sync();
		$("#informationForm").attr("action",$("#basePath").val() + "/information/updateInformation/"+$("#id").val());
		$("#informationForm").submit();
	//新写的文章
	}else{
		//文章已写完
		if($("#titleId").val()&&imageValue!=null&&$("#editor_id").text()){
			editor.sync();
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
    
//修改文章
//if($("#id").val()){
//	editor.sync();
//	$("#informationForm").attr("action",$("#basePath").val() + "/information/updateInformation/"+$("#id").val());
//	$("#informationForm").submit();
////新写的文章
//}else{
//	//文章已写完
//	if($("#titleId").val()&&imageValue!=null&&$("#editor_id").text()){
//		editor.sync();
//		$("#informationForm").submit();
//	//文章未写完
//	}else{
//		alert("请完善文章内容");
//	}
//}
//


