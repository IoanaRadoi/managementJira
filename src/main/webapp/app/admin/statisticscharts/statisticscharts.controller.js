(function() {
            'use strict';

            angular
                .module('managementJiraApp')
                .controller('StatisticsChartController', StatisticsChartController);

            StatisticsChartController.$inject = ['$scope', '$http',  '$state', 'Projectrelease'];

    function StatisticsChartController ($scope, $http, $state, Projectrelease) {


            var vm = this;



            vm.projectreleases = [];

                    loadAll();

                    function loadAll() {
                        Projectrelease.query(function(result) {
                            vm.projectreleases = result;
                        });
                    }

            vm.storypointsPerSprint = [];
            $scope.labels = [];
            $scope.data = [];

            $scope.dataForAv = [];
            $scope.series = ['Story points Per Sprint'];
            $scope.seriesAv = ['Average Per Sprint'];

            $http({
                method: 'GET',
                url: '/api/storypointsPerSprint'
            }).then(function successCallback(response) {
                vm.storypointsPerSprint = response.data;

                     angular.forEach(vm.storypointsPerSprint, function(value){
                                  $scope.labels.push(value.project + " " + value.year + " " + value.release + " " + value.sprint);
                                  $scope.data.push(value.totalStoryPointsPerSprint);
                                  $scope.average =  value.totalStoryPointsPerSprint/value.sprintCapacity
                                  $scope.dataForAv.push($scope.average);
                               });
            });
    }
})();
