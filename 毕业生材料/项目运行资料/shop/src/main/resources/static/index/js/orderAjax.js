function payment(id) {
    //alert(id);
    //paymentPay(id, 1);
    //alert("1:"+id);
    sessionStorage.setItem('oid',id);
    window.location.href='/shop/index/apply'
}

function returno(id) {
    //alert(id);
    batch_update(id, 3);
    //window.location.href='/shop/index/index'
}

function cancel(id) {
    var r = confirm("确认取消订单吗？");
    if (r == true) {
        batch_update(id, 5);
    }
}

function index() {
    window.location.href = '/shop/index/index';
}

/*停用-启用ajax*/
function batch_update(ids, orderStatus) {
    var mid = sessionStorage.getItem('mid');
    var token = sessionStorage.getItem('token');
    alert(token);
    var batchUpdate = {
        "oids": [ids],
        "status": orderStatus
    }
    console.log(JSON.stringify(batchUpdate));
    //发异步把用户状态进行更改
    $.ajax({
        url: "/shop/order/batchUpdate"
        , headers: {
            "token": token,
            "Access-Control-Allow-Origin": "*"
        }
        , type: "post"
        , data: JSON.stringify(batchUpdate)
        , contentType: 'application/json'
        , success: function (data) {
            console.log(data);
            alert(data.msg);
            //location.reload(); //成功后再刷新
        },
        error: function (data) {
            alert(data.msg);
        }
    });
}

/*停用-启用ajax*/
function paymentPay(ids, orderStatus) {
    var mid = sessionStorage.getItem('mid');
    // var mid = sessionStorage.getItem('mid');
    // var token = sessionStorage.getItem('token');
    // alert(token);
    // var batchUpdate = {
    //     "oids": [ids],
    //     "status": orderStatus
    // }
    // console.log(JSON.stringify(batchUpdate));
    // //发异步把用户状态进行更改
    // $.ajax({
    //     url: "/shop/alipay/pay"
    //     , headers: {
    //         "token": token,
    //         "Access-Control-Allow-Origin": "*"
    //     }
    //     , type: "get"
    //     , data: "orderid=" + "202119591015101"
    //     //, contentType: 'application/json'
    //     , success: function (data) {
    //         console.log(data);
    //         alert(data.msg);
    //         //location.reload(); //成功后再刷新
    //     },
    //     error: function (data) {
    //         alert(data.msg);
    //     }
    // });
}