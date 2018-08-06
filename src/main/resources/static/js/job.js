$(function(){
    $('.select2').select2({
        placeholder: "请选择城市",
        allowClear: true
    });
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
        $.post("jobExpAndSalary",{"city":cityName,"name":jobName},function(data){
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
        $.post("jobEducationAndSalary",{"city":cityName,"name":jobName},function(data){
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
    var demandExp=echarts.init(document.getElementById("demand_exp"),"macarons");
    demandExp.setOption(option2);
    findDemandExp();
    var demandEducation=echarts.init(document.getElementById("demand_education"),"macarons");
    demandEducation.setOption(option2);
    findDemandEducation();

    //统计工作经验与用人需求的关系
    function findDemandExp(cityName,jobName) {
        $.post("jobExpAndDemand",{"city":cityName,"name":jobName},function(data){
            var experience=[],name=[];
            for(var i=0;i<data.length;i++){
                name.push(data[i].experience);
                experience.push({name:data[i].experience,value:data[i].demand});
            }
            demandExp.setOption({
                title : {
                    text: '工作经验与用人需求的关系'
                },

                legend: {
                    data:name
                },
                series :[
                    {name:'工作经验',data:experience}
                ]
            });
        });
    }

    //统计学历与用人需求的关系
    function findDemandEducation(cityName,jobName) {
        $.post("jobEducationAndDemand",{"city":cityName,"name":jobName},function(data){
            var education=[],name=[];
            for(var i=0;i<data.length;i++){
                name.push(data[i].education);
                education.push({name:data[i].education,value:data[i].demand});
            }
            demandEducation.setOption({
                title : {
                    text: '学历与用人需求的关系'
                },

                legend: {
                    data:name
                },
                series :[
                    { name:'学历',data:education}
                ]
            });
        });
    }

    $('#btn_submit').click(function () {
        var city=$('#city').select2("val");
        var name=$('#keyword').val();
        findSalaryExp(city,name);
        findSalaryEducation(city,name);
        findDemandExp(city,name);
        findDemandEducation(city,name);
    });
});