Vue.directive('drag',//自定义指令                                      JS
        {bind:function (el, binding) {
                let oDiv = el;   //当前元素
                let self = this;  //上下文
                   oDiv.childNodes[0].onmousedown = function (e) {
                 //鼠标按下，计算当前元素距离可视区的距离
                    let disX = e.clientX - oDiv.offsetLeft;
                    let disY = e.clientY -  oDiv.offsetTop;
                    document.onmousemove = function (e) {
                      //通过事件委托，计算移动的距离 
                        let l = e.clientX - disX;
                        let t = e.clientY - disY;
                      //移动当前元素  
                        oDiv.style.left = l + 'px';
                        oDiv.style.top = t + 'px';
                         //将此时的位置传出去
                       // binding.value({x:e.pageX,y:e.pageY})
                    };
                    document.onmouseup = function (e) {
                        document.onmousemove = null;
                        document.onmouseup = null;
                     };
                };
            }
        }
    );
    
 function resize(oParent, handle, isLeft, isTop, lockX, lockY)
{
	handle.onmousedown = function (event)
	{
		var event = event || window.event;
		var disX = event.clientX - handle.offsetLeft;
		var disY = event.clientY - handle.offsetTop;	
		var iParentTop = oParent.offsetTop;
		var iParentLeft = oParent.offsetLeft;
		var iParentWidth = oParent.offsetWidth;
		var iParentHeight = oParent.offsetHeight;
		
		document.onmousemove = function (event)
		{
			var event = event || window.event;
			
			var iL = event.clientX - disX;
			var iT = event.clientY - disY;
			var maxW = document.documentElement.clientWidth - oParent.offsetLeft - 2;
			var maxH = document.documentElement.clientHeight - oParent.offsetTop - 2;			
			var iW = isLeft ? iParentWidth - iL : handle.offsetWidth + iL;
			var iH = isTop ? iParentHeight - iT : handle.offsetHeight + iT;
			
			isLeft && (oParent.style.left = iParentLeft + iL + "px");
			isTop && (oParent.style.top = iParentTop + iT + "px");
			
			iW < dragMinWidth && (iW = dragMinWidth);
			iW > maxW && (iW = maxW);
			lockX || (oParent.style.width = iW + "px");
			
			iH < dragMinHeight && (iH = dragMinHeight);
			iH > maxH && (iH = maxH);
			lockY || (oParent.style.height = iH + "px");
			
			if((isLeft && iW == dragMinWidth) || (isTop && iH == dragMinHeight)) document.onmousemove = null;
			
			return false;	
		};
		document.onmouseup = function ()
		{
			document.onmousemove = null;
			document.onmouseup = null;
		};
		return false;
	}
};
var app = new Vue({
	el: '#app',
	data: {
		wincount: 0,
		ltrshow: false,
		ltrhide: false,
		rtlshow: false,
		rtlhide: false,
		message: '开始',
		wins: []
	},
	methods: {
		navswitch: function(event) {
			this.ltrshow = !this.ltrshow;
			this.ltrhide = !this.ltrshow;
		},
		settingswitch: function(c) {
			if(c) {
				if(this.rtlshow) {
					this.rtlshow = false;
					this.rtlhide = !this.rtlshow;
				}
				return;
			}
			this.rtlshow = !this.rtlshow;
			this.rtlhide = !this.rtlshow;
		},
		openWin: function(name, src) {
			var openwin = {
				name: name,
				id: this.wincount++,
				hide: false,
				close: false,
				left: 6 * this.wincount + 200,
				top: 12 * this.wincount + 100,
				width: 940,
				height: 403,
				recleft: 6 * this.wincount + 200,
				rectop: 12 * this.wincount + 100,
				recwidth: 940,
				recheight: 403,
				max: false,
				activ: true,
				backactivwin: null,
				src: src
			};
			this.wins.forEach(function(win) {
				if(win.activ) {
					win.activ = false;
					openwin.backactivwin = win;
				}
			})
			this.wins.push(openwin);
		},
		closeWin: function(win, event) {
			win.close = true;
			if(win.activ) {
				win.activ = false;
				if(win.backactivwin != null && !win.backactivwin.hide && !win.backactivwin.close) {
					win.backactivwin.activ = true;
				}
			}
			event.cancelBubble = true;
		},
		maxWin: function(win, event) {
			win.left = 0;
			win.top = 0;
			win.width = document.documentElement.clientWidth - 2;
			win.height = document.documentElement.clientHeight - 2;
			event.cancelBubble = true;
		},
		maxOrRecWin: function(win, event) {
			if(win.max) {
				win.max = false;
				win.left = win.recleft;
				win.top = win.rectop;
				win.width = win.recwidth;
				win.height = win.recheight;
			} else {
				win.max = true;
				win.recleft = win.left;
				win.rectop = win.top;
				win.recwidth = win.width;
				win.recheight = win.height;
				win.left = -1;
				win.top = 0;
				win.width = document.documentElement.clientWidth - 2;
				win.height = document.documentElement.clientHeight - 2;
			}
			event.cancelBubble = true;
		},
		hideWin: function(win, event) {
			win.hide = true;
			event.cancelBubble = true;
		},
		showWin: function(win) {
			win.hide = false;
		},
		showOrHideWin: function(win) {
			if(win.hide) {
				win.hide = false;
			} else {
				win.hide = true;
			}
		},
		activWin: function(activWin) {
			if(activWin.activ) {
				return;
			}
			this.wins.forEach(function(win) {
				if(win.activ) {
					win.activ = false;
					activWin.backactivwin = win;
				}
			});
			activWin.activ = true;
		}
	}
})