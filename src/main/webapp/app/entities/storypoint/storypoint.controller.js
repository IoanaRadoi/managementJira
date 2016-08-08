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








    }
})();
