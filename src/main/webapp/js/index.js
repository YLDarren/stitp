$(document).ready(function () {
    //1、绑定图片上传事件
    $("#file").on('change' , upload);

    //2、绑定输入事件
    $(document).on('blur' , 'input:text' , inputF);

    //3、添加/减少表格事件
    $(".jia").click(addTable);
    $(".jian").click(delTable);

    //4、识别事件
    $('.shibie').click(identify);

});

//初始化事件
function init() {
    
}

//识别事件
function identify() {
    //1、获取成绩条
    var scores = getScore();

    //2、向后端获取识别结果
    identifyScores();

    //3、把成绩条写入到cookie中
    writeCookie('scores' , scores);

    //4、把按钮变灰
    $(this).attr('disabled',true);
    $(this).css({'background-color':'#7a7a7a' , 'color':'#474747' })

    //5、加载缓冲条
    $('.loadBox').css('display' , 'block')
}

//写入cookie
function writeCookie(key , scores) {
    $.cookie(key, scores, { expires: 7 });
}

//读取cookie
function readCookie(key) {
    return $.cookie(key);
}

//向后端获取识别结果
function identifyScores() {
    
}

//获取成绩条
function getScore(){
    var tbList = $('tbody tr td');
    var scores = [];
    for(var i = 1 ; i < tbList.length ; i++){
        scores.push(i == tbList.length - 1 ? $(tbList.get(i)).html() : $(tbList.get(i).children).val());
    }
    return scores;
}

//添加表格事件
function addTable() {
    var titleList = ['一' , '二' , '三' , '四' , '五' , '六' , '七' , '八' , '九' , '十'];
    var thList = $('thead tr td');
    var tbList = $('tbody tr td');
    var thLen = thList.length;
    var tdLen = tbList.length;
    if(thLen === tdLen){
        //长度相等
        var title = thLen < 12 ? titleList[thLen - 2] : thLen - 1;
        $(thList.get(thLen - 2)).after('<td>'+title+'</td>');
        $(tbList.get(tdLen - 2)).after('<td><input type="text" value=""></td>');
    }
}

//减少表格事件
function delTable() {
    var thList = $('thead tr td');
    var tbList = $('tbody tr td');
    var thLen = thList.length;
    var tdLen = tbList.length;
    if(thLen === tdLen && thLen > 2){
        //长度相等
        $(thList.get(thLen - 2)).remove();
        $(tbList.get(tdLen - 2)).remove();
    }
}

//绑定输入事件
function inputF() {
    var str = $(this).val().trim();
    if(judgeNum(str)){
        str = str.replace(/\b(0+)/gi,"");
        if(str.length <= 2){
            $(this).val(str);
        }else{
            $(this).val("");
        }
    }else{
        $(this).val("");
    }
    //统计总分
    sumScores();
}

//计算总分
function sumScores() {
    var inputList = $("input:text");
    var sum = 0;
    for(var i = 0 ; i < inputList.length ; i++){
        sum += $(inputList.get(i)).val() == "" ? 0 : Number($(inputList.get(i)).val());
    }
    //设置总分
    $('.sumScore').html(sum)
}

//判断一个字符串是不是都是数字组成
function judgeNum(str){
    var reg = /^[0-9]+\.?[0-9]*$/;
    if (reg.test(str)) {
        return 1;
    }
    return 0;
}

//图片上传事件
function upload(){
    /**
     * 我们存一下this对象，
     * 我们将在ajax的回调函数中，
     * 将要用这个对象，
     * 用它来改变父盒子的背景图
     *
     */
    var self = this;
    //如果不理解我写的，可以看看我的前几篇文章
    $.ajax({
        url: "/upload",
        type: "post",
        dataType: "json",
        cache: false,
        data: new FormData($("#formTag")[0]),
        processData: false,// 不处理数据
        contentType: false, // 不设置内容类型
        success: function(data){
            console.log(data)
            //设置背景
            $(self).parent().css({
                "background-image": "url("+data.url+")"
            })

            //把识别按钮设置成可点击
            $('.shibie').attr('disabled',false);
            $('.shibie').css({'background-color':'#B1FFFF' , 'color':'#317EF3' });
            $('.loadBox').css('display' , 'none')
        },
        error: function(error){
            console.log(error);
            alert('图像上传失败,请检查下是否网络的原因');

        }

    })
}

