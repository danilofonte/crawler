(function(exports, global) {
    global["app"] = exports;
    (function($) {
        var modulo = angular.module("appModule", [ "ngRoute" ]);
        modulo.config([ "$routeProvider", "$httpProvider", function($routeProvider, $httpProvider) {
            $routeProvider.when("/", {
                templateUrl: "home/inicial.html",
                controller: "InicialCtrl"
            }).when("/sobre", {
                templateUrl: "home/sobre.html",
                controller: "SobreCtrl"
            }).when("/contato", {
                templateUrl: "home/contato.html",
                controller: "ContatoCtrl"
            }).otherwise({
                redirectTo: "/"
            });
            $httpProvider.responseInterceptors.push("myHttpInterceptor");
            var spinnerFunction = function spinnerFunction(data, headersGetter) {
                $("#loader").removeClass("off");
                $("#loader").addClass("on");
                return data;
            };
            $httpProvider.defaults.transformRequest.push(spinnerFunction);
        } ]).filter("removeAccents", removeAccents).filter("isEmpty", [ function() {
            return function(object) {
                return angular.equals({}, object);
            };
        } ]).directive("onErrorSrc", function() {
            return {
                link: function(scope, element, attrs) {
                    element.bind("error", function() {
                        if (attrs.src != attrs.onErrorSrc) {
                            attrs.$set("src", attrs.onErrorSrc);
                        }
                    });
                }
            };
        }).factory("myHttpInterceptor", function($q, $window) {
            return function(promise) {
                return promise.then(function(response) {
                    $("#loader").removeClass("on");
                    $("#loader").addClass("off");
                    return response;
                }, function(response) {
                    $("#loader").removeClass("on");
                    $("#loader").addClass("off");
                    return $q.reject(response);
                });
            };
        });
    })(jQuery);
    (function() {
        var modulo = angular.module("appModule");
        modulo.service("anuncioService", [ "$http", "$rootScope", "config", function($http, $rootScope, config) {
            var service = {};
            service.getAnuncios = function(estadoCidade, cidade, marca, modelo, successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "anuncios/filtrar/" + estadoCidade + "/" + cidade + "/" + marca + "/" + modelo,
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            return service;
        } ]);
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.factory("config", function() {
            var defaultConfig = {
                BASE_URL: "/"
            };
            return defaultConfig;
        });
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.controller("ContatoCtrl", [ "$scope", "$rootScope", "$filter", function($scope, $rootScope, $filter) {} ]);
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.service("estadoCidadeService", [ "$http", "$rootScope", "config", function($http, $rootScope, config) {
            var service = {};
            service.getEstados = function(successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "estados",
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            service.getCidades = function(uf, successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "cidades/" + uf,
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            return service;
        } ]);
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.controller("InicialCtrl", [ "$scope", "$rootScope", "$filter", "$window", "estadoCidadeService", "marcaModeloService", "anuncioService", function($scope, $rootScope, $filter, $window, estadoCidadeService, marcaModeloService, anuncioService) {
            $scope.estados = [];
            $scope.cidades = [];
            $scope.marcas = [];
            $scope.modelos = [];
            $scope.estado = {};
            $scope.cidade = {};
            $scope.marca = {};
            $scope.modelo = {};
            $scope.anuncios = {};
            $scope.OLX = "OLX";
            $scope.WEBMOTORS = "WEBMOTORS";
            $scope.anuncioEscolhido = {};
            estadoCidadeService.getEstados(function(data) {
                $scope.estados = data;
            });
            marcaModeloService.getMarcas(function(data) {
                $scope.marcas = data;
            });
            $scope.getCidades = function() {
                estadoCidadeService.getCidades($scope.estado, function(data) {
                    $scope.cidades = data;
                    $scope.anuncioEscolhido = {};
                    $scope.anuncios = {};
                });
            };
            $scope.getModelos = function() {
                marcaModeloService.getModelos($scope.marca, function(data) {
                    $scope.modelos = data;
                    $scope.anuncioEscolhido = {};
                    $scope.anuncios = {};
                });
            };
            $scope.getAnuncios = function() {
                $scope.cidadeEstado = $filter("removeAccents")($scope.estado.toLowerCase() + "-" + $scope.cidade.toLowerCase().replace(" ", "-"));
                anuncioService.getAnuncios($scope.cidadeEstado, _.escape($scope.cidade.toLowerCase()), $scope.marca, $scope.modelo, function(data) {
                    $scope.anuncios = data;
                    $scope.anuncioEscolhido = {};
                });
            };
            $scope.nmVersaoFilter = function(item) {
                return item.nmVersao !== undefined && item.nmVersao !== null && item.nmVersao !== "";
            };
            $scope.abrirAnuncio = function(item) {
                $scope.anuncioEscolhido = item;
            };
            $scope.voltar = function() {
                $anuncioEscolhido = {};
            };
            $scope.visitar = function(item) {
                $window.open(item.link);
            };
        } ]);
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.controller("AppCtrl", [ "$scope", "$rootScope", "$location", "$timeout", "config", function($scope, $rootScope, $location, $timeout, config) {} ]);
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.service("marcaModeloService", [ "$http", "$rootScope", "config", function($http, $rootScope, config) {
            var service = {};
            service.getMarcas = function(successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "marcas",
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            service.getModelos = function(marca, successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "modelos/" + marca,
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            return service;
        } ]);
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.controller("SobreCtrl", [ "$scope", "$rootScope", "$filter", function($scope, $rootScope, $filter) {} ]);
    })();
})({}, function() {
    return this;
}());