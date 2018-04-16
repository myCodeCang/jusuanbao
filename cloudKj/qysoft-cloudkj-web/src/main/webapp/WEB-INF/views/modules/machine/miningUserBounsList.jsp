<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>奖励统计表管理</title>
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
		<li class="active"><a href="${ctx}/machine/miningUserBouns/">奖励统计表列表</a></li>
		<shiro:hasPermission name="machine:miningUserBouns:edit"><li><a href="${ctx}/machine/miningUserBouns/form">奖励统计表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="miningUserBouns" action="${ctx}/machine/miningUserBouns/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
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

					
				<th>id</th>
					
				<th>用户名</th>
					
				<th>矿机编号</th>
					
				<th>算力数量</th>
					
					
				<th>创建时间</th>
					
					
				<th>更新时间</th>
					
				<th>备注信息</th>
					
				<shiro:hasPermission name="machine:miningUserBouns:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="miningUserBouns">
			<tr>

				<td><a href="${ctx}/machine/miningUserBouns/form?id=${miningUserBouns.id}">
					${miningUserBouns.id}
				</a></td>
				<td>
					${miningUserBouns.userName}
				</td>
				<td>
					${miningUserBouns.machineId}
				</td>
				<td>
					${miningUserBouns.hashrate}
				</td>
				<td>
					<fmt:formatDate value="${miningUserBouns.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${miningUserBouns.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${miningUserBouns.remarks}
				</td>
				<shiro:hasPermission name="machine:miningUserBouns:edit"><td>
    				<a href="${ctx}/machine/miningUserBouns/form?id=${miningUserBouns.id}">修改</a>
					<a href="${ctx}/machine/miningUserBouns/delete?id=${miningUserBouns.id}" onclick="return confirmx('确认要删除该奖励统计表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>