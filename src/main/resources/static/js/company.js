$(function(){
    //统计公司类型
    $.get('companyNature',function (data) {
        var nature=[],total=[];
        for(var i=0;i<data.length;i++){
            nature.push(data[i].nature);
            total.push(data[i].total);
        }
        option = {
            title : {
                text: '公司类型',
                subtext: '数据来自智联招聘',
                x: 'center'
            },
            tooltip : {
                trigger: 'axis'
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data :nature
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'公司数',
                    type:'bar',
                    data:total
                }
            ]
        };
        var mychart=echarts.init(document.getElementById("company_nature"),"macarons");
        mychart.setOption(option);
    });

    //统计公司规模
    $.get('companyScale',function (data) {
        var scale=[],total=[];
        for(var i=0;i<data.length;i++){
            scale.push(data[i].scale);
            total.push(data[i].total);
        }
        option = {
            title : {
                text: '公司规模',
                subtext: '数据来自智联招聘',
                x: 'center'
            },
            tooltip : {
                trigger: 'axis'
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data :scale
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'公司数',
                    type:'bar',
                    data:total
                }
            ]
        };
        var mychart=echarts.init(document.getElementById("company_scale"),"macarons");
        mychart.setOption(option);
    });

    $.get('companyIndustry',function (data) {
        var industry=[],total=[];
        for(var i=0;i<data.length;i++){
            industry.push(data[i].industry);
            total.push(data[i].total);
        }
        option = {
            title : {
                text: '公司行业',
                subtext: '数据来自智联招聘',
                x: 'center'
            },
            tooltip : {
                trigger: 'axis'
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data :industry
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'公司数',
                    type:'bar',
                    data:total
                }
            ]
        };
        var mychart=echarts.init(document.getElementById("company_industry"),"macarons");
        mychart.setOption(option);
    });
})