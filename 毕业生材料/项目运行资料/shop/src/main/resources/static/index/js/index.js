// JavaScript Document
var token = sessionStorage.getItem('token');


$(".scroll a").click(function(){
	$(this).addClass('currer').siblings().removeClass('currer');
	})


$(window.document).scroll(function () {
	var t_shu=$(".banner").height();
	var g_shu=$(".hot").height();
	if($(window).scrollTop()>t_shu){
			$(".top").css({background:"#fff",borderBottom:"1px solid #ccc"})
		}else{
			$(".top").css({background:"transparent",borderBottom:"none"})
			};
	if($(window).scrollTop()>t_shu+g_shu){
			$(".nav_s").addClass('pos')
		}else{
			$(".nav_s").removeClass('pos')
			};
})


$(".dao").click(function(){
	$('.dao i,.dao span').css('color','#7b7b7b');
	$(this).find('i,span').css('color','#246fc0');
	})

$(".left_c li").click(function(){
	$('.left_c li a').css('color','#484848');
	$(this).css({background:"#fff"}).siblings().css({background:"transparent"});
	$(this).find('a').css('color','#246fc0');
	})


//商家
$(".titll .col-xs-4,.titll .col-xs-2").click(function(){
	$('.titll .col-xs-2').css('color','#666');
	$(this).css('color','#246fc0').siblings().css('color','#666');
	})

$(".sp_s").click(function(){
	$('.lie').css('display','block').siblings().css('display','none');
	$('.footer').css('display','block');
	})

$(".sp_p").click(function(){
	$('.ping').css('display','block').siblings().css('display','none');
	$('.footer').css('display','none');
	})

$(".sp_j").click(function(){
	$('.shop').css('display','block').siblings().css('display','none');
	$('.footer').css('display','none');
	})


$(".shou").click(function(){
if($(".icon-shoucang1").css("display")=="none"){
$(".icon-shoucang1").show();
$(".icon-shoucang").hide();
}else{
$(".icon-shoucang1").hide();
$(".icon-shoucang").show();
}
});

function les(id){
	//获得文本框对象
	var t = $("#text_box_"+id);
	//初始化数量为1,并失效减
	if (parseInt(t.val())==1){
		//$('#min_'+id).setAttribute('pointer-events', 'none');
        var r = confirm("确认移出购物车吗？");
        if (r == true) {
            ajaxadd(id,1);
        }
	}else{
        ajaxadd(id,2);
	}

	//数量减少操作

	//t.val(parseInt(t.val())-1);
	//f();
}

function addbtn(id,inventory){
    //alert("库存："+inventory);
    //获得文本框对象
    var t = $("#text_box_"+id);
    //初始化数量为1,并失效减
    if (parseInt(t.val())>=inventory){
        //$('#min_'+id).setAttribute('pointer-events', 'none');
        var r = confirm("库存不足！");
        if (r == true) {
            window.location.reload() ; //刷新页面
        }
    }else{
        ajaxadd(id,0);
    }
    // //获得文本框对象
    // var t = $("#text_box_"+id);
    // //初始化数量为1,并失效减
    // //$('#min_'+id).setAttribute('pointer-events', 'none');
    // //数量增加操作
    // // alert(id);
    // t.val(parseInt(t.val())+1)
    // //f();
    // if (parseInt(t.val())>1){
    // 	$('#min_'+id).removeAttribute('pointer-events');
    // }


}

// function commodity(id){
//     sessionStorage.setItem("goodsId",id);
//     sessionStorage.setItem('token',token);
//     window.location="/shop/index/commodity";
// }

function ajaxadd(id,status) {
    // sessionStorage.clear();
    var mid = sessionStorage.getItem('mid');
    //var token = sessionStorage.getItem('token');
    console.log("hhh:"+mid+token);
    //alert("mid"+mid+"___"+id);
    if (mid!=null){
        $.ajax({
            url : "/shop/shopcart/add"
            , headers:{"token":token,
                "Access-Control-Allow-Origin":"*"}
            ,type : "post"
            ,data:JSON.stringify({"mId":mid,"gId":id,"status":status})
            ,contentType: 'application/json'
            ,success: function(data){
                console.log(data.toString());
                window.location.reload() ; //刷新页面
            }
        });
	}else{
        var r = confirm("立即前往登录界面？");
        if (r == true) {
            window.location.href='/shop/index/login'
        }

	}

}


