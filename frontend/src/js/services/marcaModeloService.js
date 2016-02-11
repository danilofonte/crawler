(function() {

	var modulo = angular.module('appModule');

	modulo.service('marcaModeloService', ["$http", "$rootScope", "config", function($http, $rootScope, config) {

		var service = {};

		service.getMarcas = function(successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "marcas",
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

		service.getModelos = function(marca,successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "modelos/"+marca,
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};
    

		return service;

	}]);

})();