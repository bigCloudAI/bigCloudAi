var card = {
	template: "<div class='card'>	{{$store.state.count}}		</div>",
	data: function() {
		return {
			counter: 0
		}
	},
	methods:{
		countadd:function(){
			this.counter++;
			this.$store.commit('increment')
		}
	}
}

Vue.component('card', card)