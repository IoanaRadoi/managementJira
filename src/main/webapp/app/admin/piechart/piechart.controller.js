(function() {
            'use strict';

            angular
                .module('managementJiraApp')
                .controller('PieChartController', PieChartController);

            PieChartController.$inject = ['$scope', '$http',  '$state', 'Projectreleasesprint', 'Storypoint'];

    function PieChartController ($scope, $http, $state, Projectreleasesprint, Storypoint) {
var vm = this;

        vm.projectreleasesprints = [];


        loadAll();

        function loadAll() {
            Projectreleasesprint.query(function(result) {
                vm.projectreleasesprints = result;
            });
        }

       $http({
                       method: 'GET',
                       url: '/api/storypointsDetailsPerSprint'
                   }).then(function successCallback(response) {
                       vm.categoriesPerSprint = response.data;
                   });

        $scope.sendData = function(){
                        $scope.labels = [];
                        $scope.data = [];

                        angular.forEach(vm.categoriesPerSprint, function(value1){

                            if(value1.idProjectReleaseSprint == vm.projectreleasesprintSelected.id){

                                angular.forEach(value1.valuePerCategorie, function(value, key) {
                                    $scope.data.push(value);
                                    $scope.labels.push(key);

                                });
                            }

                        });
                    }
    }
})();
