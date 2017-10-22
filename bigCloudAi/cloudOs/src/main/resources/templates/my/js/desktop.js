var app = new Vue({
  el: '#app',
  data: {
	ltrshow:false,
	ltrhide:false,
	rtlshow:false,
	rtlhide:false,
    message: 'Hello Vue!',
    wins:[]
  },
  methods: {
	    navswitch: function (event) {
	    	this.ltrshow=!this.ltrshow;
	    	this.ltrhide=!this.ltrshow;
	    },
	    settingswitch:function(c){
	    	if(c){
	    		if(this.rtlshow){
	    			this.rtlshow=false;
	    			this.rtlhide=!this.rtlshow;
	    		}
	    		return;
	    	}
	    	this.rtlshow=!this.rtlshow;
	    	this.rtlhide=!this.rtlshow;
	    },
	    openWin:function(){
	    	this.wins.push({name:this.wins.length});
	    },
	    closeWin:function(index){
	    	this.wins.splice(index, 1);
	    }
  }
})


//定义移动对象和移动坐标
var Mouse_Obj="none",_x,_y;
//拖动对象函数(自动)
document.onmousemove=function()
{
 if(Mouse_Obj!=="none")
 {
(Mouse_Obj).style.left=_x+event.x;
(Mouse_Obj).style.top=_y+event.y;
 event.returnValue=false;
 }
}
//停止拖动函数(自动)
document.onmouseup=function()
{
 Mouse_Obj="none";
}
//确定被拖动对象函数 o为被拖动对象
function m(o)
{
 Mouse_Obj=o;
 _x=parseInt((Mouse_Obj).style.left)-event.x;
 _y=parseInt((Mouse_Obj).style.top)-event.y;
}

