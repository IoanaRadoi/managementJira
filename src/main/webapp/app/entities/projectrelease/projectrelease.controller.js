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

        $scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
          $scope.series = ['Series A'];

          $scope.data = [
            [65, 59, 80, 81, 56, 55, 40]
          ];


    }
})();
