(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('EfforttypeDetailController', EfforttypeDetailController);

    EfforttypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Efforttype'];

    function EfforttypeDetailController($scope, $rootScope, $stateParams, previousState, entity, Efforttype) {
        var vm = this;

        vm.efforttype = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('managementJiraApp:efforttypeUpdate', function(event, result) {
            vm.efforttype = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
