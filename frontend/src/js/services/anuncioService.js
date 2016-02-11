(function() {

	var modulo = angular.module('appModule');

	modulo.service('anuncioService', ["$http", "$rootScope", "config", function($http, $rootScope, config) {

		var service = {};

		service.getAnuncios = function(estadoCidade,cidade,marca,modelo,successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "anuncios/filtrar/"+estadoCidade+"/"+cidade+"/"+marca+"/"+modelo,
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};	

		return service;

	}]);

})();