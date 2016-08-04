(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleasesprintDetailController', ProjectreleasesprintDetailController);

    ProjectreleasesprintDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectreleasesprint', 'Projectrelease', 'Sprint'];

    function ProjectreleasesprintDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectreleasesprint, Projectrelease, Sprint) {
        var vm = this;

        vm.projectreleasesprint = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('managementJiraApp:projectreleasesprintUpdate', function(event, result) {
            vm.projectreleasesprint = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
