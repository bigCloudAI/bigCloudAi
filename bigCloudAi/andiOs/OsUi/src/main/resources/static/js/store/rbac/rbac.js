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
	  ],
	  tempUser:{
		  name:'',
		  pwd:'000000',
		  email:'',
		  desc:'',
		  adding:false
	  },
	  configuserSys:false,
	  userRoleUsers:[],
	  addRoles:[],
	  removeRoles:[],
	  users:{
		  content:[],
		  first:true,
		  last:false,
		  number:0,
		  numberOfElements:0,
		  size:8,
		  totalElements:0,
		  totalPages:0,
		  likekey:''
	  },
	  tempRole:{
		  name:'',
		  status:0,
		  adding:false
	  },
	  roles:{
		  content:[],
		  first:true,
		  last:false,
		  number:0,
		  numberOfElements:0,
		  size:8,
		  totalElements:0,
		  totalPages:0,
		  likekey:''
	  },
	  tempAccess:{
		  title:'',
		  urls:'',
		  status:0,
		  adding:false
	  },
	  accesss:{
		  content:[],
		  first:true,
		  last:false,
		  number:0,
		  numberOfElements:0,
		  size:8,
		  totalElements:0,
		  totalPages:0,
		  likekey:''
	  }
 
  
  },
  mutations: { 
	  isLoginCheck:function(state){
		  $("#userSys").show();
//		  $("#configuserSys").hide();
		  $("#adduserSys").hide();
		  $("#roleSys").hide();
		  $("#accessSys").hide();
		  $("#accesslogSys").hide();
		  $.get("/osUi/isLogin",
				  function(data,status){
					  if(data.success){
						  state.user.isLogin=true;
						  state.user.name=data.name;
					  }else{
						  state.user.isLogin=false;
					  }
				  });
		  vm.$store.commit('listUser');
		 
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
		  state.tempUser.desc="";
		  state.tempRole.desc="";
		  state.tempAccess.desc="";
	  },
	  rbacuser:function(state){
		  var value='user';
		  chooseone(value);state.menu='user';
		  vm.$store.commit('listUser');
	  },
	  listUser:function(state){
		  $.get("/rbac/user/page/"+state.users.number+"/"+state.users.size+"",function(data,status){
			  state.users.content=data.content;
			  state.users.first=data.first;
			  state.users.last=data.last;
			  state.users.number=data.number;
			  state.users.numberOfElements=data.numberOfElements;
			  state.users.size=data.size;
			  state.users.totalElements=data.totalElements;
			  state.users.totalPages=data.totalPages;
		  });
	  },
	  listUserlike:function(state){
		  $.post("/rbac/user/page/"+state.users.number+"/"+state.users.size,state.users.likekey,function(data,status){
			  state.users.content=data.content;
			  state.users.first=data.first;
			  state.users.last=data.last;
			  state.users.number=data.number;
			  state.users.numberOfElements=data.numberOfElements;
			  state.users.size=data.size;
			  state.users.totalElements=data.totalElements;
			  state.users.totalPages=data.totalPages;
		  });
	  },
	  toNumberlistUser:function(state,value){
		  state.users.number=value-1;
		  vm.$store.commit('listUser');
	  },
	  addUser:function(state){
		  if(''==state.tempUser.name){ state.tempUser.desc="用户不可为空";	
		  		return;
		  }
		  if(''==state.tempUser.email){state.tempUser.desc="邮箱不可为空"; 
		  		return;
		  }
		  state.tempUser.adding=true;
		  $.post("/rbac/user/add",state.tempUser,function(data,status){
			  $("#adduserSys").hide();
			  $("#listuserSys").show();
			  vm.$store.commit('listUser');
			  state.tempUser.adding=false;
		  });
		  state.tempUser.adding=false;
	  },
	  configUser:function(state,user){
		 // $("#configuserSys").toggle();
		  state.configuserSys=!state.configuserSys;
		  state.addRoles=[];
		  state.removeRoles=[];
		  //加载该用户的角色
		  if(user!=null){
			  state.tempUser=user;
			  $.get("/rbac/user/"+user.id+"/roles",function(data,status){
				  state.userRoleUsers=data;
				  console.log(data);
			  });
		  }else{
			  state.tempUser={};
		  }
	  },
	  addOrRemoveRole:function(state,userRoleUser){
		  console.log(!userRoleUser.hasUserRole);
		  if(!userRoleUser.hasUserRole){
			  state.addRoles.push(userRoleUser.role.id);
			  state.removeRoles.remove(userRoleUser.role.id);
		  }else{
			  state.removeRoles.push(userRoleUser.role.id);
			  state.addRoles.remove(userRoleUser.role.id);
		  }
		  console.log(state.removeRoles);
		  console.log(state.addRoles);
	  },
	  configUserRole:function(state){
		  if(state.addRoles.length==0&&state.removeRoles.length==0){
			  alert("没有改动");
			  return;
		  }
		  if(state.addRoles.length>0){
			  $.post("/rbac/user/"+ state.tempUser.id+"/roles/add",{"addRoles":state.addRoles},function(data,status){
				  console.log(data);
			  });
		  }
		  if(state.removeRoles.length>0){
			  $.post("/rbac/user/"+ state.tempUser.id+"/roles/remove",{"removeRoles":state.removeRoles},function(data,status){
				  console.log(data);
			  });
		  }
	  },
	  delUser:function(state,value){
		  $.get("/rbac/user/del/"+value,function(data,status){
			  vm.$store.commit('listUser');
		  });
	  },
	  rbacrole:function(state){
		  var value='role';
		  chooseone(value);
		  $("#addroleSys").hide();
		  state.menu='role';
		  vm.$store.commit('listRole');
	  },
	  listRole:function(state){
		  $.get("/rbac/role/page/"+state.roles.number+"/"+state.roles.size+"",function(data,status){
			  state.roles.content=data.content;
			  state.roles.first=data.first;
			  state.roles.last=data.last;
			  state.roles.number=data.number;
			  state.roles.numberOfElements=data.numberOfElements;
			  state.roles.size=data.size;
			  state.roles.totalElements=data.totalElements;
			  state.roles.totalPages=data.totalPages;
		  });
	  },
	  listRolelike:function(state){
		  $.post("/rbac/role/page/"+state.roles.number+"/"+state.roles.size,JSON.stringify({likekey:state.roles.likekey}),function(data,status){
			  state.roles.content=data.content;
			  state.roles.first=data.first;
			  state.roles.last=data.last;
			  state.roles.number=data.number;
			  state.roles.numberOfElements=data.numberOfElements;
			  state.roles.size=data.size;
			  state.roles.totalElements=data.totalElements;
			  state.roles.totalPages=data.totalPages;
		  });
	  },
	  toNumberlistRole:function(state,value){
		  state.roles.number=value-1;
		  vm.$store.commit('listRole');
	  },
	  addRole:function(state){
		  state.tempRole.adding=true;
		  $.post("/rbac/role/add",state.tempRole,function(data,status){
			  $("#addroleSys").hide();
			  $("#listroleSys").show();
			  vm.$store.commit('listRole');
			  state.tempRole.adding=false;
		  });
		  state.tempRole.adding=false;
	  },
	  delRole:function(state,value){
		  $.get("/rbac/role/del/"+value,function(data,status){
			  vm.$store.commit('listRole');
		  });
	  },
	
	  rbacaccess:function(state){
		  var value='access';
		  $("#addaccessSys").hide();
		  chooseone(value); state.menu='access';
		  vm.$store.commit('listAccess');
	  },
	  listAccess:function(state){
		  $.get("/rbac/access/page/"+state.accesss.number+"/"+state.accesss.size+"",function(data,status){
			  state.accesss.content=data.content;
			  state.accesss.first=data.first;
			  state.accesss.last=data.last;
			  state.accesss.number=data.number;
			  state.accesss.numberOfElements=data.numberOfElements;
			  state.accesss.size=data.size;
			  state.accesss.totalElements=data.totalElements;
			  state.accesss.totalPages=data.totalPages;
		  });
	  },
	  listAccesslike:function(state){
		  $.post("/rbac/access/page/"+state.accesss.number+"/"+state.accesss.size,JSON.stringify({likekey:state.accesss.likekey}),function(data,status){
			  state.accesss.content=data.content;
			  state.accesss.first=data.first;
			  state.accesss.last=data.last;
			  state.accesss.number=data.number;
			  state.accesss.numberOfElements=data.numberOfElements;
			  state.accesss.size=data.size;
			  state.accesss.totalElements=data.totalElements;
			  state.accesss.totalPages=data.totalPages;
		  });
	  },
	  toNumberlistAccess:function(state,value){
		  state.accesss.number=value-1;
		  vm.$store.commit('listAccess');
	  },
	  addAccess:function(state){
		  state.tempAccess.adding=true;
		  $.post("/rbac/access/add",state.tempAccess,function(data,status){
			  $("#addaccessSys").hide();
			  $("#listaccessSys").show();
			  vm.$store.commit('listAccess');
			  state.tempAccess.adding=false;
		  });
		  state.tempAccess.adding=false;
	  },
	  delAccess:function(state,value){
		  $.get("/rbac/access/del/"+value,function(data,status){
			  vm.$store.commit('listAccess');
		  });
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

Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};