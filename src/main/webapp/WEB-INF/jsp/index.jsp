<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>招聘信息统计平台</title>
	<link rel="stylesheet" href="css/semantic.min.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<style type="text/css">
	#companynature,#scale{
		width:700px;
		height:300px;
		margin:0 auto;
		/* background-color:#948; */
	}
	#industry{
		width:1000px;
		height:300px;
		margin:0 auto;
		/* background-color:#948; */
	}
	</style>
</head>
<body>
<div id="main">
	<div class="login" style="color:#fff;">当前所在的城市：<span id="cityname">null</span></div>
	<!--导航栏start-->
	<div class="line">
		<div class="top">招聘信息统计平台</div>
		<div id="topmenu" class="ui inverted red menu">
			<a href="index.html" class="item">首页</a>
			<a href="job.html" class="item">招聘信息</a>
			<a href="company.html" class="item">公司信息</a>
			<a href="update.html" class="item right">数据更新</a>
		</div>
	</div>
	<!--导航栏end-->
	
	<!-- 搜索start -->
	<div class="search">
		<table class="ui table" style="background:rgba(228, 241, 221, 0.26);">
			<tr align="center">
				<td width="250px" style="color:red;text-align:left;">首页</td>
				<td width="400px"><strong>首页显示已入库的所有公司的统计信息</strong></td>
				<td width="250px">&nbsp;</td>
			</tr>
		</table>
	</div>
	<!-- 搜索end -->

	<!--内容栏start-->
	<div class="content">
		<div class="online">
			<div id="companynature" class="chr">
				<!-- 公司类型 -->
			</div>
			<div id="scale" class="chr">
				<!-- 公司规模 -->
			</div>
			<div id="industry" class="chr">
				<!-- 公司行业 -->
			</div>
		</div>
	</div>
	<!--内容栏end-->
	
	<!-- 底部开始 -->
	<div class="bottom">
      <ul class="bottom-left">
        <li>
          <ul>
            <li class="titles">关于可视化</li>
            <li><a href="javascript:void(0);">联系我们</a></li>
            <li><a href="javascript:void(0);">加入我们</a></li>
            <li><a href="javascript:void(0);">友情链接</a></li>
          </ul>
        </li>
        <li>
          <ul>
            <li class="titles">帮助中心</li>
            <li><a href="javascript:void(0);">意见反馈</a></li>
            <li><a href="javascript:void(0);">投诉中心</a></li>
            <li><a href="javascript:void(0);">服务说明</a></li>
          </ul>
        </li>
        <li>
          <ul>
            <li class="titles">平台特色</li>
            <li><a href="javascript:void(0);">安全可靠</a></li>
            <li><a href="javascript:void(0);">数据真实</a></li>
            <li><a href="javascript:void(0);">数据统计</a></li>
          </ul>
        </li>
        <li>
          <ul>
            <li class="titles">关注我们</li>
            <li><a href="javascript:void(0);">官方微信</a></li>
            <li><a href="javascript:void(0);">新浪微博</a></li>
            <li><a href="javascript:void(0);">腾讯微博</a></li>
          </ul>
        </li>
      </ul>
      <p>Copyright ©2017-2018 招聘信息统计平台网版权所有 - <a href="http://www.miitbeian.gov.cn/" target="blank_">湘ICP备88888888号</a></p>
    </div><!-- 底部结束 -->
	
	
</div>
<script type="text/javascript" src="js/echarts-all.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/semantic.min.js"></script>

<!-- 统计 -->
<script type="text/javascript" src="js/load.js"></script>
</body>
</html>