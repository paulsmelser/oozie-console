var app = angular.module('Oozie', ['ui.router', 'ngResource', 'angularModalService', 'ngDialog', 'xeditable'])
.config([ '$stateProvider', '$urlRouterProvider', '$locationProvider', function($stateProvider, $urlRouterProvider, $locationProvider) {
	
	$stateProvider.state('index', {
		url: '',
		templateUrl: 'resources/app/views/home/home.html',
	})
	.state('main', {
		url: '/coordinators',
		templateUrl: 'resources/app/views/home/main.html',
	})
	.state('coord', {
		url: '/coordinator/:id',
		templateUrl: 'resources/app/views/home/coord.html',
	})
	.state('wf', {
		url: '/workflow/:id',
		templateUrl: 'resources/app/views/home/workflow.html',
	})
	.state('wfAction', {
		url: '/workflow/:id/action/:name',
		templateUrl: 'resources/app/views/home/action.html',
	})
	.state('wfList', {
		url: '/workflows',
		templateUrl: 'resources/app/views/home/workflow_list.html',
	})
	.state('wfRerun', {
		url: '/workflow/rerun/:id',
		templateUrl: 'resources/app/views/home/workflow_rerun.html',
	});
}]);