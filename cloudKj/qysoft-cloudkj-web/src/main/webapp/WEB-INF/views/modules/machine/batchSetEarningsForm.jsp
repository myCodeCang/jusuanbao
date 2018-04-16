<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>矿机管理</title>
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
		<li><a href="${ctx}/machine/miningMachine/">矿机列表</a></li>
		<li ><a href="${ctx}/machine/miningMachine/form?id=${miningMachine.id}">矿机<shiro:hasPermission name="user:miningMachine:edit">${not empty miningMachine.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:miningMachine:edit">查看</shiro:lacksPermission></a></li>
		<li class="active"><a href="${ctx}/machine/miningMachine/batchSetEarnings">批量设置收益</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="miningMachine" action="${ctx}/machine/miningMachine/batchSetEarnings" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">设置收益：</label>
			<div class="controls">
				<input name="earnings" type="number" min="0.0000000001" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="user:miningMachine:edit"><input id="btnSubmit"   class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>