var Example = Example || {};

Matter.use(
    'matter-wrap'
);

Example.avalanche = function() {
    var Engine = Matter.Engine,
        Render = Matter.Render,
        Runner = Matter.Runner,
        Composite = Matter.Composite,
        Composites = Matter.Composites,
        Common = Matter.Common,
        MouseConstraint = Matter.MouseConstraint,
        Mouse = Matter.Mouse,
        World = Matter.World,
        Bodies = Matter.Bodies;

    // create engine
    var engine = Engine.create(),
        world = engine.world;

    // create renderer
    var render = Render.create({
        element: document.body,
        engine: engine,
        options: {
            width: 800,
            height: 600,
            showAngleIndicator: true
        }
    });

    Render.run(render);

    // create runner
    var runner = Runner.create();
    Runner.run(runner, engine);


    // add bodies
    render.options.wireframes=false;
        World.add(world, [
        // walls
        Bodies.rectangle(400, 5, 800, 100, { isStatic: true }),
        Bodies.rectangle(0, 300, 50, 600, { isStatic: true }),
        Bodies.rectangle(800, 300, 50, 600, { isStatic: true }),
        Bodies.rectangle(400, 600, 800, 100, { isStatic: true })
    ]);
    
    var stack = Composites.stack( 400, 20, 1, 1, 0, 0, function(x, y) {
        return Bodies.circle(x, y, Common.random(10, 12), 
        { friction: 0.00001, restitution: 0.5, density: 0.001 });
    });
    
    World.add(world, stack);
    
    World.add(world, [
       // Bodies.rectangle(308, 190, 200, 50, { isStatic: true, angle: -Math.PI * 0.05 }),
       // Bodies.rectangle(493, 190, 200, 50, { isStatic: true, angle: Math.PI * 0.05 }),
       // Bodies.c(308, 150, 200, 50, { isStatic: true, angle: -Math.PI * 0.05 }),
        Bodies.circle(400, 150, 40, { isStatic: true}),
        Bodies.circle(300, 200, 40, { isStatic: true}),
        Bodies.circle(500, 200, 40, { isStatic: true}),
        Bodies.circle(400, 300, 40, { isStatic: true}),
        Bodies.circle(290, 300, 40, { isStatic: true}),
        Bodies.circle(510, 300, 40, { isStatic: true}),
        Bodies.circle(300, 400, 40, { isStatic: true}),
        Bodies.circle(500, 400, 40, { isStatic: true}),
         Bodies.circle(400, 400, 40, { isStatic: true}),
    ]);

    // add mouse control
    var mouse = Mouse.create(render.canvas),
        mouseConstraint = MouseConstraint.create(engine, {
            mouse: mouse,
            constraint: {
                stiffness: 0.2,
                render: {
                    visible: false
                }
            }
        });

    World.add(world, mouseConstraint);

    // keep the mouse in sync with rendering
    render.mouse = mouse;

    // fit the render viewport to the scene
    Render.lookAt(render, Composite.allBodies(world));

    // wrapping using matter-wrap plugin
    for (var i = 0; i < stack.bodies.length; i += 1) {
        stack.bodies[i].plugin.wrap = {
            min: { x: render.bounds.min.x, y: render.bounds.min.y },
            max: { x: render.bounds.max.x, y: render.bounds.max.y }
        };
    }

    // context for MatterTools.Demo
    return {
        engine: engine,
        runner: runner,
        render: render,
        canvas: render.canvas,
        stop: function() {
            Matter.Render.stop(render);
            Matter.Runner.stop(runner);
        }
    };
};

Example.avalanche();
