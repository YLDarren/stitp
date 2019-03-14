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

    //5、初始化
    init();

});

//初始化事件
function init() {
    var score = readCookie('scores');
    if(score != null){
        var titleList = ['一' , '二' , '三' , '四' , '五' , '六' , '七' , '八' , '九' , '十'];
        var scores = score.split(",");
        var thead = '<thead><tr><td>题号</td>';
        var tbody = '<tbody><tr><td>分数</td>';
        for(var i = 0 ; i < scores.length ; i++){
            if(i === scores.length - 1){
                thead += '<td>总分</td></tr></thead>';
                tbody += '<td class="sumScore">'+scores[i]+'</td></tr></tbody>';
            }else{
                thead += '<td>'+(i < 10 ? titleList[i] : i + 1)+'</td>';
                tbody += '<td><input type="text" value="'+scores[i]+'"></td>';
            }
        }
        $('.score-table table').html(thead + tbody);
        // '<thead>' +
        // '<tr><td>题号</td><td>一</td><td>二</td><td>三</td><td>总分</td></tr>' +
        // '</thead>' +
        // '<tbody>' +
        // '<tr><td>分数</td><td><input type="text" value=""></td><td><input type="text" value=""></td><td><input type="text" value=""></td><td class="sumScore">100</td></tr>' +
        // '</tbody>'
    }
}

//识别事件
function identify() {
    //1、获取成绩条
    var scores = getScore();

    //2、向后端获取识别结果
    //2.1、获取上传到服务器的图像名字
    var fileName = getUploadFileName();
    if(fileName == null || !judgeNum(fileName)){
        alert("请先上传图像");
        //4、把按钮变灰
        $('.shibie').attr('disabled',true);
        $('.shibie').css({'background-color':'#7a7a7a' , 'color':'#474747' })
        return;
    }

    //2.2获取识别结果
    identifyScores(fileName , scores);

    console.log(scores)
    //3、把成绩条写入到cookie中
    writeCookie('scores' , scores);

    //4、把按钮变灰
    $(this).attr('disabled',true);
    $(this).css({'background-color':'#7a7a7a' , 'color':'#474747' })

    //5、加载缓冲条
    $('.loadBox').css('display' , 'block')
}

//获取上传到服务器的图像名字
function getUploadFileName() {
    var fileName = readCookie('imgName');
    fileName = fileName == null ? fileName : fileName.split('.')[0];
    return fileName;
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
function identifyScores(fileName , scores) {
    $.ajax({
        url: "/stitp/identify",
        type: "post",
        dataType: "json",
        data: {
            "imgName": fileName,
            "scores": scores + ""
        },
        success: function(data){
            if(!data.status){
                $('.loadBox').css('display' , 'none');
                alert('服务异常，请稍后再试');
                return;
            }
            data = data.data;
            data = data.replace(/\"/gi , "'");
            data = data.replace(/\'/gi , '"');
            data = JSON.parse(data);

            console.log(data)

            if(data.isSucess){
                var scores = [];
                for(var i = 1 ; i <= data.count ; i++){
                    scores.push(data['result'+i])
                }

                console.log(scores);

                //把加载条隐藏掉
                $('.loadBox').css('display' , 'none');

                //把成绩渲染到页面中
                if(scores != null && scores.length != 0){
                    var titleList = ['一' , '二' , '三' , '四' , '五' , '六' , '七' , '八' , '九' , '十'];
                    var thead = '<thead><tr><td>题号</td>';
                    var tbody = '<tbody><tr><td>分数</td>';
                    for(var i = 0 ; i < scores.length ; i++){
                        if(i === scores.length - 1){
                            thead += '<td>总分</td></tr></thead>';
                            tbody += '<td>'+scores[i]+'</td></tr></tbody>';
                        }else{
                            thead += '<td>'+(i < 10 ? titleList[i] : i + 1)+'</td>';
                            tbody += '<td>'+scores[i]+'</td>';
                        }
                    }
                    $('.resultBox table').html(thead + tbody);
                }
            }else{
                $('.loadBox').css('display' , 'none');
                alert(data.information)
            }
        },
        error: function(error){
            $('.loadBox').css('display' , 'none');
            alert('网络异常，请稍后再试');
        }
    })
}

//获取成绩条
function getScore(){
    var tbList = $('.score-table table tbody tr td');
    var scores = [];
    for(var i = 1 ; i < tbList.length ; i++){
        scores.push(i == tbList.length - 1 ? $(tbList.get(i)).html() : $(tbList.get(i).children).val());
    }
    return scores;
}

//添加表格事件
function addTable() {
    var titleList = ['一' , '二' , '三' , '四' , '五' , '六' , '七' , '八' , '九' , '十'];
    var thList = $('.score-table table thead tr td');
    var tbList = $('.score-table table tbody tr td');
    var thLen = thList.length;
    var tdLen = tbList.length;
    if(thLen === tdLen){
        //长度相等
        var title = thLen < 12 ? titleList[thLen - 2] : thLen - 1;
        $(thList.get(thLen - 2)).after('<td>'+title+'</td>');
        $(tbList.get(tdLen - 2)).after('<td><input type="text" value=""></td>');
    }
    //统计总分
    sumScores();
}

//减少表格事件
function delTable() {
    var thList = $('.score-table table thead tr td');
    var tbList = $('.score-table table tbody tr td');
    var thLen = thList.length;
    var tdLen = tbList.length;
    if(thLen === tdLen && thLen > 2){
        //长度相等
        $(thList.get(thLen - 2)).remove();
        $(tbList.get(tdLen - 2)).remove();
    }
    //统计总分
    sumScores();
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
        url: "/stitp/upload",
        type: "post",
        dataType: "json",
        cache: false,
        data: new FormData($("#formTag")[0]),
        processData: false,// 不处理数据
        contentType: false, // 不设置内容类型
        success: function(data){
            console.log(data);
            if(data.status === true){
                //上传成功
                //设置背景
                $(self).parent().css({
                    "background-image": "url("+data.data.url+")"
                })
                writeCookie('imgName' , data.data.imgName);
                //把识别按钮设置成可点击
                $('.shibie').attr('disabled',false);
                $('.shibie').css({'background-color':'#B1FFFF' , 'color':'#317EF3' });
                $('.loadBox').css('display' , 'none')
            }else{
                alert(data.reason)
            }
        },
        error: function(error){
            console.log(error);
            alert('图像上传失败,请检查下是否网络的原因');

        }

    })
}

