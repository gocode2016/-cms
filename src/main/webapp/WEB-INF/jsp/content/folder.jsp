<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="tag.jsp"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>目录页</title>
    <%@include file="head.jsp"%>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<link href="${basePath}/css/zTreeStyle/metroStyle.css" type="text/css" rel="stylesheet" />


<link href="${basePath}/css/fileinput.css" type="text/css" rel="stylesheet" />
<link href="${basePath}/css/rmenu.css" type="text/css" rel="stylesheet" />
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="${basePath }/js/common/jquery.ztree.all.js" type="text/javascript"></script>
<script src="${basePath}/js/common/validation/jquery.validate.js" type="text/javascript"></script>
<script src="${basePath}/js/common/validation/messages_zh.js" type="text/javascript"></script>
 <script src="${basePath }/js/common/json2.js" type="text/javascript"></script> 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script src="${basePath }/js/content/folderInit.js" type="text/javascript"></script>
<script src="${basePath }/js/content/fileinput.js" type="text/javascript"></script>
<script src="${basePath }/js/content/zh.js" type="text/javascript"></script>

    <style type="text/css">
        html,body{
          height:100%;
        }
        .container{
            	position: relative;
        }
    	.tabulation{
    		margin-top: 10px;
    		text-align: center;
    	}
    	.tree{
    		text-align: center;
    		margin-left: 100px;
    	}
    	body{
    		background-color: #ECF5FF
    	}
    	.table{
    		background-color: #FFFFFF
    	}
    	.panel{
    		margin-top: 10px;
    	}
        .pagination{
        	margin-left: 300px;
        }
        .delete{
        	margin-right: -200px;
        }
        .modal-title{
        	text-indent: 270px;
        }
        .upload_pic{
        	margin-left: 130px;
        }
        .upload_video{
        	margin-left: 100px;
        }
        .title-pic{
           text-indent:250px;
        }
        .title-video{
           text-indent:250px;
        }
        .upload-btn{
           margin-top:10px;
          
        }
        .success-file-upload{
           margin-left: 513px;
        }
        img{
           height:"20%";
            width:"20%";
        }
        #page{
           font-size: 20px;
        }
        .popPic{
          text-align:center;
          margin:0 auto;
        }
        #picUrl{
          max-width: 100%;
          max-height: 100%;
        }
        

    </style>
    
    
