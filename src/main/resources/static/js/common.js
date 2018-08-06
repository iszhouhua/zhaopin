$(function(){
    var activeTag=$('#navigation>.active');

    $("#navigation>li>a").click(function(){
        activeTag.removeAttr("class");
        activeTag = $(this).parent();
        activeTag.attr("class", "active");
        var url = $(this).attr("href");
        $("#content").load(url,function (responseTxt,statusTxt,xhr) {
            if(statusTxt=="error") {
                console.error("加载过程中出现错误: " + xhr.status + ": " + xhr.statusText);
                $("#content").load("/"+xhr.status)
            }
        });
        return false;
    });
});