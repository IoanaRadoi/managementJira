(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ReleasejiraController', ReleasejiraController);

    ReleasejiraController.$inject = ['$scope', '$state', 'Releasejira'];

    function ReleasejiraController ($scope, $state, Releasejira) {
        var vm = this;
        
        vm.releasejiras = [];

        loadAll();

        function loadAll() {
            Releasejira.query(function(result) {
                vm.releasejiras = result;
            });
        }
    }
})();