function add(id,inventory){
    //alert("库存："+inventory);
    //获得文本框对象
    var t = $("#text_box_"+id);
    //初始化数量为1,并失效减
    if (parseInt(t.val())>=inventory){
        //$('#min_'+id).setAttribute('pointer-events', 'none');
        var r = confirm("库存不足！");
        if (r == true) {
            window.location.reload() ; //刷新页面
        }
    }else{
        ajaxadd(id,0);
    }
	// //获得文本框对象
	// var t = $("#text_box_"+id);
	// //初始化数量为1,并失效减
	// //$('#min_'+id).setAttribute('pointer-events', 'none');
	// //数量增加操作
	// // alert(id);
	// t.val(parseInt(t.val())+1)
	// //f();
	// if (parseInt(t.val())>1){
	// 	$('#min_'+id).removeAttribute('pointer-events');
	// }


}
//获得所有属性name为fav的多选框
var fav=document.getElementsByName("fav");
//判断是否全选
function checkTest1(th){
	var flag=th.checked;//点击全选
	//alert(flag);
	/*	if(flag){
            for(var i in fav){
                fav[i].checked=true;
            }
        }else{
            for(var i in fav){
                fav[i].checked=false;
            }
        }
    */

	for(var i in fav){
		fav[i].checked=flag;
	}
}
function getFormatDate(){
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
    var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
    var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() : nowDate.getHours();
    var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
    var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
    return year + "-" + month + "-" + date+" "+"16:00:00";
}
//单选决定全选操作
function checkTest2(){
	var flag=true;
	for(var i=1;i<fav.length-1;i++){
		if(!fav[i].checked){//如果有有个没被选择，则返回false
			flag=false;
			break;
		}
	}
	//决定框是否勾选
	fav[0].checked=flag;
	fav[fav.length-1].checked=flag;

	//价格是否统计
	//商品的总价格
	var zong=0.00;
	//统计是否有勾选的对象
	var num=0;
	//统计商品的数量
	var spNum=0;

	for(var i=1;i<fav.length-1;i++){
		if(fav[i].checked){
			//获得ul父节点
			num++;
			var par=fav[i].parentNode.parentNode;
			//获得指定ul下面的所有的li
			var li=par.getElementsByTagName("li");
			//单个商品的总价格
			var z=li[6].innerText.split("￥")[1];
			//获得所有商品的总价格
			var z1=Number(z).toFixed(2);
			zong=Number(zong).toFixed(2)+z1;
			document.getElementById("zongz").innerText=zong.toFixed(2);


			//获得商品的数量
			var z2=li[5].getElementsByTagName("input");
			var num2=z2[0].value;
			spNum+=Number(num2);
			//获得商品数量统计的对象
			document.getElementById("snum").innerText=spNum;



		}
	}

	if(num==0){
		document.getElementById("zongz").innerText=0.00;
		document.getElementById("snum").innerText=0;
	}
}

// $(document).ready(function(){
// //获得文本框对象
//    var t = $("#text_box");
// //初始化数量为1,并失效减
// $('#min').attr('disabled',true);
//     //数量增加操作
//     $("#add").click(function(){
//         t.val(parseInt(t.val())+1)
//         if (parseInt(t.val())!=1){
//             $('#min').attr('disabled',false);
//         }
//
//     })
//     //数量减少操作
//     $("#min").click(function(){
//         t.val(parseInt(t.val())-1);
//         if (parseInt(t.val())==1){
//             $('#min').attr('disabled',true);
//         }
//
//     })
//
// });


$('.icon-iconfontgouwuche').click(function(){
	$('.window').css('display','block');
	$('.gou').css('display','block');
	})

$('.window').click(function(){
	$('.window').css('display','none');
	$('.gou').css('display','none');
	})


//支付

$(".zhong").click(function(){
if($(".icon-xuanzhong").css("display")=="none"){
$(".icon-xuanzhong").show();
$(".icon-weixuanzhong").hide();
}else{
$(".icon-xuanzhong").hide();
$(".icon-weixuanzhong").show();
}
});





//商家订单

$('.fa').click(function(){
	$('.window').css('display','block');
	$('.tis').css('display','block');
	})

$('.window').click(function(){
	$('.window').css('display','none');
	$('.tis').css('display','none');
	})





