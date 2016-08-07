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


        $http({
          method: 'GET',
          url: '/api/storypointsPerSprint'
        }).then(function successCallback(response) {
            vm.storypointsPerSprint = response.data;
        });

       /* $scope.myJson = {
                     type : "bar",
                     title:{
                       backgroundColor : "transparent",
                       fontColor :"black",
                       text : "Story points per Sprint"
                     },
                     backgroundColor : "white",
                     series : [
                       {
                         values : [1,2,3,4],
                         backgroundColor : "#4DC0CF"
                       }
                     ]
                   };*/
    }
})();
