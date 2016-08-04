(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('YearController', YearController);

    YearController.$inject = ['$scope', '$state', 'Year'];

    function YearController ($scope, $state, Year) {
        var vm = this;
        
        vm.years = [];

        loadAll();

        function loadAll() {
            Year.query(function(result) {
                vm.years = result;
            });
        }
    }
})();
