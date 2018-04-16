<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>btc提现管理</title>
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
		<li><a href="${ctx}/machine/btcAccountchange/">btc提现列表</a></li>
		<li class="active"><a href="${ctx}/machine/btcAccountchange/form?id=${btcAccountchange.id}">btc提现<shiro:hasPermission name="machine:btcAccountchange:edit">${not empty btcAccountchange.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="machine:btcAccountchange:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="btcAccountchange" action="${ctx}/machine/btcAccountchange/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户帐号：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变化金额：</label>
			<div class="controls">
				<form:input path="changeMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">之前金额：</label>
			<div class="controls">
				<form:input path="beforeMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">之后金额：</label>
			<div class="controls">
				<form:input path="afterMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="commt" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否统计：</label>
			<div class="controls">
				<form:input path="ischeck" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">0收入 1支出：</label>
			<div class="controls">
				<form:input path="changeType" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">帐变类型：</label>
			<div class="controls">
				<form:input path="moneyType" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="machine:btcAccountchange:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>