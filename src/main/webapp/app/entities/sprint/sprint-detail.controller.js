(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('SprintDetailController', SprintDetailController);

    SprintDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Sprint'];

    function SprintDetailController($scope, $rootScope, $stateParams, previousState, entity, Sprint) {
        var vm = this;

        vm.sprint = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('managementJiraApp:sprintUpdate', function(event, result) {
            vm.sprint = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
