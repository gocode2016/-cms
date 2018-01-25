<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%> 
<html xmlns="http://www.w3.org/1999/xhtml"><head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
		<title></title>
		<%@include file="head.jsp"%>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
		<script type="text/javascript" src="${basePath}/js/content/informationList.js"></script>
		<style type="text/css">
		 .wirte-article{
		    margin-left: 1400px;
		 }
		 .modal-title{
		    text-indent: 250px;
		 }
		 span{
		   font-size: 20px;
		 }
		 .title{
		    text-indent: 190px;
		 }
		 
		</style>
	</head>
	<body style="background: #e1e9eb;">
		<form action="${basePath}/information/search" id="mainForm" method="post" >
		  <input type="hidden" name="page.currentPage" id="currentPage" />
		  <input type="hidden"  id="id" name="id"/>  
		  <input type="hidden" id="basePath" value="${basePath}"/>
		  
			<div class="right">
				<div class="current">当前位置：<a href="#">内容管理</a> &gt; 动态资讯管理
				
				</div>
				
				<div class="rightCont">
					<p class="g_title fix">动态资讯列表</p>
					<table class="tab1">
						<tbody>
							<tr>
								<td align="right" width="80">标题：</td>
								<td>
									<input id="title" name="title"  class="allInput" type="text"/>
								</td>
	                            <td style="text-align: left;" width="150">
	                            	&nbsp;&nbsp;&nbsp;&nbsp;<input class="tabSub" value="查询" onclick="search('1')" type="button"/>&nbsp;  
	                            	<input class="tabSub" id="writeInf" onclick="location.href='${basePath}/information/addInformationPage'"  value="添加"   type="button"/>              	
	                            </td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th class="title">序号</th>
								    <th class="title">标题</th>
								    <th class="title">创建时间</th>
								    <th class="title">操作</th>
								</tr>
								
								 <c:forEach items="${list}" var="item" varStatus="s">
								    <tr>
								        <td>${s.index+1}</td>
								        <td>${item.title}</td>
								        <td>${item.time}</td>
								        <td>
								           <a href="javascript:void(0);" onclick="modifyInit('${item.id}')">修改</a>
								           <a href="javascript:void(0);" onclick="remove('${item.id}')">删除</a>
								        </td>
								    </tr>
								</c:forEach> 
									
								
							</tbody>
						</table>
						
						<!-- 分页 -->
						<t:page jsMethodName="search" page="${informationDto.page}"></t:page>
					</div>
					
				</div>
			</div>
		</form>
		<div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h2 class="modal-title"><span>资讯标题</span></h2>
			</div>
			<div class="modal-body">
			   
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default intoInformation" data-dismiss="modal">进入</button>				
			</div>
		</div>
	</div>
</div>
	</body>
</html>