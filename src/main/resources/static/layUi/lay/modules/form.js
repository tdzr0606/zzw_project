/**

 @Name：layui.form 表单组件
 @Author：贤心
 @License：MIT

 */

layui.define('layer', function (exports) {
    "use strict";

    var $ = layui.$
        , layer = layui.layer
        , hint = layui.hint()
        , device = layui.device()

        , MOD_NAME = 'form', ELEM = '.layui-form', THIS = 'layui-this', SHOW = 'layui-show', HIDE = 'layui-hide',
        DISABLED = 'layui-disabled'

        , Form = function () {
            this.config = {
                verify: {
                    required: [
                        /[\S]+/
                        , '必填项不能为空'
                    ]
                    , phone: [/^1[34578]\d{9}$/
                        , '请输入正确的手机号'
                    ]
                    , email: [
                        /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
                        , '邮箱格式不正确'
                    ]
                    , url: [
                        /(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/
                        , '链接格式不正确'
                    ]
                    , number: [
                        /^\d+$/
                        , '只能填写数字'
                    ]
                    , date: [
                        /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/
                        , '日期格式不正确'
                    ]
                    , identity: [
                        /(^\d{15}$)|(^\d{17}(x|X|\d)$)/
                        , '请输入正确的身份证号'
                    ]
                    , username: function (value) {
                        if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                            return '用户名不能有特殊字符';
                        }
                        if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                            return '用户名首尾不能出现下划线\'_\'';
                        }
                    }
                    , pass: [
                        /^[\S]{6,12}$/
                        , '密码必须6到12位，且不能出现空格'
                    ]
                    , postCode: [
                        /^[1-9][0-9]{5}$/
                        , "请输入正确的邮政编码"
                    ]
                    , money: [
                        /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/
                        , '金额格式不正确'
                    ]
                    , checkCode: [
                        /^[A-Za-z0-9]{4}$/
                        , '你输入的验证码格式不正确'
                    ]
                    , fax: [
                        /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/
                        , '你输入的传真号码格式不正确'
                    ]
                    , tel: [
                        /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/
                        , '你输入的电话号码格式不正确'
                    ]
                    , idCard: function (idcard) {
                        var area = {
                            11: "北京",
                            12: "天津",
                            13: "河北",
                            14: "山西",
                            15: "内蒙古",
                            21: "辽宁",
                            22: "吉林",
                            23: "黑龙江",
                            31: "上海",
                            32: "江苏",
                            33: "浙江",
                            34: "安徽",
                            35: "福建",
                            36: "江西",
                            37: "山东",
                            41: "河南",
                            42: "湖北",
                            43: "湖南",
                            44: "广东",
                            45: "广西",
                            46: "海南",
                            50: "重庆",
                            51: "四川",
                            52: "贵州",
                            53: "云南",
                            54: "西藏",
                            61: "陕西",
                            62: "甘肃",
                            63: "青海",
                            64: "宁夏",
                            65: "新疆",
                            71: "台湾",
                            81: "香港",
                            82: "澳门",
                            91: "国外"
                        };
                        var Y, JYM;
                        var S, M;
                        var ereg;
                        var idcard_array = [];
                        idcard = idcard.replace(/\s/g, "").toUpperCase();//去空格，转大写
                        for (var i = 0; i < idcard.length; i++) {//字符串截成数组
                            idcard_array[i] = idcard.substring(i, i + 1);
                        }
                        //地区检验
                        if (area[parseInt(idcard.substr(0, 2))] === null) return "身份证地区非法！";
                        //身份号码位数及格式检验
                        switch (idcard.length) {
                            //15位身份号码检测
                            case 15:
                                if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
                                    ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
                                } else {
                                    ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
                                }
                                if (!ereg.test(idcard)) return "身份证号码出生日期超出范围或含有非法字符！t15";
                                break;
                            //18位身份号码检测
                            case 18:
                                if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
                                    ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
                                } else {
                                    ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
                                }
                                if (ereg.test(idcard)) {//测试出生日期的合法性
                                    //计算校验位
                                    S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                                        + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                                        + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                                        + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                                        + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                                        + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                                        + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                                        + parseInt(idcard_array[7])
                                        + parseInt(idcard_array[8]) * 6
                                        + parseInt(idcard_array[9]) * 3;
                                    Y = S % 11;
                                    M = "F";
                                    JYM = "10X98765432";
                                    M = JYM.substr(Y, 1);//判断校验位
                                    if (M !== idcard_array[17]) return "身份证号码校验错误！";//检测ID的校验位
                                }
                                else return "身份证号码出生日期超出范围或含有非法字符！t18";
                                break;
                            default:
                                return "身份证号码位数不对！";
                                break;
                        }
                    }
                    , greenCard: function (idcard) {
                        idcard = idcard.replace(/\s/g, "").toUpperCase();//去空格，转大写
                        //绿卡15位验证
                        if (idcard.length !== 15) return "外国人永久居留证位数不正确！";
                        //国籍代码3位大写
                        if (!/^[A-Z]+$/.test(idcard.substr(0, 3))) return "外国人永久居留证国籍代码不正确！";
                        //受理机关代码4位数字
                        if (!/^\d{4}$/.test(idcard.substr(3, 4))) return "外国人永久居留证受理机关代码不正确！";
                        //出生年月验证
                        var ereg = /^[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]){4})$/;
                        if (!ereg.test(idcard.substr(7, 6))) return "身份证号码出生日期超出范围或含有非法字符！(绿卡)";
                    }
                    , length100: function (value) {
                        if (value.length > 100) {
                            return '您输入的字数超过限制字数100！';
                        }
                    }
                    , length150: function (value) {
                        if (value.length > 150) {
                            return '您输入的字数超过限制字数150！';
                        }
                    }
                    , length200: function (value) {
                        if (value.length > 200) {
                            return '您输入的字数超过限制字数200！';
                        }
                    }
                    , length4000: function (value) {
                        if (value.length > 4000) {
                            return '您输入的字数超过限制字数4000！';
                        }
                    }
                    , length10000: function (value) {
                        if (value.length > 10000) {
                            return '您输入的字数超过限制字数10000！';
                        }
                    }
                }
            };
        };

    //全局设置
    Form.prototype.set = function (options) {
        var that = this;
        $.extend(true, that.config, options);
        return that;
    };

    //验证规则设定
    Form.prototype.verify = function (settings) {
        var that = this;
        $.extend(true, that.config.verify, settings);
        return that;
    };

    //表单事件监听
    Form.prototype.on = function (events, callback) {
        return layui.onevent.call(this, MOD_NAME, events, callback);
    };
    //表单数据读入 luo
    Form.prototype.loadData = function (data, filter) {
        $.each(data, function (key, value) {
            var inputType = $('#' + filter + ' [name="' + key + '"]').attr('type');
            if ("checkbox" == inputType) {
                $('#' + filter + ' [name="' + key + '"]').prop("checked", value);
            }
            else {
                $('#' + filter + ' [name="' + key + '"]').val(value);
            }
        });
    };
    //表单控件渲染
    Form.prototype.render = function (type, filter) {
        var that = this
            , elemForm = $(ELEM + function () {
            return filter ? ('[lay-filter="' + filter + '"]') : '';
        }())
            , items = {

            //下拉选择框
            select: function () {
                var TIPS = '请选择', CLASS = 'layui-form-select', TITLE = 'layui-select-title'
                    , NONE = 'layui-select-none', initValue = '', thatInput

                    , selects = elemForm.find('select'), hide = function (e, clear) {
                    if (!$(e.target).parent().hasClass(TITLE) || clear) {
                        $('.' + CLASS).removeClass(CLASS + 'ed ' + CLASS + 'up');
                        thatInput && initValue && thatInput.val(initValue);
                    }
                    thatInput = null;
                }

                    , events = function (reElem, disabled, isSearch) {
                    var select = $(this)
                        , title = reElem.find('.' + TITLE)
                        , input = title.find('input')
                        , dl = reElem.find('dl')
                        , dds = dl.children('dd')


                    if (disabled) return;

                    //展开下拉
                    var showDown = function () {
                        var top = reElem.offset().top + reElem.outerHeight() + 5 - win.scrollTop()
                            , dlHeight = dl.outerHeight();
                        reElem.addClass(CLASS + 'ed');
                        dds.removeClass(HIDE);

                        //上下定位识别
                        if (top + dlHeight > win.height() && top >= dlHeight) {
                            reElem.addClass(CLASS + 'up');
                        }
                    }, hideDown = function (choose) {
                        reElem.removeClass(CLASS + 'ed ' + CLASS + 'up');
                        input.blur();

                        if (choose) return;

                        notOption(input.val(), function (none) {
                            if (none) {
                                initValue = dl.find('.' + THIS).html();
                                input && input.val(initValue);
                            }
                        });
                    };

                    //点击标题区域
                    title.on('click', function (e) {
                        reElem.hasClass(CLASS + 'ed') ? (
                            hideDown()
                        ) : (
                            hide(e, true),
                                showDown()
                        );
                        dl.find('.' + NONE).remove();
                    });

                    //点击箭头获取焦点
                    title.find('.layui-edge').on('click', function () {
                        input.focus();
                    });

                    //键盘事件
                    input.on('keyup', function (e) {
                        var keyCode = e.keyCode;
                        //Tab键
                        if (keyCode === 9) {
                            showDown();
                        }
                    }).on('keydown', function (e) {
                        var keyCode = e.keyCode;
                        //Tab键
                        if (keyCode === 9) {
                            hideDown();
                        } else if (keyCode === 13) { //回车键
                            e.preventDefault();
                        }
                    });

                    //检测值是否不属于select项
                    var notOption = function (value, callback, origin) {
                        var num = 0;
                        layui.each(dds, function () {
                            var othis = $(this)
                                , text = othis.text()
                                , not = text.indexOf(value) === -1;
                            if (value === '' || (origin === 'blur') ? value !== text : not) num++;
                            origin === 'keyup' && othis[not ? 'addClass' : 'removeClass'](HIDE);
                        });
                        var none = num === dds.length;
                        return callback(none), none;
                    };

                    //搜索匹配
                    var search = function (e) {
                        var value = this.value, keyCode = e.keyCode;

                        if (keyCode === 9 || keyCode === 13
                            || keyCode === 37 || keyCode === 38
                            || keyCode === 39 || keyCode === 40
                        ) {
                            return false;
                        }

                        notOption(value, function (none) {
                            if (none) {
                                dl.find('.' + NONE)[0] || dl.append('<p class="' + NONE + '">无匹配项</p>');
                            } else {
                                dl.find('.' + NONE).remove();
                            }
                        }, 'keyup');

                        if (value === '') {
                            dl.find('.' + NONE).remove();
                        }
                    };
                    if (isSearch) {
                        input.on('keyup', search).on('blur', function (e) {
                            thatInput = input;
                            initValue = dl.find('.' + THIS).html();
                            setTimeout(function () {
                                notOption(input.val(), function (none) {
                                    if (none && !initValue) {
                                        input.val('');
                                    }
                                }, 'blur');
                            }, 200);
                        });
                    }

                    //选择
                    dds.on('click', function () {
                        var othis = $(this), value = othis.attr('lay-value');
                        var filter = select.attr('lay-filter'); //获取过滤器

                        if (othis.hasClass(DISABLED)) return false;

                        if (othis.hasClass('layui-select-tips')) {
                            input.val('');
                        } else {
                            input.val(othis.text());
                            othis.addClass(THIS);
                        }

                        othis.siblings().removeClass(THIS);
                        select.val(value).removeClass('layui-form-danger')
                        layui.event.call(this, MOD_NAME, 'select(' + filter + ')', {
                            elem: select[0]
                            , value: value
                            , othis: reElem
                        });

                        hideDown(true);
                        return false;
                    });

                    reElem.find('dl>dt').on('click', function (e) {
                        return false;
                    });

                    //关闭下拉
                    $(document).off('click', hide).on('click', hide);
                }

                selects.each(function (index, select) {
                    var othis = $(this)
                        , hasRender = othis.next('.' + CLASS)
                        , disabled = this.disabled
                        , value = select.value
                        , selected = $(select.options[select.selectedIndex]) //获取当前选中项
                        , optionsFirst = select.options[0];

                    if (typeof othis.attr('lay-ignore') === 'string') return othis.show();

                    var isSearch = typeof othis.attr('lay-search') === 'string'
                        , placeholder = optionsFirst ? (
                        optionsFirst.value ? TIPS : (optionsFirst.innerHTML || TIPS)
                    ) : TIPS;

                    //替代元素
                    var reElem = $(['<div class="layui-unselect ' + CLASS + (disabled ? ' layui-select-disabled' : '') + '">'
                        , '<div class="' + TITLE + '"><input type="text" placeholder="' + placeholder + '" value="' + (value ? selected.html() : '') + '" ' + (isSearch ? '' : 'readonly') + ' class="layui-input layui-unselect' + (disabled ? (' ' + DISABLED) : '') + '">'
                        , '<i class="layui-edge"></i></div>'
                        , '<dl class="layui-anim layui-anim-upbit' + (othis.find('optgroup')[0] ? ' layui-select-group' : '') + '">' + function (options) {
                            var arr = [];
                            layui.each(options, function (index, item) {
                                if (index === 0 && !item.value) {
                                    arr.push('<dd lay-value="" class="layui-select-tips">' + (item.innerHTML || TIPS) + '</dd>');
                                } else if (item.tagName.toLowerCase() === 'optgroup') {
                                    arr.push('<dt>' + item.label + '</dt>');
                                } else {
                                    arr.push('<dd lay-value="' + item.value + '" class="' + (value === item.value ? THIS : '') + (item.disabled ? (' ' + DISABLED) : '') + '">' + item.innerHTML + '</dd>');
                                }
                            });
                            arr.length === 0 && arr.push('<dd lay-value="" class="' + DISABLED + '">没有选项</dd>');
                            return arr.join('');
                        }(othis.find('*')) + '</dl>'
                        , '</div>'].join(''));

                    hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
                    othis.after(reElem);
                    events.call(this, reElem, disabled, isSearch);
                });
            }
            //复选框/开关
            , checkbox: function () {
                var CLASS = {
                    checkbox: ['layui-form-checkbox', 'layui-form-checked', 'checkbox']
                    , _switch: ['layui-form-switch', 'layui-form-onswitch', 'switch']
                }
                    , checks = elemForm.find('input[type=checkbox]')

                    , events = function (reElem, RE_CLASS) {
                    var check = $(this);

                    //勾选
                    reElem.on('click', function () {
                        var filter = check.attr('lay-filter') //获取过滤器
                            , text = (check.attr('lay-text') || '').split('|');

                        if (check[0].disabled) return;

                        check[0].checked ? (
                            check[0].checked = false
                                , reElem.removeClass(RE_CLASS[1]).find('em').text(text[1])
                        ) : (
                            check[0].checked = true
                                , reElem.addClass(RE_CLASS[1]).find('em').text(text[0])
                        );

                        layui.event.call(check[0], MOD_NAME, RE_CLASS[2] + '(' + filter + ')', {
                            elem: check[0]
                            , value: check[0].value
                            , othis: reElem
                        });
                    });
                }

                checks.each(function (index, check) {
                    var othis = $(this), skin = othis.attr('lay-skin')
                        , text = (othis.attr('lay-text') || '').split('|'), disabled = this.disabled;
                    if (skin === 'switch') skin = '_' + skin;
                    var RE_CLASS = CLASS[skin] || CLASS.checkbox;

                    if (typeof othis.attr('lay-ignore') === 'string') return othis.show();

                    //替代元素
                    var hasRender = othis.next('.' + RE_CLASS[0]);
                    var reElem = $(['<div class="layui-unselect ' + RE_CLASS[0] + (
                        check.checked ? (' ' + RE_CLASS[1]) : '') + (disabled ? ' layui-checkbox-disbaled ' + DISABLED : '') + '" lay-skin="' + (skin || '') + '">'
                        , {
                            _switch: '<em>' + ((check.checked ? text[0] : text[1]) || '') + '</em><i></i>'
                        }[skin] || ((check.title.replace(/\s/g, '') ? ('<span>' + check.title + '</span>') : '') + '<i class="layui-icon">' + (skin ? '&#xe605;' : '&#xe618;') + '</i>')
                        , '</div>'].join(''));

                    hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
                    othis.after(reElem);
                    events.call(this, reElem, RE_CLASS);
                });
            }
            //单选框
            , radio: function () {
                var CLASS = 'layui-form-radio', ICON = ['&#xe643;', '&#xe63f;']
                    , radios = elemForm.find('input[type=radio]')

                    , events = function (reElem) {
                    var radio = $(this), ANIM = 'layui-anim-scaleSpring';

                    reElem.on('click', function () {
                        var name = radio[0].name, forms = radio.parents(ELEM);
                        var filter = radio.attr('lay-filter'); //获取过滤器
                        var sameRadio = forms.find('input[name=' + name.replace(/(\.|#|\[|\])/g, '\\$1') + ']'); //找到相同name的兄弟

                        if (radio[0].disabled) return;

                        layui.each(sameRadio, function () {
                            var next = $(this).next('.' + CLASS);
                            this.checked = false;
                            next.removeClass(CLASS + 'ed');
                            next.find('.layui-icon').removeClass(ANIM).html(ICON[1]);
                        });

                        radio[0].checked = true;
                        reElem.addClass(CLASS + 'ed');
                        reElem.find('.layui-icon').addClass(ANIM).html(ICON[0]);

                        layui.event.call(radio[0], MOD_NAME, 'radio(' + filter + ')', {
                            elem: radio[0]
                            , value: radio[0].value
                            , othis: reElem
                        });
                    });
                };

                radios.each(function (index, radio) {
                    var othis = $(this), hasRender = othis.next('.' + CLASS), disabled = this.disabled;

                    if (typeof othis.attr('lay-ignore') === 'string') return othis.show();

                    //替代元素
                    var reElem = $(['<div class="layui-unselect ' + CLASS + (radio.checked ? (' ' + CLASS + 'ed') : '') + (disabled ? ' layui-radio-disbaled ' + DISABLED : '') + '">'
                        , '<i class="layui-anim layui-icon">' + ICON[radio.checked ? 0 : 1] + '</i>'
                        , '<span>' + (radio.title || '未命名') + '</span>'
                        , '</div>'].join(''));

                    hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
                    othis.after(reElem);
                    events.call(this, reElem);
                });
            }
        };
        type ? (
            items[type] ? items[type]() : hint.error('不支持的' + type + '表单渲染')
        ) : layui.each(items, function (index, item) {
            item();
        });
        return that;
    };

    //表单提交校验
    var submit = function () {
        var button = $(this), verify = form.config.verify, stop = null
            , DANGER = 'layui-form-danger', field = {}, elem = button.parents(ELEM)

            , verifyElem = elem.find('*[lay-verify]') //获取需要校验的元素
            , formElem = button.parents('form')[0] //获取当前所在的form元素，如果存在的话
            , fieldElem = elem.find('input,select,textarea') //获取所有表单域
            , filter = button.attr('lay-filter'); //获取过滤器

        //开始校验
        layui.each(verifyElem, function (_, item) {
            var othis = $(this), ver = othis.attr('lay-verify').split('|');
            var tips = '', value = othis.val();
            othis.removeClass(DANGER);
            layui.each(ver, function (_, thisVer) {
                var isFn = typeof verify[thisVer] === 'function';
                if (verify[thisVer] && (isFn ? tips = verify[thisVer](value, item) : !verify[thisVer][0].test(value))) {
                    layer.msg(tips || verify[thisVer][1], {
                        icon: 5
                        , shift: 6
                    });
                    //非移动设备自动定位焦点
                    if (!device.android && !device.ios) {
                        item.focus();
                    }
                    othis.addClass(DANGER);
                    return stop = true;
                }
            });
            if (stop) return stop;
        });

        if (stop) return false;

        layui.each(fieldElem, function (_, item) {
            if (!item.name) return;
            if (/^checkbox|radio$/.test(item.type) && !item.checked) return;
            field[item.name] = item.value;
        });

        //获取字段
        return layui.event.call(this, MOD_NAME, 'submit(' + filter + ')', {
            elem: this
            , form: formElem
            , field: field
        });
    };

    //自动完成渲染
    var form = new Form()
        , dom = $(document), win = $(window);

    form.render();

    //表单reset重置渲染
    dom.on('reset', ELEM, function () {
        var filter = $(this).attr('lay-filter');
        setTimeout(function () {
            form.render(null, filter);
        }, 50);
    });

    //表单提交事件
    dom.on('submit', ELEM, submit)
        .on('click', '*[lay-submit]', submit);

    exports(MOD_NAME, form);
});

 
