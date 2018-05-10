
var testrouter = { template: '<div>	testrouter</div>' }
var testrouter2 = { template: '<div>	2222222222</div>' }

var routes = [
  { path: '/go', components: {os:testrouter} },
  { path: '/go2', components: {app:testrouter2} }
]

var router = new VueRouter({
  routes: routes
})