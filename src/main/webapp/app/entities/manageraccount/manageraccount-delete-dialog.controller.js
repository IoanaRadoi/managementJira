(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ManageraccountDeleteController',ManageraccountDeleteController);

    ManageraccountDeleteController.$inject = ['$uibModalInstance', 'entity', 'Manageraccount'];

    function ManageraccountDeleteController($uibModalInstance, entity, Manageraccount) {
        var vm = this;

        vm.manageraccount = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Manageraccount.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
