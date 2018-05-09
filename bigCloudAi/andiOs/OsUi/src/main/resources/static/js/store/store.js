var store = new Vuex.Store({
	modules : {
		menu : menu
	},
	state : {
		count:1
	},
	getters:{
		countgetter:function(state){
			return state.count+2;
		}
	}

})

/*store.commit('increment')*/
//console.log(store.state.menu.test)   // -> 1   