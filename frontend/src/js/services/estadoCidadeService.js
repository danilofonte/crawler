(function() {

	var modulo = angular.module('appModule');

	modulo.service('estadoCidadeService', ["$http", "$rootScope", "config", function($http, $rootScope, config) {

		var service = {};

		service.getEstados = function(successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "estados",
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

		service.getCidades = function(uf,successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "cidades/"+uf,
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

		return service;

	}]);

})();