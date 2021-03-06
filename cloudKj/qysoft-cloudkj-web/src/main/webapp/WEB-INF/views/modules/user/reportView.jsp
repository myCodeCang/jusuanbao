<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报表统计</title>
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
	<li class="active"><a href="##">报表统计</a></li>
</ul>
<form:form id="searchForm" modelAttribute="userReport"
		   action="${ctx}/user/ykjReport/ykjReportView/" method="post"
		   class="breadcrumb form-search">
	<ul class="ul-form">
		<li><label>时间：</label>
			<input name="createDateBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${createDateBegin}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
			<input name="createDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${createDateEnd}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary"
								type="submit" value="查询" /></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<style>
	.bochu-one{margin:20px 0;width:100%;}
	.bochu-one>div{padding:0 0.75rem;width:50%;box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;-o-box-sizing: border-box;}
	.bochu-one>div>div{background-color:#0e90d2;border-radius:8px;}
	.bochu-one>div>div>p{margin: 0;padding:0 30px;color:#fff;padding-bottom:15px;}
	.bochu-one>div>div>div{padding: 15px 30px;padding-top:20px;}
	.bochu-one>div>div>div>span{font-size:24px;color:#fff;}

	.bochu{margin-top:20px;width:100%;margin-bottom:20px;}
	.bochu ul{width:100%;}
	.bochu li{margin-bottom:20px;padding:0 0.75rem;padding-right:0.75rem;width:50%;float: left;box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;-o-box-sizing: border-box;}
	.bochu li>div{background-color:#0e90d2;border-radius:8px;}
	.bochu li>div>div{padding: 15px 30px;padding-top:20px;}
	.bochu li>div>p{margin: 0;padding:0 30px;color:#fff;padding-bottom:15px;}
	.bochu li>div>div>span{font-size:24px;color:#fff;}
</style>
<blockquote class="layui-elem-quote" style="height: 20px;">拨出统计</blockquote>
<div class="bochu">
	<ul>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>平台拨出率:</span>&nbsp;<span>${ykjReport.dividendEight==null ? '--' : ykjReport.dividendEight}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的平台拨出率 [总支出 / 总收入]</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>总收入:</span>&nbsp;<span>${ykjReport.dividendSeven==null ? '--' : ykjReport.dividendSeven}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总收入</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>总支出:</span>&nbsp;<span>${ykjReport.dividendSix==null ? '--' : ykjReport.dividendSix}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总支出</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>钱包余额:</span>&nbsp;<span>${ykjReport.dividendThree==null ? '--' : ykjReport.dividendThree}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总首奖</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<li>
			<div>
				<div><span>充值:</span>&nbsp;<span>${ykjReport.dividendFour==null ? '--' : ykjReport.dividendFour}</span></div>
				<p>根据所选择的时间范围计算出的充值总额</p>
			</div>
		</li>
		<li>
			<div>
				<div><span>提现:</span>&nbsp;<span>${ykjReport.dividendFive==null ? '--' : ykjReport.dividendFive}</span></div>
				<p>根据所选择的时间范围计算出的提现总额</p>
			</div>
		</li>
		<li>
			<div>
				<div><span>购买矿机:</span>&nbsp;<span>${ykjReport.dividendOne==null ? '--' : ykjReport.dividendOne}</span></div>
				<p>根据所选择的时间范围计算出购买的矿机产品</p>
			</div>
		</li>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>转账:</span>&nbsp;<span>${ykjReport.dividendSeven==null ? '--' : ykjReport.dividendSeven}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总转账</p>--%>
			<%--</div>--%>
		<%--</li>--%>
			<%--<li>--%>
				<%--<div>--%>
					<%--<div><span>直推收益:</span>&nbsp;<span>${ykjReport.dividendTwo==null ? '--' : ykjReport.dividendTwo}</span></div>--%>
					<%--<p>根据所选择的时间范围计算出的直属推广收益</p>--%>
				<%--</div>--%>
			<%--</li>--%>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>间推收益:</span>&nbsp;<span>${ykjReport.dividendThree==null ? '--' : ykjReport.dividendThree}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总消费</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<li>
			<div>
			<div><span>电量消耗:</span>&nbsp;<span>${ykjReport.dividendSix==null ? '--' : ykjReport.dividendSix}</span></div>
			<p>根据所选择的时间范围计算出的电量消耗</p>
			</div>
		</li>

		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>代理收益:</span>&nbsp;<span>${ykjReport.dividendThree==null ? '--' : ykjReport.dividendThree}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的代理收益</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>手续费:</span>&nbsp;<span>${ykjReport.dividendTen==null ? '--' : ykjReport.dividendTen}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总手续费</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>静态奖励:</span>&nbsp;<span>${ykjReport.dividendNine==null ? '--' : ykjReport.dividendNine}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总静态奖励</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<div>--%>
				<%--<div><span>手续费:</span>&nbsp;<span>${ykjReport.dividendTen==null ? '--' : ykjReport.dividendTen}</span></div>--%>
				<%--<p>根据所选择的时间范围计算出的总手续费统计</p>--%>
			<%--</div>--%>
		<%--</li>--%>
		<li>
			<div>
				<div><span>BTC收益:</span>&nbsp;<span>${ykjReport.dividendThirteen<0.0000000001 ?'0' : ykjReport.dividendThirteen}</span></div>
				<p>根据所选择的时间范围计算出的BTC收益</p>
			</div>
		</li>
		<li>
			<div>
				<div><span>BTC管理费:</span>&nbsp;<span>${ykjReport.dividendFourteen>-0.0000000001 ?'0' : ykjReport.dividendFourteen}</span></div>
				<p>根据所选择的时间范围计算出的BTC管理费</p>
			</div>
		</li>
			<li>
				<div>
					<div><span>BTC提现:</span>&nbsp;<span>${ykjReport.dividendFifteen<0.0000000001 ?'0': ykjReport.dividendFifteen}</span></div>
					<p>根据所选择的时间范围计算出的BTC提现总额</p>
				</div>
			</li>
	</ul>
</div>
<%--<blockquote class="layui-elem-quote" style="height: 20px;margin-top: 490px;">余额统计</blockquote>--%>
<%--<div class="bochu-one">--%>
	<%--<div>--%>
		<%--<div>--%>
			<%--<div><span>冻结资金:</span>&nbsp;<span>${ykjReport.dividendNine==null ? '--' : ykjReport.dividendNine}</span></div>--%>
			<%--<p>所有用户的冻结资金</p>--%>
		<%--</div>--%>
	<%--</div>--%>
<%--</div>--%>
</body>
</html>