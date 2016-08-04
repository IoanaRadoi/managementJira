(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ManageraccountDetailController', ManageraccountDetailController);

    ManageraccountDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Manageraccount', 'Project'];

    function ManageraccountDetailController($scope, $rootScope, $stateParams, previousState, entity, Manageraccount, Project) {
        var vm = this;

        vm.manageraccount = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('managementJiraApp:manageraccountUpdate', function(event, result) {
            vm.manageraccount = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
