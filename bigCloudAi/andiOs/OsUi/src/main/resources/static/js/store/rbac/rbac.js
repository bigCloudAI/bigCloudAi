var rbac = {
  state: {
	  count:100,
	  menu:"user",
	  user:{
		  name:'',desc:'',
		  pwd:'',
		  isLogin:false,
		  logining:false
	  },
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
		  },
		  {
			  name:'权限管理',code:'rbac',
			  icon:'fa fa-drivers-license-o',
		  }
	  ]
	  
  },
  mutations: { 
	  isLoginCheck:function(state){
		  $("#userSys").show();
		  $("#roleSys").hide();
		  $("#accessSys").hide();
		  $.get("/osUi/isLogin",
				  function(data,status){
					  if(data.success){
						  state.user.isLogin=true;
						  state.user.name=data.name;
					  }else{
						  state.user.isLogin=false;
					  }
				  });
		 
	  },
	  login:function(state){
		  if(''==state.user.name){ state.user.desc="用户不可为空";	
			  return;
		  }
		  if(''==state.user.pwd){state.user.desc="密码不可为空"; 
			  return;
		  }
		  state.user.logining=true;
		  $.post("/osUi/loginPost",state.user,function(data,status){
			  if(data.success){
				  state.user.isLogin=true;
			  }else{
				  state.user.desc="用户密码错误";
			  }
			  state.user.logining=false;
		  });
	  },
	  logout:function(state){
		  state.user.name="";
		  state.user.pwd="";
		  
		  $.get("/osUi/logoutGet",state.user,function(data,status){
			  if(data.success){
				  state.user.isLogin=false;
			  }else{
				  state.user.isLogin=true;
			  }
		  });
	  },
	  descfocus:function(state){
		  state.user.desc="";
	  },
	  rbacuser:function(state){
		  var value='user';
		  chooseone(value);
		  state.menu='user';
	  },
	  rbacrole:function(state){
		  var value='role';
		  chooseone(value); state.menu='role';
	  },
	  rbacaccess:function(state){
		  var value='access';
		  chooseone(value); state.menu='access';
	  },
	  rbacaccesslog:function(state){
		  var value='accesslog';
		  chooseone(value); state.menu='accesslog';
	  }
  },
  actions: { 
  },
  getters: {
	  userdesc:function(state){
		  return state.user.desc;
	  },
	  menusindex:function(state){
		  for(var i=0;i<state.menus.length;i++){
			  state.menus[i].left=Math.floor(i/7)*100+20;
			  state.menus[i].top=(i%7)*100+20;
		  }
		  return state.menus;
	  }
	  
  }
}

function chooseone(value){
	  var menu=['user','role','access','accesslog'];
	  for (var i = 0; i < menu.length; i++) {
		if(menu[i]==value){
			$("#"+menu[i]+"Sys").show();
		}else{
			$("#"+menu[i]+"Sys").hide();
		}
	}
	  
}