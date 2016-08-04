(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ReleasejiraDetailController', ReleasejiraDetailController);

    ReleasejiraDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Releasejira'];

    function ReleasejiraDetailController($scope, $rootScope, $stateParams, previousState, entity, Releasejira) {
        var vm = this;

        vm.releasejira = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('managementJiraApp:releasejiraUpdate', function(event, result) {
            vm.releasejira = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
