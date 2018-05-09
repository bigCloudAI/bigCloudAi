var menu = {
  state: {
	  count:100,
	  menus:[
		  {
			  name:'我的云系统',code:'os',
			  icon:'/img/jquerydesktop/icons/icon_32_computer.png',
			  con:'os/index.html'
		  },
		  {
			  name:'我的应用',code:'app',
			  icon:'/img/jquerydesktop/icons/icon_32_computer.png',
			  con:'app/index.html'
		  },
		  {
			  name:'应用市场',code:'appstore',
			  icon:'/img/jquerydesktop/icons/icon_32_computer.png',
		  },
		  {
			  name:'开发者平台',code:'dev',
			  icon:'/img/jquerydesktop/icons/icon_32_computer.png',
		  },
		  {
			  name:'平台组件自配置',code:'sysconfig',
			  icon:'/img/jquerydesktop/icons/icon_32_computer.png',
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