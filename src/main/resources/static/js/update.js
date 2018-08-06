var currentCity = $("#infiniteCity");
var currentIndustry = $("#infiniteIndustry");
var currentSalary = $("#infiniteSalary");
$(function () {
    $(".select2").select2().change(function () {
        currentCity.removeClass('zp-queryChecked');
        currentCity = $(this);
        $("#city").val(currentCity.val());
        $(".city_name").text(currentCity.val());
        $(".currentCity").click();
    });

    $(".currentCity").click(function () {
        $("#arrows").toggleClass("span_up");
        $("#arrows").toggleClass("span_down");
        $(".cityChild").toggle();
    });

    $(".choiseCity>li>a").click(function () {
        currentCity.removeClass('zp-queryChecked');
        $(this).addClass('zp-queryChecked');
        currentCity = $(this);
        $("#city").val(currentCity.text());
        $(".city_name").text(currentCity.text());
        $(".currentCity").click();
    });

    $("#btn_submit").click(function () {
        var param=$("#form").serialize();
        console.log(param);
        $.ajax({
            type:"post",
            url:"/updateJob",
            data:{
                "param":param
            },
            beforeSend:function(){
                $(".overlay").show();
            },
            success:function (data) {
                console.log(data);
                $(".overlay").hide();
                if (data.code==0){
                    toastr.success("更新成功!");
                }else{
                    toastr.error(data.msg);
                }
            }
        })
    });

    $("#input_city").change(function () {

    });

    $("#form").submit(function () {
        $("#btn_submit").click();
        return false;
    });
});

function chooseIndustry(obj, industry) {
    currentIndustry.removeClass("zp-queryChecked");
    $(obj).addClass("zp-queryChecked");
    currentIndustry=$(obj)
    $("#industry").val(industry)
}

function chooseSalary(obj, minSalary, maxSalary) {
    currentSalary.removeClass("zp-queryChecked");
    $(obj).addClass("zp-queryChecked");
    currentSalary=$(obj)
    $("#minSalary").val(minSalary);
    $("#maxSalary").val(maxSalary);
}

function ktChange() {
    var kt=$("#keywordType").val();
    if (kt == "2")
        $("#keyword").attr("placeholder", "请输入公司名称或关键词,例如：联想,华为等")
    else
        $("#keyword").attr("placeholder", "请输入关键词,例如:JAVA,销售代表,行政助理等")
}