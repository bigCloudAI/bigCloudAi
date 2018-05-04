var Engine=Matter.Engine;
    Render=Matter.Render;
    World=Matter.World;
    Bodies=Matter.Bodies;
    
    var engine;
    var world;
    var particles = [];
        setup();
function setup(){
	engine=Engine.create();
    world=engine.world;
    var p = new Particle(300,50,10);
    particles.push(p);
    
    var render=Render.create({
                 engine:engine,
                 element:document.body
           });
    Engine.run(engine);
Render.run(render);
    
}

function draw(){
	background(51);
	particles[0].show();
}

//https://www.bilibili.com/video/av9116731/?p=1