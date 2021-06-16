//获得所有属性name为fav的多选框
var fav=document.getElementsByName("fav");
// alert(fav.length)
//判断是否全选
function checkTest1(th){
    var flag=th.checked;//点击全选
    for(var i in fav){
        fav[i].checked=flag;
    }
f()
}

//单选决定全选操作
function checkTest2(){
    var flag=true;
    for(var i=0;i<fav.length-1;i++){
        if(!fav[i].checked){//如果有有个没被选择，则返回false
            flag=false;
            break;
        }
    }
    //决定框是否勾选
    // fav[0].checked=flag;
    fav[fav.length-1].checked=flag;
f();

}
function f() {
    //商品的总价格
    var zong=0;
    //统计是否有勾选的对象
    var num=0;
    //统计商品的数量
    var spNum=0;
    for(var i=0;i<fav.length-1;i++){
        // alert(12)
        if(fav[i].checked){

            //获得父节点
            num++;
            var par=fav[i].parentNode.parentNode;
            //获得指定ul下面的所有的li
            var jiage=par.children[2].children[2].innerText;
            // alert(jiage);
            //单个商品的价格
            var d=jiage.split("￥")[1];
            //获取单个商品的数量
            var g=par.children[4].children[1].value;
            // alert(g);
            var z=Number(d)*Number(g)
            //获得所有商品的总价格
            zong+=Number(z);
            document.getElementById("zongz").innerText=zong;
            //
            //
            // //获得商品的数量
            // var z2=li[5].getElementsByTagName("input");
            // var num2=z2[0].value;
            // spNum+=Number(num2);
            // //获得商品数量统计的对象
            // document.getElementById("snum").innerText=spNum;



        }
    }

    if(num==0){
        document.getElementById("zongz").innerText=0;
        // document.getElementById("snum").innerText=0;
    }
}
