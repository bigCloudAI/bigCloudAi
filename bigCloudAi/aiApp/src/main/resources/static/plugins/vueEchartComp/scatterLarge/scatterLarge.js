function initHtmlTemp(htmlUrl,compName,compId){
	$.ajax({
		async : false,
		url : htmlUrl,
		success : function(result) {
			$("body").append(result);
			var compCon = Vue.extend(initComp(compId));
			Vue.component(compName, compCon);
		}
	});
}
initHtmlTemp("/plugins/vueEchartComp/scatterLarge/scatterLarge.html","scatter-large","scatterLarge")
function initComp(compId){
	return {
        data:function(){
        	return {
        		    myChart:null,
	        		option:{
	        		    title: {
	        		        text: '大规模散点图'
	        		    },
	        		    tooltip : {
	        		        trigger: 'axis',
	        		        showDelay : 0,
	        		        axisPointer:{
	        		            show: true,
	        		            type : 'cross',
	        		            lineStyle: {
	        		                type : 'dashed',
	        		                width : 1
	        		            }
	        		        },
	        		        zlevel: 1
	        		    },
	        		    legend: {
	        		        data:['sin','cos']
	        		    },
	        		    toolbox: {
	        		        show : true,
	        		        feature : {
	        		            mark : {show: true},
	        		            dataZoom : {show: true},
	        		            dataView : {show: true, readOnly: false},
	        		            restore : {show: true},
	        		            saveAsImage : {show: true}
	        		        }
	        		    },
	        		    xAxis : [
	        		        {
	        		            type : 'value',
	        		            scale:true
	        		        }
	        		    ],
	        		    yAxis : [
	        		        {
	        		            type : 'value',
	        		            scale:true
	        		        }
	        		    ],
	        		    series : [
	        		        {
	        		            name:'sin',
	        		            type:'scatter',
	        		            large: true,
	        		            symbolSize: 3,
	        		            data: (function () {
	        		                var d = [];
	        		                var len = 10000;
	        		                var x = 0;
	        		                while (len--) {
	        		                    x = (Math.random() * 10).toFixed(3) - 0;
	        		                    d.push([
	        		                        x,
	        		                        //Math.random() * 10
	        		                        (Math.sin(x) - x * (len % 2 ? 0.1 : -0.1) * Math.random()).toFixed(3) - 0
	        		                    ]);
	        		                }
	        		                //console.log(d)
	        		                return d;
	        		            })()
	        		        },
	        		        {
	        		            name:'cos',
	        		            type:'scatter',
	        		            large: true,
	        		            symbolSize: 2,
	        		            data: (function () {
	        		                var d = [];
	        		                var len = 20000;
	        		                var x = 0;
	        		                while (len--) {
	        		                    x = (Math.random() * 10).toFixed(3) - 0;
	        		                    d.push([
	        		                        x,
	        		                        //Math.random() * 10
	        		                        (Math.cos(x) - x * (len % 2 ? 0.1 : -0.1) * Math.random()).toFixed(3) - 0
	        		                    ]);
	        		                }
	        		                //console.log(d)
	        		                return d;
	        		            })()
	        		        }
	        		    ]
	        		}
        		}
        	},		
		template : '#'+compId,
		mounted : function() {
			var _that = this;
			this.$nextTick(function() {
				_that.initEchart();_that.showEchar();
			})
		},
		methods:{
			test:function(){
				var _that=this;
				_that.count++;
				},
			 initEchart:function(){
				 var _that = this;
				 _that.myChart = echarts.init(document.getElementById('main'));
			 },
			 showEchar:function(){
				 var _that = this;
				 _that.myChart.setOption(_that.option);
			 }	
		}
			
	};
}
