var zjkmenu = {
	template: "<div class='zjkmenu'>{{menuName}}</div>",
	data: function() {
		return {
			menuName:"菜单"
		}
	},
	mounted: function() {
		var _that = this;
		this.$nextTick(function() {
		})
	},
	methods: {
	
	}
}

Vue.component('zjk-menu', zjkmenu)
