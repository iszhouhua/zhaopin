$(function(){
    $('.select2').select2();
    option = {
        title : {
            subtext: '数据来自智联招聘',
            x:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            x : 'left',
            data:['最高','最低','平均薪资']
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
                data : []
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel: {
                    formatter: '{value} 元'
                }
            }
        ],
        series : [
            {
                name:'最高',
                type:'bar',
                data:[],
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'最低',
                type:'bar',
                data:[],
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            },
            {
                name: '平均薪资',
                type: 'line',
                data: []
            }
        ]
    };
    var salaryExp=echarts.init(document.getElementById("salary_exp"),"macarons");
    salaryExp.setOption(option);
    findSalaryExp();
    var salaryEducation=echarts.init(document.getElementById("salary_education"),"macarons");
    salaryEducation.setOption(option);
    findSalaryEducation();

    //统计工作经验与工资的关系
    function findSalaryExp(cityName,jobName) {
        salaryExp.showLoading();
        $.post("jobExpAndSalary",{"city":cityName,"name":jobName},function(data){
            salaryExp.hideLoading();
            salaryExp.setOption({
                title : {
                    text: '工作经验与工资的关系'
                },
                xAxis : [{data : data.experience}],
                series :[
                    {data:data.maxPrice},
                    {data:data.minPrice},
                    {data: data.avgPrice}
                ]
            });
        });
    }

    //统计学历与工资的关系
    function findSalaryEducation(cityName,jobName) {
        salaryEducation.showLoading();
        $.post("jobEducationAndSalary",{"city":cityName,"name":jobName},function(data){
            salaryEducation.hideLoading();
            salaryEducation.setOption({
                title : {
                    text: '学历与工资的关系'
                },
                xAxis : [{data : data.education}],
                series :[
                    {data:data.maxPrice},
                    {data:data.minPrice},
                    {data: data.avgPrice}
                ]
            });
        });
    }


    option2 = {
        title : {
            subtext: '数据来自智联招聘',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:[]
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[]
            }
        ]
    };

    $('#btn_submit').click(function () {
        var city=$('#city').select2("val");
        var name=$('#keyword').val();
        findSalaryExp(city,name);
        findSalaryEducation(city,name);
    });
});