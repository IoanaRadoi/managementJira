(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleaseController', ProjectreleaseController);

    ProjectreleaseController.$inject = ['$scope', '$state', 'Projectrelease'];

    function ProjectreleaseController ($scope, $state, Projectrelease) {
        var vm = this;
        
        vm.projectreleases = [];

        loadAll();

        function loadAll() {
            Projectrelease.query(function(result) {
                vm.projectreleases = result;
            });
        }
    }
})();
