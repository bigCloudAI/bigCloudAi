var backgroud = {
	template: "<canvas id='canvas'></canvas>",
	data: function() {
		return {
			canvas:null,
			canvas2:null,
			stars: [],
			count:0,
			w:0,
			h:0,
			hue:217,
			maxStars:1200
		}
	},
	mounted: function() {
		var _that = this;
		this.$nextTick(function() {
			this.init();
		})
	},
	methods: {
		init: function() {
			this.canvas = document.getElementById('canvas');
			this.canvas2 = document.createElement('canvas');
			this.w = canvas.width = window.innerWidth;
			this.h = canvas.height = window.innerHeight;
			ctx2 = this.canvas2.getContext('2d');
			this.canvas2.width = 100;
			this.canvas2.height = 100;
			var half = this.canvas2.width / 2,
			gradient2 = ctx2.createRadialGradient(half, half, 0, half, half, half);
			gradient2.addColorStop(0.025, '#fff');
			gradient2.addColorStop(0.1, 'hsl(' + this.hue + ', 61%, 33%)');
			gradient2.addColorStop(0.25, 'hsl(' + this.hue + ', 64%, 6%)');
			gradient2.addColorStop(1, 'transparent');
			ctx2.fillStyle = gradient2;
			ctx2.beginPath();
			ctx2.arc(half, half, half, 0, Math.PI * 2);
			ctx2.fill();
			for (var i = 0; i < this.maxStars; i++) {
			  this.newStar();
			}
			this.animation();
		},
		random: function(min, max) {
			if(arguments.length < 2) {
				max = min;
				min = 0;
			}
			if(min > max) {
				var hold = max;
				max = min;
				min = hold;
			}
			return Math.floor(Math.random() * (max - min + 1)) + min;
		},
		maxOrbit:function(x, y){
			var max = Math.max(x, y),
			diameter = Math.round(Math.sqrt(max * max + max * max));
			return diameter / 2;
		},
		newStar:function(){
			var star={};
			star.orbitRadius = this.random(this.maxOrbit(this.w, this.h));
			star.radius = this.random(60, star.orbitRadius) / 12;
			star.orbitX = this.w / 2;
			star.orbitY = this.h / 2;
			star.timePassed = this.random(0, this.maxStars);
			star.speed = this.random(star.orbitRadius) / 900000;
			star.alpha = this.random(2, 10) / 10;
			this.count++;
  			this.stars[this.count] = star;
  			var that = this;
  			star.draw=function(){
  				var x = Math.sin(this.timePassed) * this.orbitRadius + this.orbitX;
				y = Math.cos(this.timePassed) * this.orbitRadius + this.orbitY;
				    twinkle = that.random(10);
				  if (twinkle === 1 && this.alpha > 0) {
				    this.alpha -= 0.05;
				  } else if (twinkle === 2 && this.alpha < 1) {
				    this.alpha += 0.05;
				  }
				that.canvas.getContext('2d').globalAlpha = this.alpha;
				that.canvas.getContext('2d').drawImage(that.canvas2, x - this.radius / 2, y - this.radius / 2, this.radius, this.radius);
				this.timePassed += this.speed;
  			}
		},
		animation:function(){
			ctx = this.canvas.getContext('2d');
			ctx.globalCompositeOperation = 'source-over';
			ctx.globalAlpha = 0.8;
			ctx.fillStyle = 'hsla(' + this.hue + ', 64%, 6%, 1)';
			ctx.fillRect(0, 0, this.w, this.h)
			ctx.globalCompositeOperation = 'lighter';
			for (var i = 1, l = this.stars.length; i < l; i++) {
			    this.stars[i].draw();
			};
			window.requestAnimationFrame(this.animation);
		}

	}
}

Vue.component('backgroud', backgroud)
