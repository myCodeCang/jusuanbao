<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户矿机管理</title>
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
		<li class="active"><a href="${ctx}/machine/miningUser/">用户矿机列表</a></li>
		<%--<shiro:hasPermission name="user:miningUser:edit"><li><a href="${ctx}/machine/miningUser/form">用户矿机添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="miningUser" action="${ctx}/machine/miningUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>

			<li><label>矿机编号：</label>
				<form:input path="machineId" htmlEscape="false" maxlength="50" class="input-medium"/>
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

				<th>矿机名称</th>
					
				<th>算力数量</th>
					
					
				<th>创建时间</th>
					
					
				<%--<th>更新时间</th>--%>
					
					
				<%--<shiro:hasPermission name="user:miningUser:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="miningUser">
			<tr>

				<td>
					${miningUser.id}
				</td>
				<td>
					${miningUser.userName}
				</td>
				<td>
					${miningUser.machineId}
				</td>
				<td>
					${miningUser.miningMachine.name}
				</td>
				<td>
					${miningUser.hashrate}
				</td>
				<td>
					<fmt:formatDate value="${miningUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${miningUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<%--<shiro:hasPermission name="user:miningUser:edit"><td>--%>
    				<%--<a href="${ctx}/machine/miningUser/form?id=${miningUser.id}">修改</a>--%>
					<%--&lt;%&ndash;<a href="${ctx}/machine/miningUser/delete?id=${miningUser.id}" onclick="return confirmx('确认要删除该用户矿机吗？', this.href)">删除</a>&ndash;%&gt;--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>