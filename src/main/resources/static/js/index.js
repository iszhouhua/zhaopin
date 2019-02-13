$(function(){
    // 基于准备好的dom，初始化echarts实例
    var cityMapChart = echarts.init(document.getElementById('cityMap'));
    var cityChart = echarts.init(document.getElementById('city'));
    //同步获取地图坐标
    var geoCoordMap={};
    $.ajaxSettings.async = false;
    $.getJSON("/plugins/echarts/geoCoord.json",function(data){
        for (var province of data) {
            geoCoordMap[province.name] = [province.log,province.lat];
            for (var city of province.children) {
                geoCoordMap[city.name] = [city.log,city.lat];
            }
        }
    });

    /*var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };*/

    // 异步加载数据
    /*$.get('cityStatistics').done(function (data) {
        var result=[];
        for(var i=0;i<data.length;i++){
            result.push({'name':data[i].city,'value':data[i].total});
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
    });*/

    $.get('cityStatistics').done(function (data) {
        var cityArray=[],totalArray=[],cityMapArray = [];
        for (var i = 0; i < data.length; i++) {
            //前20放入第二个图表
            if(i<20){
                cityArray.push(data[i].city);
                totalArray.push(data[i].total);
            }
            //放入图表1
            var geoCoord = geoCoordMap[data[i].city];
            if (geoCoord) {
                cityMapArray.push({
                    name: data[i].city,
                    value: geoCoord.concat(data[i].total)
                });
            }
        }
        // 指定图表的配置项和数据
        setCityChartOption(cityArray,totalArray);
        setCityMapChartOption(cityMapArray);
    });

    function setCityChartOption(city,total) {
        cityChart.setOption({
            title: {
                text: '招聘职位数TOP 20'
            },
            tooltip: {
                trigger: 'item'
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
                name:'职位数Top 20',
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
        });
    }

    function setCityMapChartOption(data){
        cityMapChart.setOption({
            backgroundColor: '#404a59',
            title: {
                text: '各城市招聘信息统计',
                subtext: '招聘信息数据来自于智联招聘',
                sublink: 'https://www.zhaopin.com/',
                left: 'center',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function (param) {
                    return param.seriesName+'<br/>'+param.marker+param.data.name+'：'+param.data.value[2];
                }
            },
            geo: {
                map: 'china',
                label: {
                    emphasis: {
                        show: false
                    }
                },
                roam: true,
                itemStyle: {
                    normal: {
                        areaColor: '#323c48',
                        borderColor: '#111'
                    },
                    emphasis: {
                        areaColor: '#2a333d'
                    }
                }
            },
            series : [
                {
                    name: '职位数',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    data: data,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#ddb926'
                        }
                    }
                },
                {
                    name: '职位数Top 5',
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    data: data.sort(function (a, b) {
                        return b.value - a.value;
                    }).slice(0, 5),
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#f4e925',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                }
            ]});
    }
});