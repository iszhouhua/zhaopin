$(function(){
    // 异步加载数据
    $.get('provinceStatistics').done(function (data) {
        var result=[];
        for(var i=0;i<data.length;i++){
            result.push({'name':data[i].province,'value':data[i].total});
        }
        // 填入数据
        var provinceChart = echarts.init(document.getElementById('province'));
        provinceChart.setOption({
            title: {
                text: '全国各地招聘信息统计',
                subtext: '招聘信息数据来自于智联招聘',
                sublink: 'https://www.zhaopin.com/'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{b}<br/>{c}'
            },
            series: [{
                name:'各省招聘数量',
                type: 'map',
                mapType: 'china',
                itemStyle:{
                    normal:{label:{show:true}},
                    emphasis:{label:{show:true}}
                },
                data: result
            }],
            visualMap: {
                min: result[result.length-1].value,
                max: result[0].value,
                text:['最高','最低'],
                realtime: false,
                calculable: true,
                itemWidth:30,
                itemHeight:200,
                inRange: {
                    color: ['#d6f5e9', '#85daef','#FEFA6E', '#FF7337', '#FF6641']
                }
            }
        });
    });

    $.get('cityStatistics').done(function (data) {
        var city=[],total=[];
        for(var i=0;i<data.length;i++){
            city.push(data[i].city);
            total.push(data[i].total);
        }
        // 基于准备好的dom，初始化echarts实例
        var cityChart = echarts.init(document.getElementById('city'));
        // 指定图表的配置项和数据
        option = {
            title: {
                text: '职位数TOP 20'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{b}<br/>{c}'
            },
            xAxis:{
                type:'value',
                position: 'top',
                splitLine:{
                    show:false
                },
                axisTick:{
                    show:false
                },
                axisLabel:{
                    show:true
                }

            },
            yAxis:{
                type:'category',
                inverse:true,
                data:city,
                splitLine:{
                    show:false
                },
                axisTick:{
                    show:false
                }
            },
            series: [{
                name:'招聘数前20的城市',
                type:'bar',
                data:total,
                color:'red',
                label: {
                    normal: {
                        position: 'right',
                        show: true,
                        color:'black'
                    }
                },
            }],
        };
        cityChart.setOption(option);
    });
});