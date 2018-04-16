<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核管理</title>
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
		<li class="active"><a href="${ctx}/machine/renzhengAudit/">审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="renzhengAudit" action="${ctx}/machine/renzhengAudit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="13" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="trueName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%--<li><label>认证类型:</label>--%>
				<%--<form:select path="type" class="input-medium">--%>
					<%--<form:option value="" label="全部"/>--%>
					<%--<form:options items="${fns:getDictList('hc_renzheng')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<li><label>审核状态:</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('usercenter_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
					
				<th>用户名</th>
					
				<th>真实姓名</th>
					
				<th>身份证号</th>

				<%--<th>学校名</th>--%>
					<%----%>
				<%--<th>专业名称</th>--%>
					<%----%>
				<%--<th>入学年份</th>--%>
					<%----%>
				<%--<th>学年制</th>--%>
					<%----%>
				<%--<th>学历</th>--%>

				<th>认证状态</th>
					
				<%--<th>认证类型</th>--%>

				<th>创建时间</th>
					
					
				<th>更新时间</th>
					
					
				<shiro:hasPermission name="user:renzhengAudit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="renzhengAudit">
			<tr>

				<td><a href="${ctx}/machine/renzhengAudit/form?id=${renzhengAudit.id}">
					${renzhengAudit.id}
				</a></td>
				<td>
					${renzhengAudit.userName}
				</td>
				<td>
					${renzhengAudit.trueName}
				</td>
				<td>
					${renzhengAudit.idCard}
				</td>
				<%--<td>--%>
					<%--${renzhengAudit.schoolName}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${renzhengAudit.subjectName}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${renzhengAudit.enrollmentYear}--%>
				<%--</td>--%>
				<%--<td>--%>
						<%--${fns:getDictLabel(renzhengAudit.schoolYear, 'hc_xuenian', '')}--%>
				<%--</td>--%>
				<%--<td>--%>
						<%--${fns:getDictLabel(renzhengAudit.education, 'hc_xueli', '')}--%>
				<%--</td>--%>
				<td>
						${fns:getDictLabel(renzhengAudit.status, 'usercenter_type', '')}
				</td>
				<%--<td>--%>
						<%--${fns:getDictLabel(renzhengAudit.type, 'hc_renzheng', '')}--%>
				<%--</td>--%>
				<td>
					<fmt:formatDate value="${renzhengAudit.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${renzhengAudit.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="user:renzhengAudit:edit"><td>
					<c:if test="${renzhengAudit.status==0}">
    				<a href="${ctx}/machine/renzhengAudit/check?id=${renzhengAudit.id}&type=${renzhengAudit.type}" class="layui-btn layui-btn-mini layui-btn-normal" onclick="layui.qyframe.openPromptMsg('认证审核','是否同意','${renzhengAudit.userName}',[{'label':'是','value':'1'},{'label':'否','value':'0'}],'1',this.href);return  false;">审核</a>
					</c:if>
					<c:if test="${renzhengAudit.status==1}">
						已处理
					</c:if>
					<c:if test="${renzhengAudit.status==2}">
						已处理
					</c:if>
					<a href="${ctx}/machine/renzhengAudit/form?id=${renzhengAudit.id}" class="layui-btn layui-btn-mini layui-btn-normal">查看</a>
				</td></shiro:hasPermission>
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