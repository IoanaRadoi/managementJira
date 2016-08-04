(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('SprintController', SprintController);

    SprintController.$inject = ['$scope', '$state', 'Sprint'];

    function SprintController ($scope, $state, Sprint) {
        var vm = this;
        
        vm.sprints = [];

        loadAll();

        function loadAll() {
            Sprint.query(function(result) {
                vm.sprints = result;
            });
        }
    }
})();
