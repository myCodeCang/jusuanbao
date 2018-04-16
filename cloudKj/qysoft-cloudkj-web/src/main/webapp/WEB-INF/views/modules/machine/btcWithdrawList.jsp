<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>BTC提现列表</title>
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
		<li class="active"><a href="${ctx}/machine/btcWithdraw/">BTC提现列表</a></li>
		<%--<shiro:hasPermission name="user:btcWithdraw:edit"><li><a href="${ctx}/machine/btcWithdraw/form">btc提现添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="btcWithdraw" action="${ctx}/machine/btcWithdraw/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%--<li><label>账户：</label>--%>
				<%--<form:input path="account" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
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
					
				<th>BTC提现账户</th>
					
				<th>BTC提现金额</th>

				<th>实际到账</th>
					
				<th>状态</th>
					
					
				<th>创建时间</th>
					
				<shiro:hasPermission name="user:btcWithdraw:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="btcWithdraw">
			<tr>

				<td><a href="${ctx}/machine/btcWithdraw/form?id=${btcWithdraw.id}">
					${btcWithdraw.id}
				</a></td>
				<td>
					${btcWithdraw.userName}
				</td>
				<td>
					${btcWithdraw.account}
				</td>
				<td>
					${btcWithdraw.money}
				</td>
				<th>
					${btcWithdraw.money - fns:getOption('system_help','change_one')}
				</th>
				<td>
						${fns:getDictLabel(btcWithdraw.status, 'usercenter_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${btcWithdraw.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:if test="${btcWithdraw.status==0}">
					<a class="layui-btn layui-btn-mini layui-btn-normal" href="${ctx}/machine/btcWithdraw/check?id=${btcWithdraw.id}"
					   onclick="layui.qyframe.openPromptMsg('提现审核','是否同意','${btcWithdraw.userName}',[{'label':'是','value':'1'},{'label':'否','value':'0'}],'1',this.href);return  false;">处理</a>
				</c:if>
				<c:if test="${btcWithdraw.status==1}">
					已同意
				</c:if>
				<c:if test="${btcWithdraw.status==2}">
					已驳回
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">

        layui.use('qyframe', function() {
            var qyframe = layui.qyframe;
        });

	</script>
</body>
</html>