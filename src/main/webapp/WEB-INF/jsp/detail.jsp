<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h1 class="text-success">${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <span class="glyphicon glyphicon-time"></span>
                <span id="seckill-box"></span>
            </h2>
        </div>

    </div>
</div>
<div class="modal fade" id="killPhoneModal">
    <div class="modal-dialog">
        <div class="modal-header">
            <h3 class="modal-title text-center"><span class="glyphicon glyphicon-phone"></span>秒杀电话</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2">
                    <input type="text" name="killPhone" id="killPhoneKey" placeholder="请输入电话号码..."  class="form-control">
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <span class="glyphicon" id="killPhoneMessage"></span>
            <button type="button" id="killPhoneBtn" class="btn btn-success">
                <span class="glyphicon glyphicon-phone"></span>
                提交
            </button>
        </div>
    </div>
</div>

</body>
<%@include file="common/foot.jsp"%>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<script src="/resources/script/seckill.js"></script>

<script type="text/javascript">
    $(function(){

        seckill.detail.init({
            seckillId : ${seckill.seckillId},
            startTime : ${seckill.startTime.time},
            endTime : ${seckill.endTime.time}
        })
    });
</script>
</html>
