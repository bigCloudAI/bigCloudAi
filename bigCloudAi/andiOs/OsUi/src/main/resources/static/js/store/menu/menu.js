var menu = {
  state: {
	  count:100,
	  menus:[
		  {
			  name:'我的云系统',code:'os',
			  icon:'fa fa-mixcloud',
			  con:'os/index.html'
		  },
		  {
			  name:'我的应用',code:'app',
			  icon:'fa fa-modx',
			  con:'app/index.html'
		  },
		  {
			  name:'应用市场',code:'appstore',
			  icon:'fa fa-hospital-o',
		  },
		  {
			  name:'开发者平台',code:'dev',
			  icon:'fa fa-github',
		  },
		  {
			  name:'平台组件自配置',code:'sysconfig',
			  icon:'fa fa-pied-piper',
		  }
	  ]
	  
  },
  mutations: {  },
  actions: { },
  getters: {
	  menusindex:function(state){
		  for(var i=0;i<state.menus.length;i++){
			  state.menus[i].left=Math.floor(i/7)*100+20;
			  state.menus[i].top=(i%7)*100+20;
		  }
		  return state.menus;
	  }
	  
  }
}