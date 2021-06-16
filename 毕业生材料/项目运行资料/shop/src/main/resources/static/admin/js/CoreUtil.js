/*CoreUtil*/
/*工具类，类似java静态工具类*/
var CoreUtil = (function () {
    var coreUtil = {};
	/*存入本地缓存*/
    coreUtil.setData = function (key, value) {
        layui.data('LocalData', {
            key: key,
            value: value
        })
    };
    /*从本地缓存拿数据*/
    coreUtil.getData = function (key) {
        var localData = layui.data('LocalData');
        return localData[key];
    };

}