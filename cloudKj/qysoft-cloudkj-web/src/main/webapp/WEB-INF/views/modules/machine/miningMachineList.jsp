<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>矿机管理</title>
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
		<li class="active"><a href="${ctx}/machine/miningMachine/">矿机列表</a></li>
		<shiro:hasPermission name="user:miningMachine:edit"><li><a href="${ctx}/machine/miningMachine/form">矿机添加</a></li></shiro:hasPermission>
		<li ><a href="${ctx}/machine/miningMachine/batchSetEarnings">批量设置收益</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="miningMachine" action="${ctx}/machine/miningMachine/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>矿机名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:option value="1" label="上架"/>
					<form:option value="0" label="下架"/>
				</form:select>
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
					
				<th>矿机名称</th>

				<th>单价(单位:T)</th>
					
				<th>总算力</th>
					
				<th>剩余算力</th>
					
				<th>收益</th>
					
				<th>电量消耗</th>
					
				<th>图片</th>
					
				<th>算力开始时间</th>
					
				<th>状态</th>
					<%----%>
				<%--<th>介绍</th>--%>
					<%----%>
				<%--<th>风险提示</th>--%>
					
					
				<th>创建时间</th>
					
					
				<%--<th>更新时间</th>--%>
					
					
				<shiro:hasPermission name="user:miningMachine:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="miningMachine">
			<tr>

				<td><a href="${ctx}/machine/miningMachine/form?id=${miningMachine.id}">
					${miningMachine.id}
				</a></td>
				<td>
					${miningMachine.name}
				</td>
				<td>
					${miningMachine.money}
				</td>
				<td>
					${miningMachine.allHashrate}
				</td>
				<td>
					${miningMachine.nowHashrate}
				</td>
				<td>
					${miningMachine.earnings}
				</td>
				<td>
					${miningMachine.powerExpend}
				</td>
				<td>
					<img src="${miningMachine.image}" width="100px;" height="100px"/>
				</td>
				<td>
					<fmt:formatDate value="${miningMachine.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${miningMachine.status==1?'上架':'下架'}
				</td>
				<%--<td>--%>
					<%--${miningMachine.message}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${miningMachine.risk}--%>
				<%--</td>--%>
				<td>
					<fmt:formatDate value="${miningMachine.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${miningMachine.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<shiro:hasPermission name="user:miningMachine:edit"><td>
    				<a class="layui-btn layui-btn-mini layui-btn-normal" href="${ctx}/machine/miningMachine/form?id=${miningMachine.id}">修改</a>
					<%--<a class="layui-btn layui-btn-mini layui-btn-normal" href="${ctx}/machine/miningMachine/delete?id=${miningMachine.id}" onclick="return confirmx('确认要删除该矿机吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>