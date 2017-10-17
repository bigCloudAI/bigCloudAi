var app = new Vue({
  el: '#app',
  data: {
	ltrshow:false,
	ltrhide:false,
	rtlshow:false,
	rtlhide:false,
    message: 'Hello Vue!'
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
	    }
  }
})