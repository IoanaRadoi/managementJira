(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('StorypointController', StorypointController);

    StorypointController.$inject = ['$scope', '$http',  '$state', 'Storypoint'];

    function StorypointController ($scope, $http, $state, Storypoint) {
        var vm = this;

        vm.storypoints = [];
        vm.storypointsPerSprint = [];

        loadAll();

        function loadAll() {
            Storypoint.query(function(result) {
                vm.storypoints = result;
            });
        }

        $scope.labels = [];
        $scope.data = [];

        $http({
          method: 'GET',
          url: '/api/storypointsPerSprint'
        }).then(function successCallback(response) {
            vm.storypointsPerSprint = response.data;
             angular.forEach(vm.storypointsPerSprint, function(value){
                          $scope.labels.push(value.project + " " + value.year + " " + value.release + " " + value.sprint);
                          $scope.data.push(value.totalStoryPointsPerSprint);
                       });
        });






    }
})();
