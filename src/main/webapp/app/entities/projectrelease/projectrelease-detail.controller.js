(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleaseDetailController', ProjectreleaseDetailController);

    ProjectreleaseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectrelease', 'Project', 'Releasejira', 'Year'];

    function ProjectreleaseDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectrelease, Project, Releasejira, Year) {
        var vm = this;

        vm.projectrelease = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('managementJiraApp:projectreleaseUpdate', function(event, result) {
            vm.projectrelease = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
