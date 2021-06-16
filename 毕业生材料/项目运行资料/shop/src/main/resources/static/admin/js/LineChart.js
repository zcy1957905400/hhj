var data0,data1,data2,data3;
$.ajax({
    url: 'http://localhost:8089/shop/statistics/LineChart',
    headers: {
        "token": "token:PC-c4ca4238a0b923820dcc509a6f75849b-20210412090817473-554972",
        "Access-Control-Allow-Origin": "*"
    },
    type: "get",
    //data:JSON.stringify(updateid),
    contentType: 'application/json',
    success: function (resp) {
        console.log(resp.data);
        data0 = resp.data[0];
        data1= resp.data[1];
        data2= resp.data[2];
        data3= resp.data[3];
        echarts_LineChart();
    }
});

function echarts_LineChart() {
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '折线图堆叠'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['销售额','订单数','提现金额']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data:data0
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'销售额',
                type:'line',
                stack: '总量',
                data:data1
            },
            {
                name:'订单数',
                type:'line',
                stack: '总量',
                data:data2
            },
            {
                name:'提现金额',
                type:'line',
                stack: '总量',
                data:data3
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}