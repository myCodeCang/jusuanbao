<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>比特币提现管理</title>
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
		<li class="active"><a href="${ctx}/machine/btcAccountchange/">比特币提现列表</a></li>
		<%--<shiro:hasPermission name="user:btcAccountchange:edit"><li><a href="${ctx}/machine/btcAccountchange/form">btc提现添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="btcAccountchange" action="${ctx}/machine/btcAccountchange/findBtcEarn" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%--<li><label>账变类型</label>--%>
				<%--<form:select path="changeType" class="input-medium">--%>
					<%--<form:option value="" label="全部"/>--%>
					<%--<form:options items="${fns:getDictList('change_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<li><label>账变时间：</label>
				<input name="createDateBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${btcAccountchange.createDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="createDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${btcAccountchange.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
					
				<th>用户帐号</th>

				<th>真实姓名</th>
					
				<th>变化金额</th>


				<th>备注</th>
					
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="btcAccountchange">
			<tr>

				<td><a href="${ctx}/machine/btcAccountchange/form?id=${btcAccountchange.id}">
					${btcAccountchange.id}
				</a></td>
				<td>
					${btcAccountchange.userName}
				</td>
				<td>
						${btcAccountchange.trueName}
				</td>
				<td>
					${btcAccountchange.changeMoney}
				</td>
				<td>

					<c:choose>
						<c:when test="${btcAccountchange.changeType == 1}">
							矿机产出收益
						</c:when>
						<c:when test="${btcAccountchange.changeType == 3}">
							扣除矿机管理费
						</c:when>
						<c:otherwise>
							${btcAccountchange.commt}
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${btcAccountchange.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>