</head>
<body>
  <!-- 埋一个basePath -->
  <input type="hidden" id="basePath" value="${basePath}">
  <!-- 埋一个文件夹id -->
  <input type="hidden" id="folderId" value="${folderId}">
  <!-- 埋一个kind种类 -->  
  <input type="hidden" id="kind" value="${CodeDto.kind}">
  <!-- 埋一个当前页数 -->  
  <input type="hidden" id="currentPage" value="${CodeDto.page.currentPage}">
  <!-- 埋一个是否是文件夹搜索还是全文搜索 -->  
  <input type="hidden" id="isAll" value="${CodeDto.isAll}">   
  <div class="row">
      <div class="col-md-4">
           <div class="tabulation">
          	  <button class="btn btn-primary pic-list" onclick="ImageList();" type="button">图片列表</button> 
          	  <button class="btn btn-primary dir-list" onclick="DirList();" id="DirList" type="button">目录列表</button> 
              <button class="btn btn-primary vid-list" onclick="VideoList();" id="Vidlist" type="button">视频列表</button> 
           </div>
          <div class="tree">
             <ul id="treeDemo" class="ztree"></ul>
          </div> 
      </div>
      <div class="col-md-7" >
         <div class="panel panel-default">
            <div class="panel-heading"><b>QianYue</b></div>
	        <div class="panel-body">
	          <form  id="listForm" action="${basePath}/picture">     
	            <table id="listTable"  style="table-layout:fixed" class="table table-striped table-bordered table-hover">
                   <thead>
                     <tr>
                       <th width="100">序号</th>
                       <th width="300">路径</th>
                       <th width="100">类别</th>
                       <th width="130">内容/标题</th>
                       <th width="200">创建时间</th>
                       <th width="150">操作</th>
                     </tr>
                   </thead>
                   <tbody>
                       <c:if test="${ CodeDto.kind == 'Image' }">
                       <c:forEach items="${CodeDto.list}" var="item" varStatus="s">
					<tr>
					    <td>${s.index+1}</td>
					    <td>${item.path}</td>
					    <td>图片</td>
					    <td>
					      <button class="btn btn-primary btn-xs" type="button" onclick="popPictureShow('${item.img}')">查看首图</button>
					    </td>
					    
					    <td>${item.time}</td>
					    <td>
					       <a href="${basePath}/picture/deleteOnePicture/${item.id}"  onclick="remove('${item.id}')">删除</a>
					    </td>
					</tr>
		               </c:forEach>
                      </c:if>
                      
                      <c:if test="${ CodeDto.kind == 'Video' }">
                       <c:forEach items="${list}" var="item" varStatus="s">
					<tr>
					    <td>${s.index+1}</td>
					    <td>${item.path}</td>
					    <td>视频</td>
					    <td>${item.title}</td>
					    <td>${item.time}</td>
					    <td>
					       <a href="${basePath}/video/deleteOneVideo/${item.id}" onclick="remove('${item.id}')">删除</a>
					    </td>
					</tr>
		               </c:forEach>
                      </c:if>
                      
                      <c:if test="${ CodeDto.kind == 'Directory' }">
                       <c:forEach items="${list}" var="item" varStatus="s">
					<tr>
					    <td>${s.index+1}</td>
					    <td>${item.path}</td>
					    
					    
					    <td>${item.headImageHaveOrnot}</td>
					    
					    <td>${item.folderName}</td>
					    <td>无</td>
					    
					    <c:if test="${ item.headImageHaveOrnot == '无首图' }">
					       <td>
					         
					         <button class="btn btn-danger btn-xs" type="button" onclick="addHeadImageForList('${item.id}')">添加首图</button>
					         
					       </td>
					       
					    </c:if>
					    <c:if test="${ item.headImageHaveOrnot == '有首图' }">
					       <td>
					         <button class="btn btn-primary btn-xs" type="button" onclick="popPictureShow('${item.headImageUrl}')">查看首图</button>
					         <button class="btn btn-warning btn-xs" type="button" onclick="addHeadImageForList('${item.id}')">更换首图</button>
					       </td>
					    </c:if>
					</tr>
		               </c:forEach>
                      </c:if>
                      
                   </tbody>
                </table>
              </form> 
	        </div>	        
         </div>
         <ul class="pager">
            <li  id="last" value="" ><a href="javascript:lastPage('${CodeDto.page.currentPage-1}');">&laquo;上一页</a></li>
            <span id="page">第${CodeDto.page.currentPage }页/共${CodeDto.page.totalPage }页</span>
            <li><a  id="next"  href="javascript:nextPage('${CodeDto.page.currentPage+1}');">下一页&raquo;</a></li>
         </ul> 
      </div>
      <div class="col-md-1">      	
      </div>
  </div>
  




  <div class="upload">
      


      <!-- 上传图片表单弹出框-->
      <div class="modal fade" id="mymodal-data-pic" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	       <div class="modal-dialog">
		    <div class="modal-content">
		    	<div class="modal-header">
		    		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span    ></button>
		    		<h4 class="modal-title title-pic">上传图片</h4>
		    	</div>
		    	<div class="modal-body">
		    		<input id="file-pic" multiple name="imgFile" type="file">
		    	</div>
		    	<div class="modal-footer">
		    		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		    		<button type="button" class="btn btn-primary">保存</button>
		    	</div>
		    </div>
	       </div>
      </div>
      <!-- 隐藏返回信息 -->
     <input type="hidden" value="${pageCode.msg}"  id="messageUploadVid" class="form-control" > 

      <!-- 上传视频表单弹出框-->
      <div class="modal fade" id="mymodal-data-video" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	       <div class="modal-dialog">
		    <div class="modal-content">
		    	<div class="modal-header">
		    		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span    ></button>
		    		<h4 class="modal-title title-video">上传视频</h4>
		    		
		    	</div>
		    	<div class="modal-body">
		    	 <form id="mainForm" method="post" action="${basePath}/video/addVideo" enctype="multipart/form-data">
                     <input type="hidden" id="message-folder-id-vid" name="folderId" class="form-control" placeholder="隐藏内容">    
		    		 <input type="text"  name="title" id="videoTitle" class="form-control" placeholder="请输入标题"> 
		    		 <input type="file" name="videoFile" id="videoFile" class="upload-btn" id=video />
		    		 <button class="btn btn-success success-file-upload" type="button">提交</button> 
                 </form>
		    	</div>
		    	<div class="modal-footer">
		    		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		    		
		    	</div>
		    </div>
	       </div>
      </div>
      
      <!-- 上传首图表单弹出框-->
      <div class="modal fade" id="mymodal-data-headImage" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	       <div class="modal-dialog">
		    <div class="modal-content">
		    	<div class="modal-header">
		    		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span    ></button>
		    		<h4 class="modal-title title-video">上传首图</h4>
		    		
		    	</div>
		    	<div class="modal-body">
		    	 <form id="mainFormForHeadImage" method="post" action="${basePath}/directory/HeadImage" enctype="multipart/form-data">
                     <input type="hidden" id="message-folder-id-headImage" name="id" class="form-control" placeholder="隐藏内容">
		    		 <input type="file" name="headImage" id="headImage" class="upload-btn" id=video />
		    		 <button class="btn btn-success success-file-upload" type="button">提交</button> 
                 </form>
		    	</div>
		    	<div class="modal-footer">
		    		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		    	</div>
		    </div>
	       </div>
      </div>
      
      <!-- 查看图片弹出框 -->
      <div class="modal fade" id="popPictureShow" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">图片</h4>
			</div>
			<div class="modal-body popPic">
				<img  id="picUrl" alt="图片" src="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				
			</div>
		</div>
	</div>
</div>
      
      
      <!-- 文件夹右键菜单 -->
        <div id="rMenu" class="rMenu" onmouseout="divOut();" onmouseover="divOver()">
            <ul class="rMenuUi">
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="addImage();">添加图片</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="showImage();">查看图片</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="addVideo();">添加视频</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="showVideo();">查看视频</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="addHeadImage();">设置首图</li>
            </ul>
        </div>
  </div>	
  </body>
</html>