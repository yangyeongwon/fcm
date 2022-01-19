requirejs.config({
    baseUrl: "/resources",
	waitSeconds: 200,
	paths: {
		"jquery": "common/js/jquery/jquery-3.3.1.min",
		"home_app": "main/js/home/app",
	},
	shim: {
		"jquery": {"exports": "$"},
		"home_app": {"exports": "home_app", deps:["jquery"]},
	}
})