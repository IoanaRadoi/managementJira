(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ReleasejiraDeleteController',ReleasejiraDeleteController);

    ReleasejiraDeleteController.$inject = ['$uibModalInstance', 'entity', 'Releasejira'];

    function ReleasejiraDeleteController($uibModalInstance, entity, Releasejira) {
        var vm = this;

        vm.releasejira = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Releasejira.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
