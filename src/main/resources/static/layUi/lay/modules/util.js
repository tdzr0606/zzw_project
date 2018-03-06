/**

 @Name：layui.util 工具集
 @Author：贤心
 @License：MIT

 */

layui.define('jquery', function (exports) {
    "use strict";

    var $ = layui.$

        //外部接口
        , util = {
            //固定块
            fixbar: function (options) {
                var ELEM = 'layui-fixbar', TOP_BAR = 'layui-fixbar-top'
                    , dom = $(document), body = $('body')
                    , is, timer;

                options = $.extend({
                    showHeight: 200 //出现TOP的滚动条高度临界值
                }, options);

                options.bar1 = options.bar1 === true ? '&#xe606;' : options.bar1;
                options.bar2 = options.bar2 === true ? '&#xe607;' : options.bar2;
                options.bgcolor = options.bgcolor ? ('background-color:' + options.bgcolor) : '';

                var icon = [options.bar1, options.bar2, '&#xe604;'] //图标：信息、问号、TOP
                    , elem = $(['<ul class="' + ELEM + '">'
                    , options.bar1 ? '<li class="layui-icon" lay-type="bar1" style="' + options.bgcolor + '">' + icon[0] + '</li>' : ''
                    , options.bar2 ? '<li class="layui-icon" lay-type="bar2" style="' + options.bgcolor + '">' + icon[1] + '</li>' : ''
                    , '<li class="layui-icon ' + TOP_BAR + '" lay-type="top" style="' + options.bgcolor + '">' + icon[2] + '</li>'
                    , '</ul>'].join(''))
                    , topBar = elem.find('.' + TOP_BAR)
                    , scroll = function () {
                    var stop = dom.scrollTop();
                    if (stop >= (options.showHeight)) {
                        is || (topBar.show(), is = 1);
                    } else {
                        is && (topBar.hide(), is = 0);
                    }
                };
                if ($('.' + ELEM)[0]) return;

                typeof options.css === 'object' && elem.css(options.css);
                body.append(elem), scroll();

                //bar点击事件
                elem.find('li').on('click', function () {
                    var othis = $(this), type = othis.attr('lay-type');
                    if (type === 'top') {
                        $('html,body').animate({
                            scrollTop: 0
                        }, 200);
                    }
                    options.click && options.click.call(this, type);
                });

                //Top显示控制
                dom.on('scroll', function () {
                    clearTimeout(timer);
                    timer = setTimeout(function () {
                        scroll();
                    }, 100);
                });
            }

            //倒计时
            , countdown: function (endTime, serverTime, callback) {
                var that = this
                    , type = typeof serverTime === 'function'
                    , end = new Date(endTime).getTime()
                    , now = new Date((!serverTime || type) ? new Date().getTime() : serverTime).getTime()
                    , count = end - now
                    , time = [
                    Math.floor(count / (1000 * 60 * 60 * 24)) //天
                    , Math.floor(count / (1000 * 60 * 60)) % 24 //时
                    , Math.floor(count / (1000 * 60)) % 60 //分
                    , Math.floor(count / 1000) % 60 //秒
                ];

                if (type) callback = serverTime;

                var timer = setTimeout(function () {
                    that.countdown(endTime, now + 1000, callback);
                }, 1000);

                callback && callback(count > 0 ? time : [0, 0, 0, 0], serverTime, timer);

                if (count <= 0) clearTimeout(timer);
                return timer;
            }

            //某个时间在当前时间的多久前
            , timeAgo: function (time, onlyDate) {
                var stamp = new Date().getTime() - new Date(time).getTime();

                //超过30天，返回具体日期
                if (stamp > 1000 * 60 * 60 * 24 * 30) {
                    stamp = new Date(time).toLocaleString();
                    onlyDate && (stamp = stamp.replace(/\s[\S]+$/g, ''));
                    return stamp;
                }

                //30天以内，返回“多久前”
                if (stamp >= 1000 * 60 * 60 * 24) {
                    return ((stamp / 1000 / 60 / 60 / 24) | 0) + '天前';
                } else if (stamp >= 1000 * 60 * 60) {
                    return ((stamp / 1000 / 60 / 60) | 0) + '小时前';
                } else if (stamp >= 1000 * 60 * 3) { //3分钟以内为：刚刚
                    return ((stamp / 1000 / 60) | 0) + '分钟前';
                } else if (stamp < 0) {
                    return '未来';
                } else {
                    return '刚刚';
                }
            }
            //js时间格式化
            , dateFormat: function (date, format) {
                var o = {
                    "M+": date.getMonth() + 1, //month
                    "d+": date.getDate(), //day
                    "h+": date.getHours(), //hour
                    "m+": date.getMinutes(), //minute
                    "s+": date.getSeconds(), //second
                    "q+": Math.floor((date.getMonth() + 3) / 3), //quarter
                    "S": date.getMilliseconds() //millisecond
                };
                if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                return format;
            }
            //判断对象是否为空
            , emptyEmpty: function (obj) {
                var flag = false;
                if (obj === null || obj === undefined || typeof (obj) === 'undefined' || obj === '') {
                    flag = true;
                } else if (typeof (obj) === 'string') {
                    obj = obj.trim();
                    if (obj === '') {//为空
                        flag = true;
                    } else {//不为空
                        obj = obj.toUpperCase();
                        if (obj === 'NULL' || obj === 'UNDEFINED' || obj === '{}') {
                            flag = true;
                        }
                    }
                } else {
                    flag = false;
                }
                return flag;
            }
            //转化字符串
            , toTxt: function (str) {
                var RexStr = /\<|\>|\"|\'|\&|\n| /g
                str = str.replace(RexStr, function (MatchStr) {
                    switch (MatchStr) {
                        case "<":
                            return "&lt;";
                            break;
                        case ">":
                            return "&gt;";
                            break;
                        case "\"":
                            return "&quot;";
                            break;
                        case "'":
                            return "&#39;";
                            break;
                        case "&":
                            return "&amp;";
                            break;
                        case "\n":
                            return "<br/>";
                            break;
                        case " ":
                            return "&nbsp;";
                            break;
                        default:
                            break;
                    }
                });
                return str;
            }
            /**
             * 根据两个日期，判断相差天数
             * @param sDate1 开始日期 如：2016-11-01
             * @param sDate2 结束日期 如：2016-11-02
             * @returns {number} 返回相差天数
             */
            , daysBetween: function (sDate1, sDate2) {
                var time1 = new Date(sDate1).getTime();
                var time2 = new Date(sDate2).getTime();
                var day = time2 - time1;
                if (day < 0 || isNaN(day)) return 0;
                return Math.abs(parseInt((time2 - time1) / 1000 / 3600 / 24) + 1);
            }
            /**
             * 天数差转化为年月日
             * @param sdate 开始的时间
             * @param edate 结束的时间
             * @returns {*}
             */
            , day2ymrStr2: function (sdate, edate) {
                var day2ymrStr = "";
                var date1 = new Date(edate);
                var date2 = new Date(sdate);
                var y = 0, m = 0, d = 0;
                var y1 = date1.getFullYear();
                var m1 = date1.getMonth();
                var d1 = date1.getDate();
                var y2 = date2.getFullYear();
                var m2 = date2.getMonth();
                var d2 = date2.getDate();
                if (d2 > d1) {
                    m1 = m1 - 1;
                    d1 = d1 + 30;
                }
                if (m2 > m1) {
                    y1 = y1 - 1;
                    m1 = m1 + 12;
                }
                d = (d1 - d2) + 1;
                m = m1 - m2;
                y = Math.abs(y1 - y2);
                if (y != 0) day2ymrStr += y + "年";
                if (m != 0) day2ymrStr += m + "个月";
                if (d != 0) day2ymrStr += d + "天";
                if (isNaN(d) || isNaN(m) || isNaN(y)) {
                    return 0 + "天";
                }
                return day2ymrStr;
            }
        };
    exports('util', util);
});