<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>配置管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("[name=inputForm]").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            <%--if(${options.system_trans.trans_open}){--%>
                <%--$("#isOpenSwitch").attr("checked",true);--%>
                <%--$("#transOpenSetDiv").hide();--%>
            <%--}else{--%>
                <%--$("#isOpenSwitch").attr("checked",false);--%>
                <%--$("#transOpenSetDiv").show();--%>
            <%--}--%>
        });


    </script>


</head>
<body>
<sys:message content="${message}"/>
<div class="layui-tab layui-tab-brief" lay-filter="optionlayuitab">
    <ul class="layui-tab-title">
        <c:forEach items="${optionList}" var="dic" varStatus="status">

            <li lay-id="${dic.optionName}"
                class="${dic.optionName  eq selOptLabel ? 'layui-this':''}">${dic.optionLabel } [${dic.optionName }]
            </li>
        </c:forEach>
    </ul>
    <div class="layui-tab-content">

        <div class="layui-tab-item layui-show">

            <form name="inputForm" modelAttribute="optionValue"
                  action="${ctx}/user/userOptions/saveHelpGroup" method="post"
                  class="form-horizontal layui-form">
                <input type="hidden" name="optName" value="system_help"/>
                <blockquote class="layui-elem-quote">基本配置</blockquote>
                <div class="control-group">
                    <label class="control-label">电费：</label>
                    <div class="controls">
                        <input type="number" name="power_money"
                               value="${options.system_help.power_money }" htmlEscape="false"
                               class="input-xlarge required"/>
                        <span class="help-inline"><font
                                color="red">*</font> 标识:[power_money] </span>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">管理费：</label>
                    <div class="controls">
                        <input type="number" name="manage_money"
                               value="${options.system_help.manage_money }" htmlEscape="false"
                               class="input-xlarge required"/>
                        <span class="help-inline"><font
                                color="red">*</font> 标识:[manage_money] </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">BTC美元转人民币配置：</label>
                    <div class="controls">
                        <input type="number" name="btc_rate"
                               value="${options.system_help.btc_rate }" htmlEscape="false"
                               class="input-xlarge required"/>
                        <span class="help-inline"><font
                                color="red">*</font> 标识:[btc_rate] </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">BTC转换余额手续费配置：</label>
                    <div class="controls">
                        <input type="number" name="change_rmb"
                               value="${options.system_help.change_rmb }" htmlEscape="false"
                               class="input-xlarge required"/>
                        <span class="help-inline"><font
                                color="red">*</font> 标识:[change_rmb] </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">BTC最低转换(提现额度)：</label>
                    <div class="controls">
                        <input type="number" name="change_low"
                               value="${options.system_help.change_low }" htmlEscape="false"
                               class="input-xlarge required"/>
                        <span class="help-inline"><font
                                color="red">*</font> 标识:[change_low] </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">BTC提现单笔手续费配置：</label>
                    <div class="controls">
                        <input type="number" name="change_one"
                               value="${options.system_help.change_one }" htmlEscape="false"
                               class="input-xlarge required"/>
                        <span class="help-inline"><font
                                color="red">*</font> 标识:[change_one] </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">用户头像统一配置：</label>
                    <div class="controls">
                        <input type="hidden" id="userLogo" name="user_logo" value="${options.system_help.user_logo}" htmlEscape="false" maxlength="255" class="input-xlarge" >
                        <sys:ckfinder input="userLogo" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100" />
                        <span class="help-inline"><font color="red"></font>标识:[userLogo]</span>
                    </div>
                </div>

                <div class="form-actions">
                    <shiro:hasPermission name="user:userUserinfo:edit">
                        <input id="btnSubmit" class="btn btn-primary" type="submit"
                               value="保 存 配 置"/>&nbsp;</shiro:hasPermission>
                </div>

            </form>
        </div>

        <div class="layui-tab-item">
        </div>
        <div class="layui-tab-item">其他设置</div>
        <div class="layui-tab-item">4</div>
        <div class="layui-tab-item">5</div>
        <div class="layui-tab-item">6</div>
    </div>
</div>



<script type="text/javascript">
    var form = null;
    var messageRemindForm = null;
    var messageRemindForm2 = null;
    layui.use(['element', 'form', 'template'], function () {
        var element = layui.element();

        messageRemindForm = layui.form("messageRemindForm");
        messageRemindForm2 = layui.form("messageRemindForm2");
        form = layui.form();
        element.tabChange('optionlayuitab', '${selOptLabel}'); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项


        form.on('switch(shopOpenStatus)', function(data){
            if(data.elem.checked){
                $("#transOpenSetDiv").hide();

                $("#istransopen").val("1");
            }
            else{
                $("#transOpenSetDiv").show();
                $("#istransopen").val("0");
            }
        });

        messageRemindForm.on('switch(shortMessageStatus)', function(data){
            if(data.elem.checked){
                $("#remind").show();

            }
            else{
                $("#remind").hide();

            }
        });

        messageRemindForm2.on('switch(shortMessageStatus2)', function(data){
            if(data.elem.checked){
                $("#remind2").show();

            }
            else{
                $("#remind2").hide();

            }
        });
    });
</script>
</body>
</html>