<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/machine/renzhengAudit/">审核列表</a></li>
		<li class="active"><a href="${ctx}/machine/renzhengAudit/form?id=${renzhengAudit.id}">审核<shiro:hasPermission name="user:renzhengAudit:edit">${not empty renzhengAudit.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:renzhengAudit:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="renzhengAudit" action="${ctx}/machine/renzhengAudit/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="trueName" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="idCard" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<c:if test="${renzhengAudit.type==1}">
		<div class="control-group">
			<label class="control-label">学校名：</label>
			<div class="controls">
				<form:input path="schoolName" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业名称：</label>
			<div class="controls">
				<form:input path="subjectName" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入学年份：</label>
			<div class="controls">
				<form:input path="enrollmentYear" htmlEscape="false" maxlength="4" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学年制：</label>
			<div class="controls">
				<input type="text" value="${fns:getDictLabel(renzhengAudit.schoolYear, 'hc_xuenian', '')}" name="type" htmlEscape="false" class="input-xlarge " readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历：</label>
			<div class="controls">
				<input type="text" value="${fns:getDictLabel(renzhengAudit.education, 'hc_xueli', '')}" name="type" htmlEscape="false" class="input-xlarge " readonly="readonly"/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">学生证照片：</label>
				<div class="controls">
						<%--<form:input path="studentImg" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
					<img src="${renzhengAudit.studentImg}" alt="" title="学生证照片" style="width: 350px;height: 230px;">
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">身份证正面照：</label>
			<div class="controls">
				<%--<form:input path="frontCardImg" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
				<img src="${renzhengAudit.frontCardImg}" alt="" title="身份证正面照"  style="width: 350px;height: 230px;">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证反面照：</label>
			<div class="controls">
				<%--<form:input path="versoCardImg" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
				<img src="${renzhengAudit.versoCardImg}" alt="" title="身份证反面照" style="width: 350px;height: 230px;">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证状态：</label>
			<div class="controls">
				<input type="text" value="${fns:getDictLabel(renzhengAudit.status, 'usercenter_type', '')}" name="type" htmlEscape="false" class="input-xlarge " readonly="readonly"/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">认证类型：</label>--%>
			<%--<div class="controls">--%>
				<%--<input type="text" value="${fns:getDictLabel(renzhengAudit.type, 'hc_renzheng', '')}" name="type" htmlEscape="false" class="input-xlarge " readonly="readonly"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">备注信息：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="form-actions">--%>
			<%--<shiro:hasPermission name="user:renzhengAudit:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
			<%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
		<%--</div>--%>
	</form:form>
</body>
</html>