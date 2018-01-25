<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<html xmlns="http://www.w3.org/1999/xhtml"><head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
	    <title>骞越后台管理</title>
        <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
        
	    <link href="${basePath}/css/all.css" rel="stylesheet" type="text/css"/>
	    <link href="${basePath}/css/pop.css" rel="stylesheet" type="text/css"/>
	    <link href="${basePath}/css/index.css" rel="stylesheet" type="text/css"/>
	    <link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.validate.css"/>  
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="${basePath}/js/common/jQuery.md5.js" type="text/javascript"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/validation/jquery.validate.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/validation/messages_zh.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
		<script src="${basePath}/js/index.js" type="text/javascript"></script>
		<style type="text/css">
		 .wishlistBox{
		   margin-left:150px;
		 }
		 #footer{
		   height:10px;
		 }
		</style>
	</head>
	<body>
		<!-- 蒙版DIV -->
		<div id="mengban" style="display:none"></div>
		
		<div class="wishlistBox" style="display: none;left:550px;top:200px;">
		    <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
		        <div class="persongRightTit" style="width:480px;">&nbsp;&nbsp;修改密码</div>
		        <div class="persongRigCon">
		            <form name="redisAddOrEditForm" id="modifyWindow" action="${basePath}/login/modifyPw" method="post">
		            <input id="basePath" type="hidden" value="${basePath}/login/modifyPw"/>
		            <input id="message" type="hidden" value="${pageCode.msg}"/>
		                <table class="x-form-table">
		                    <tbody>
		                    <tr class="line">
		                        <td class="left" width="10%"><em class="required">*</em><label>原始密码:</label></td>
		                        <td width="90%">
		                            <input class="normal-input" name="oldPassword" readonly="readonly" id="oldPassword" value="${userDto.password }" style="width: 240px;" type="password"/>
                                    
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><label><em class="required">*</em>新密码：</label></td>
		                        <td>
		                            <input class="normal-input" name="newPassword" id="newPassword" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><em class="required">*</em><label>确认新密码：</label></td>
		                        <td>
		                            
		                            <input class="normal-input" name="newPasswordAgain" id="newPasswordAgain" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="left"></td>
		                        <td class="submit">
		                            <input id="submitVal" class="tabSub" value="提交" onclick="submitPw();" type="button"/>
		                            <input class="tabSub" value="关闭" onclick="closeDiv();" type="reset"/>
		                        </td>
		                    </tr>
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
		</div>
		
		<form method="post">
		    <div id="header">
		        <div class="iheader">
		            <div class="logo"><a href="#"><img src="" alt="" height="88px" width="99px"/></a> </div>
		            <div style="height: 44px;">
		                <div class="wuxianlogo"><img src="" alt="" height="28px" width="275px"/></div>
		                <div class="h_info">
		                    <span class="line"></span>
							欢迎您！姓名[${userDto.name}]&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		                    <a href="javascript:void(0);" onclick="openAddDiv();">[修改密码]</a>
		                    &nbsp;
		                    <a href="javascript:void(0);" onclick="logoutSystem();">[退出系统]</a>
		                </div>
		            </div>
		            <ul class="nav" id="menuDiv">
		            	<li onclick="" class="on"><a><span>内容管理</span></a></li>
		            </ul>
		        </div>
		    </div>
		    <div id="container">
		        <table style="vertical-align:top" cellspacing="0" cellpadding="0" bgcolor="#e1e9eb" border="0">
		            <tbody>
		            <tr>
		                <td class="leftTd" style="vertical-align:top" width="150">
		                    <div class="left">
		                        <div class="ileft" id="subMenuDiv">
		                        	<h3 onclick="clickMenu(this,'${basePath}/directory')"><a>目录管理</a></h3>	
		                        	<h3 onclick="clickMenu(this,'${basePath}/information')"><a>动态资讯管理</a></h3>
		                        </div>
		                    </div>
		                </td>
		                <td width="7">
		                    <div class="pointer"></div>
		                </td>
		                <td style="vertical-align:top" height="600px" width="100%">
		                	<br/><iframe id="mainPage" src="" frameborder="0" height="580px" width="100%"></iframe><br/>
		                 </td>
		            </tr>
		            </tbody>
		        </table>
		    </div>
		    <div id="footer">
		        <div class="copyright">骞越</div>
		        <div class="flr">copyright &copy;</div>
		    </div>
		</form>
	</body>
</html>