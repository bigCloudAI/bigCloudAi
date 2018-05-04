
var cardEx = { template: '<div class="ccc">	<div class="ccc div-test"><card></card></div><div class="ccc div-test">	<card></card></div>' }

var routes = [
  { path: '/compent/card', component: cardEx }
]

var router = new VueRouter({
  routes: routes
})