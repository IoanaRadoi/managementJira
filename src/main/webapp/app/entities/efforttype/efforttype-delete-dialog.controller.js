(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('EfforttypeDeleteController',EfforttypeDeleteController);

    EfforttypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Efforttype'];

    function EfforttypeDeleteController($uibModalInstance, entity, Efforttype) {
        var vm = this;

        vm.efforttype = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Efforttype.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
