var store = new Vuex.Store({
	state: {
		count: 0
	},
	mutations: {
		increment: function(state) {
			state.count++
		}
	}
})

store.commit('increment')
console.log(store.state.count)   // -> 1   