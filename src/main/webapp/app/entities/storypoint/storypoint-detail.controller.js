(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('StorypointDetailController', StorypointDetailController);

    StorypointDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Storypoint', 'Projectreleasesprint', 'Efforttype', 'Item'];

    function StorypointDetailController($scope, $rootScope, $stateParams, previousState, entity, Storypoint, Projectreleasesprint, Efforttype, Item) {
        var vm = this;

        vm.storypoint = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('managementJiraApp:storypointUpdate', function(event, result) {
            vm.storypoint = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
