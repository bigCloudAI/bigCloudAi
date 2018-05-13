var vm = new Vue({
	el: '#wrapper',
	router:router,
	store:store,
	mounted: function () { 
		this.$nextTick(function () {
			this.$store.commit('isLoginCheck');
		})
	},
	data: {

	},
	methods: {
	}
})
