(function($) {

	var modulo = angular.module('appModule', ['ngRoute']);



	modulo.config(
		function($routeProvider,$httpProvider) {

			$routeProvider.when('/',
			{
				templateUrl: 'home/inicial.html',
				controller: 'InicialCtrl'
			})	
			.when('/sobre',
			{
				templateUrl: 'home/sobre.html',
				controller: 'SobreCtrl'
			})	
			.when('/contato',
			{
				templateUrl: 'home/contato.html',
				controller: 'ContatoCtrl'
			})		
			.otherwise({
				redirectTo: '/'
			});

			$httpProvider.responseInterceptors.push('myHttpInterceptor');
			var spinnerFunction = function spinnerFunction(data, headersGetter) {
				$("#loader").removeClass("off");
				$("#loader").addClass("on");
				return data;
			};
			$httpProvider.defaults.transformRequest.push(spinnerFunction);
		}
	)

	.filter('removeAccents', removeAccents)

	.filter('isEmpty', [function() {
		return function(object) {
			return angular.equals({}, object);
		};
	}])

	.directive('onErrorSrc', function() {
		return {
			link: function(scope, element, attrs) {
				element.bind('error', function() {
					if (attrs.src != attrs.onErrorSrc) {
						attrs.$set('src', attrs.onErrorSrc);
					}
				});
			}
		};
	})

	.factory('myHttpInterceptor', function ($q, $window) {
		return function (promise) {
			return promise.then(function (response) {
				$("#loader").removeClass("on");
				$("#loader").addClass("off");
				return response;
			}, function (response) {
				$("#loader").removeClass("on");
				$("#loader").addClass("off");
				return $q.reject(response);
			});
		};
	});

})(jQuery);
