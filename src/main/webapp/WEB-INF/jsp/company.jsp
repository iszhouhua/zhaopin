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
                <td width="250px" style="color:red;text-align:left;">公司信息</td>
                <td width="90px">选择省份:</td>
                <td width="100px">
                    <div class="ui dropdown">
                        <input type="hidden" id="province" value=""/>
                        <div class="default text">选择省</div>
                        <i class="dropdown icon"></i><!-- 向下的箭头 -->
                        <div class="menu" id="provincelist">
                            <!--省份列表  -->
                        </div>
                    </div>
                </td>
                <td width="80px">选择城市:</td>
                <td width="120px">
                    <div class="ui dropdown">
                        <input type="hidden" id="city" value=""/>
                        <div class="default text">先选择省</div>
                        <i class="dropdown icon"></i><!-- 向下的箭头 -->
                        <div class="menu" id="citylist">
                            <!--市区列表  -->
                        </div>
                    </div>
                </td>
                <td align="right">
                    <button class="ui primary button" id="search">搜索</button>
                </td>
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

<!-- 省市级联 -->
<script type="text/javascript" src="js/city.js"></script>

<!-- 公司统计 -->
<script type="text/javascript" src="js/searchCompany.js"></script>

<script type="text/javascript">
    $(function(){
        $(".ui.dropdown").dropdown();//下拉框生效
        $(".treebox .level1>a").click(function(){
            $(this).addClass('current')   //给当前元素添加"current"样式
                .find('i').addClass('down')   //小箭头向下样式
                .parent().next().slideDown('slow','easeOutQuad')  //下一个元素显示
                .parent().siblings().children('a').removeClass('current')//父元素的兄弟元素的子元素去除"current"样式
                .find('i').removeClass('down').parent().next().slideUp('slow','easeOutQuad');//隐藏
            return false; //阻止默认时间
        });
    })
</script>
</body>
</html>