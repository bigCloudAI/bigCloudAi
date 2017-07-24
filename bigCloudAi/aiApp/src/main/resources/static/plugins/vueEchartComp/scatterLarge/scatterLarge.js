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
        		count:1,
        		    myChart:null,
        		    domId:"",
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
	        		        data:[/*'sin'*//*,'cos'*/]
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
	        		    ]
	        		}
        		}
        	},		
		template : '#'+compId,
		mounted : function() {
			var _that = this;
			this.$nextTick(function() {
				_that.domId =new Date().getTime(); 
				$("#main").attr('id',_that.domId);
				_that.initEchart();
				_that.title("test");
				_that.data("sins",(function () {
	                var d = [];
	                var len = 100;
	                var x = 0;
	                while (len--) {
	                    x = (Math.random() * 10).toFixed(3) - 0;
	                    d.push([
	                        x,
	                        x*2 - 1000
	                    ]);
	                }
	                return d;
	            })());
			})
		},
		methods:{
			test:function(){
				var _that=this;
				_that.count++;
				},
			 initEchart:function(){
				 var _that = this;
				 _that.myChart = echarts.init(document.getElementById(_that.domId));
			 },
			 showEchar:function(){
				 var _that = this;
				 _that.myChart.setOption(_that.option);
			 },
			 title:function(title){
				 var _that = this;
				 _that.option.title.text=title;
				 _that.showEchar();
     		    },
     		 data:function(legend,seriesData){
     			 var _that = this;
     			 _that.option.legend.data.push(legend);
     			 alert( _that.option.legend.data);
     			 var series = 
     			{ name:legend,
		            type:'scatter',
		            large: true,
		            symbolSize: 3,
		            data: seriesData
		        };
     			 _that.option.series.push(series);
     			 _that.showEchar();
     		   }
			 }
	};
}
