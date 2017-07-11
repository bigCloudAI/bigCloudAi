function initHtmlTemp(htmlUrl,compName){
	$.ajax({
		async : false,
		url : htmlUrl,
		success : function(result) {
			$("body").append(result);
			var compCon = Vue.extend(initComp());
			Vue.component(compName, compCon);
		}
	});
}

initHtmlTemp("/plugins/vue/vueComp/grid/tcGrid.html","tc-grid")

function initComp(){
	return {
        data:function(){
        	return {
        		count:1
        		}
        	},		
		template : '#tcGrid',
		methods:{
			test:function(){
				var _that=this;
				_that.count++;
				}
		}
			
	};
}
