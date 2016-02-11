(function() {

	var modulo = angular.module('appModule');

	modulo.controller('InicialCtrl', function($scope, $rootScope, $filter, $window, estadoCidadeService, marcaModeloService,anuncioService) {

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

          estadoCidadeService.getEstados(function(data){

            $scope.estados = data;

          });

          marcaModeloService.getMarcas(function(data){

            $scope.marcas = data;

          });

          $scope.getCidades = function() {

              estadoCidadeService.getCidades($scope.estado,function(data){

                $scope.cidades = data;

                $scope.anuncioEscolhido = {};

                $scope.anuncios = {};

              });

          };

          $scope.getModelos = function() {

              marcaModeloService.getModelos($scope.marca,function(data){

                $scope.modelos = data;

                $scope.anuncioEscolhido = {};

                $scope.anuncios = {};

              });

          };

          $scope.getAnuncios = function() {

              $scope.cidadeEstado = $filter('removeAccents')($scope.estado.toLowerCase()+"-"+$scope.cidade.toLowerCase().replace(" ","-"));

              anuncioService.getAnuncios($scope.cidadeEstado, _.escape($scope.cidade.toLowerCase()), $scope.marca, $scope.modelo, function(data){
                
                $scope.anuncios = data;

                $scope.anuncioEscolhido = {};
              
              });

          };

          $scope.nmVersaoFilter = function (item) { 
            return item.nmVersao !== undefined && item.nmVersao !== null && item.nmVersao !== ''; 
          };

          $scope.abrirAnuncio = function(item) {
            $scope.anuncioEscolhido = item;
          };

          $scope.voltar = function() {
            $scope.anuncioEscolhido = {};
          };

          $scope.visitar = function(item) {
            $window.open(item.link);
          };

	});

})();