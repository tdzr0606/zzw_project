layui.use(['layer', 'jquery'], function () {
    var layer = layui.layer;
    var device = layui.device();

    //ie检测，直接关闭浏览器
    if (device.ie) {
        layer.alert("还用ie？再见！");
        window.opener = null;
        window.open(' ', '_self', ' ');
        window.close();
    }

    //存储系统信息
    layui.data('sysInfo',
        {key: 'unit', value: '昆明微布科技'}
        , {key: 'webName', value: '创新人才库'}
    );
});



