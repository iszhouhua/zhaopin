$(function(){
    $('.select2').select2({
        placeholder: "请选择城市",
        allowClear: true
    });
    var myChart = echarts.init(document.getElementById('skill'));
    $.post('skillStatistics').done(function (data) {
        // 指定图表的配置项和数据
        option = {
            title: {
                text: '技能要求TOP 50'
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
                data:data.skill,
                splitLine:{
                    show:false
                },
                axisTick:{
                    show:false
                }
            },
            series: [{
                name:'出现次数最多的50项技术',
                type:'bar',
                data:data.total,
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
        myChart.setOption(option);
    });


    function skillStatistics(cityName,jobName) {
        $.post('skillStatistics',{"city":cityName,"name":jobName},function(data){
            myChart.setOption({
                yAxis : [{data:data.skill }],
                series: [{data:data.total }]
            });
        });
    }

    $('#btn_submit').click(function () {
        var city=$('#city').select2("val");
        var name=$('#keyword').val();
        skillStatistics(city,name);
    });
});