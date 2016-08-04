(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleasesprintController', ProjectreleasesprintController);

    ProjectreleasesprintController.$inject = ['$scope', '$state', 'Projectreleasesprint'];

    function ProjectreleasesprintController ($scope, $state, Projectreleasesprint) {
        var vm = this;
        
        vm.projectreleasesprints = [];

        loadAll();

        function loadAll() {
            Projectreleasesprint.query(function(result) {
                vm.projectreleasesprints = result;
            });
        }
    }
})();
