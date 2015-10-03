angular.module('JsImport',[])
	.provider('$jsImport', function(){
		this.$get = {
			import: function(url)
			{
				// Adding the script tag to the head as suggested before
				var body = document.getElementsByTagName('body')[0];
		    	var script = document.createElement('script');
		    	script.type = 'text/javascript';
		    	script.src = url;
		    	// Fire the loading
		    	body.appendChild(script);
			}
		};
		this.import = function(url){
			return this.$get.import(url)
		};
});



