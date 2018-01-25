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
<link href="${basePath}/kindeditor/themes/default/default.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.validate.css"/>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://unpkg.com/wangeditor/release/wangEditor.min.js"></script>

<script src="${basePath }/js/wangEditor/wangEditor.min.js" type="text/javascript"></script>
<script src="${basePath }/js/content/informationAdd.js" type="text/javascript"></script>
<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
<script type="text/javascript" src="${basePath}/js/common/validation/jquery.validate.js"></script>
<script type="text/javascript" src="${basePath}/js/common/validation/messages_zh.js"></script>
  <style type="text/css">
    body{
       font-family:"微软雅黑";
       text-align:center;
    }
    .title{
       width:950px;
       margin-left: 50px;
    }
    .title-word{       
       font-size: 30px;
        text-indent: -50px;
    }
    .content{
       margin-left: 150px;
    }
    .content-word{
       text-indent: -50px;
       font-size: 25px;
    }
    .handin{
       margin-left: -150px;
    }
    .btn-primary{
       margin-left: -50px;
    }
  
    #imageFile{
    margin-left: 50px;
    }
    #editor{
     margin-left:-130px;
    }
    a{
     float:left;
      margin-left: 50px;
    }
    #pic{
      margin-top: 10px;
    }
    h4{
    text-indent: 30px;
    }
    /* table 样式 */
table {
  border-top: 1px solid #ccc;
  border-left: 1px solid #ccc;
}
table td,
table th {
  border-bottom: 1px solid #ccc;
  border-right: 1px solid #ccc;
  padding: 3px 5px;
}
table th {
  border-bottom: 2px solid #ccc;
  text-align: center;
}

/* blockquote 样式 */
blockquote {
  display: block;
  border-left: 8px solid #d0e5f2;
  padding: 5px 10px;
  margin: 10px 0;
  line-height: 1.4;
  font-size: 100%;
  background-color: #f1f1f1;
}

/* code 样式 */
code {
  display: inline-block;
  *display: inline;
  *zoom: 1;
  background-color: #f1f1f1;
  border-radius: 3px;
  padding: 3px 5px;
  margin: 0 3px;
}
pre code {
  display: block;
}

/* ul ol 样式 */
ul, ol {
  margin: 10px 0 10px 20px;
}
  </style>
      

<body data-genuitec-lp-enabled="false" data-genuitec-file-id="wc2-2" data-genuitec-path="/qianyue/src/main/webapp/WEB-INF/jsp/content/informationAdd.jsp">
 <div class="container" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc2-2" data-genuitec-path="/qianyue/src/main/webapp/WEB-INF/jsp/content/informationAdd.jsp">
 <!-- 埋一个 -->
 <input type="hidden"  id="id" name="id" value="${informationDto.id}"/>  
<input type="hidden" id="basePath" value="${basePath}" />
<input type="hidden" id="modify" value="${modify}" />
<input id="message" type="hidden" value="${pageCode.msg}"/>


 <div class="row">
  <form id="informationForm" method="post"  action="${basePath}/information/addInformation" enctype="multipart/form-data">
  <div class="col-sm-11">
  
   <div class="row">
       <p class="title-word">标题<p> <input type="text" id="titleId" name="title" value="${informationDto.title}" class="form-control input-lg title" placeholder="text">
   
   </div>
   <div id="pic">
    
    <p class="title-word">图片<p>
    <input type="file" name="imageFile" id="imageFile" onclick="selectIma();" class="upload-btn" id=image />
    <c:if test="${informationDto.image!=null}">
    <img src="${informationDto.image}" height="200px" width="200px">
    </c:if>
    
  </div>
  <%--  <img alt="已选图片" src="${informationDto.image}" height="100"> --%>
   
   <div class="row">
   
     <p class="content-word">文章内容<p>
     <div class="content">
       
       
       <div id="editor">
        ${informationDto.content}
       </div>
        <textarea id="text1" name="content"  style="width:100%; height:200px;display:none">
        
        </textarea>
     </div>
   </div>
   <div class="row">
     <br>
     <button class="btn btn-primary" onclick="submitInf();" type="button">提交</button>
   </div>
   
   </div>
  
    </form>
   </div>
   </div>

</body>
</html>