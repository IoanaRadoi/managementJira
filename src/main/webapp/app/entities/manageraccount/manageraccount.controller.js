(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ManageraccountController', ManageraccountController);

    ManageraccountController.$inject = ['$scope', '$state', 'Manageraccount', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants'];

    function ManageraccountController ($scope, $state, Manageraccount, ParseLinks, AlertService, pagingParams, paginationConstants) {
        var vm = this;




                vm.manageraccounts = [];

                loadAll();

                function loadAll() {
                    Manageraccount.query(function(result) {
                        vm.manageraccounts = result;
                    });
                }



    }
})();
