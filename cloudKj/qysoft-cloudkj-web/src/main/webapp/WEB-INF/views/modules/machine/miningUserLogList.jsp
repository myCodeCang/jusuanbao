<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户算力记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/machine/miningUserLog/">用户算力记录列表</a></li>
		<shiro:hasPermission name="machine:miningUserLog:edit"><li><a href="${ctx}/machine/miningUserLog/form">用户算力记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="miningUserLog" action="${ctx}/machine/miningUserLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>矿机编号：</label>
				<form:input path="machineId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>算力数量：</label>
				<form:input path="hashrate" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

					
				<th>编号</th>
					
				<th>用户名</th>
					
				<th>矿机编号</th>
					
				<th>算力数量</th>
					
					
					
					
				<th>更新时间</th>
					
				<th>备注信息</th>
					
				<shiro:hasPermission name="machine:miningUserLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="miningUserLog">
			<tr>

				<td><a href="${ctx}/machine/miningUserLog/form?id=${miningUserLog.id}">
					${miningUserLog.id}
				</a></td>
				<td>
					${miningUserLog.userName}
				</td>
				<td>
					${miningUserLog.machineId}
				</td>
				<td>
					${miningUserLog.hashrate}
				</td>
				<td>
					<fmt:formatDate value="${miningUserLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${miningUserLog.remarks}
				</td>
				<shiro:hasPermission name="machine:miningUserLog:edit"><td>
    				<a href="${ctx}/machine/miningUserLog/form?id=${miningUserLog.id}">修改</a>
					<a href="${ctx}/machine/miningUserLog/delete?id=${miningUserLog.id}" onclick="return confirmx('确认要删除该用户算力记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>