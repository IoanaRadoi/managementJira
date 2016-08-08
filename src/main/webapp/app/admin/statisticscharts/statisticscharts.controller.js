(function() {
            'use strict';

            angular
                .module('managementJiraApp')
                .controller('StatisticsChartController', StatisticsChartController);

            StatisticsChartController.$inject = ['$scope', '$http',  '$state', 'Projectrelease'];

    function StatisticsChartController ($scope, $http, $state, Projectrelease) {


            var vm = this;

            vm.projectreleasesSelected = [];



            vm.projectreleases = [];

                    loadAll();

                    function loadAll() {
                        Projectrelease.query(function(result) {
                            vm.projectreleases = result;
                        });
                    }

            vm.storypointsPerSprint = [];


            $http({
                method: 'GET',
                url: '/api/storypointsPerSprint'
            }).then(function successCallback(response) {
                vm.storypointsPerSprint = response.data;
            });



            $scope.sendData = function(){
                $scope.labels = [];
                $scope.data = [];

                $scope.dataForAv = [];
                $scope.series = ['Story points Per Sprint'];
                $scope.seriesAv = ['Average Per Sprint'];

                //we need to select vm.storypointsPerSprint so that to involve only the ones selected

                angular.forEach(vm.storypointsPerSprint, function(value1){

                    angular.forEach(vm.projectreleasesSelected, function(value2){

                        if (value2.id == value1.idProjectRelease){  //if the storypoint coming from server has a projectrelease selected

                            $scope.labels.push(value1.project + " " + value1.year + " " + value1.release + " " + value1.sprint);
                            $scope.data.push(value1.totalStoryPointsPerSprint);
                            $scope.average =  value1.totalStoryPointsPerSprint/value1.sprintCapacity
                            $scope.dataForAv.push($scope.average);

                        }
                    });
                });
            }
    }
})();
