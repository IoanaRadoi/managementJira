(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('StorypointController', StorypointController);

    StorypointController.$inject = ['$scope', '$state', 'Storypoint'];

    function StorypointController ($scope, $state, Storypoint) {
        var vm = this;
        
        vm.storypoints = [];

        loadAll();

        function loadAll() {
            Storypoint.query(function(result) {
                vm.storypoints = result;
            });
        }
    }
})();
