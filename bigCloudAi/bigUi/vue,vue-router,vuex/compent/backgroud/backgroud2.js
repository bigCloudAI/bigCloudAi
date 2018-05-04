var backgroud2 = {
	template: "<canvas id='canvas'></canvas>",
	data: function() {
		return {
			canvas:null,
			width:0,
			height:0,
			size:30,
			lines:[],
			tick:0
		}
	},
	mounted: function() {
		var _that = this;
		this.$nextTick(function() {
			this.init();
		})
	},
	methods: {
		init:function(){
			this.canvas = document.getElementById('canvas');
			this.size = 30;
			this.lines = [];
			this.reset();
			this.loop();
		},
		newLine:function(){
			var line={};
			line.path = [];
			line.speed = this.rand(10, 20);
			line.count = this.randInt(10, 30);
			line.x = this.width / 2 +1;
			line.y = this.height / 2 + 1;
			line.target = {
				x: this.width / 2,
				y: this.height / 2
			};
			line.dist = 0;
			line.angle = 0;
			line.hue = this.tick / 5;
			line.life = 1;
			var that = this;
			line.step=function(i){
				this.x += Math.cos(this.angle) * this.speed;
				this.y += Math.sin(this.angle) * this.speed;
				this.updateDist();
				if (this.dist < this.speed) {
					this.x = this.target.x;
					this.y = this.target.y;
					this.changeTarget();
				}
			
				this.path.push({
					x: this.x,
					y: this.y
				});
				if (this.path.length > this.count) {
					this.path.shift();
				}
			
				this.life -= 0.001;
			
				if (this.life <= 0) {
					this.path = null;
					that.lines.splice(i, 1);
				}
			};
			line.updateDist=function(){
				var dx = this.target.x - this.x,
				dy = this.target.y - this.y;
				this.dist = Math.sqrt(dx * dx + dy * dy);
			};
			line.updateAngle=function(){
				var dx = this.target.x - this.x;
				var dy = this.target.y - this.y;
				this.angle = Math.atan2(dy, dx);
			};
			line.changeTarget=function(){
				var randStart = that.randInt(0, 3);
				var size=that.size;
				switch (randStart) {
					case 0: // up
						this.target.y = this.y - size;
						break;
					case 1: // right
						this.target.x = this.x + size;
						break;
					case 2: // down
						this.target.y = this.y + size;
						break;
					case 3: // left
						this.target.x = this.x - size;
				}
				this.updateAngle();
			};
			line.draw=function(i){
				var ctx=that.canvas.getContext('2d');
				ctx.beginPath();
				var rando = that.rand(0, 10);
				for (var j = 0, length = this.path.length; j < length; j++) {
					ctx[(j === 0) ? 'moveTo' : 'lineTo'](this.path[j].x + that.rand(-rando, rando), this.path[j].y + that.rand(-rando, rando));
				}
				ctx.strokeStyle = 'hsla(' + that.rand(this.hue, this.hue + 30) + ', 80%, 55%, ' + (this.life / 3) + ')';
				ctx.lineWidth = that.rand(0.1, 2);
				
				ctx.stroke();
			};
			
			return line;
		},
		rand: function(min, max) {
			return Math.random() * (max - min) + min;
		},
		randInt: function(min, max) {
			return Math.floor(min + Math.random() * (max - min + 1));
		},
		reset:function(){
			this.width = Math.ceil(window.innerWidth / 2) * 2;
			this.height = Math.ceil(window.innerHeight / 2) * 2;
			this.tick = 0;
			this.lines.length = 0;
			this.canvas.width = this.width;
			this.canvas.height = this.height;
		},
		loop:function() {
			requestAnimationFrame(this.loop);
			this.create();
			this.step();
			this.clear();
			this.draw();
			this.tick++;
		},
		create:function() {
			if (this.tick % 10 === 0) {
				line = this.newLine();
				line.updateAngle();
			    line.updateDist();
				this.lines.push(line);
			}
		},
		step:function() {
			var i = this.lines.length;
			while (i--) {
				this.lines[i].step(i);
			}
		},
		clear:function() {
			ctx = this.canvas.getContext('2d');
			ctx.globalCompositeOperation = 'destination-out';
			ctx.fillStyle = 'hsla(0, 0%, 0%, 0.1';
			ctx.fillRect(0, 0, this.width, this.height);
			ctx.globalCompositeOperation = 'lighter';
		},
		draw:function() {
			ctx = this.canvas.getContext('2d');
			ctx.save();
			ctx.translate(this.width / 2, this.height / 2);
			ctx.rotate(this.tick * 0.001);
			var scale = 0.8 + Math.cos(this.tick * 0.02) * 0.2;
			ctx.scale(scale, scale);
			ctx.translate(-this.width / 2, -this.height / 2);
			var i = this.lines.length;
			while (i--) {
				this.lines[i].draw(i);
			}
			ctx.restore();
		}
	
	}
}

Vue.component('backgroud2', backgroud2)
