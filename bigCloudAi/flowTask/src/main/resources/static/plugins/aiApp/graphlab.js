function show(ht){
	var domId =new Date().getTime(); 
	$("body").append("<div id='app"+domId+"'><"+ht+"></"+ht+"><div>");
	new Vue({
		  el: '#app'+domId,
		  data: {
		  }
	})
}