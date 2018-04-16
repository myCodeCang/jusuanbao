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
		<li class="active"><a href="${ctx}/machine/miningMachine/form?id=${miningMachine.id}">矿机<shiro:hasPermission name="user:miningMachine:edit">${not empty miningMachine.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:miningMachine:edit">查看</shiro:lacksPermission></a></li>
		<li ><a href="${ctx}/machine/miningMachine/batchSetEarnings">批量设置收益</a></li>

	</ul><br/>
	<form:form id="inputForm" modelAttribute="miningMachine" action="${ctx}/machine/miningMachine/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">矿机名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合约单位：</label>
			<div class="controls">
				<form:input path="message" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总算力：</label>
			<div class="controls">
				<form:input path="allHashrate" htmlEscape="false" maxlength="11"  class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">剩余算力：</label>
			<div class="controls">
				<form:input path="nowHashrate" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单价：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" maxlength="11" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收益：</label>
			<div class="controls">
				<form:input path="earnings" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电量消耗：</label>
			<div class="controls">
				<form:input path="powerExpend" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<sys:ckfinder input="image" type="images" uploadPath="/machine/miningMachine" selectMultiple="false"/>
				<span class="help-inline"><font color="red">(建议图片大小：256*164)</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">矿机协议:</label>
			<div class="controls">
				<form:textarea id="remarks" htmlEscape="false" path="remarks" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="remarks" uploadPath="remarks" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算收益时间：</label>
			<div class="controls">
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${miningMachine.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status">
					<form:option value="1" label="上架"/>
					<form:option value="0" label="下架"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>

		<%--<div class="control-group">--%>
			<%--<label class="control-label">风险提示：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="risk" htmlEscape="false" rows="4" class="input-xxlarge "/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">备注信息：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="user:miningMachine:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>