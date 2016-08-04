(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('StorypointDeleteController',StorypointDeleteController);

    StorypointDeleteController.$inject = ['$uibModalInstance', 'entity', 'Storypoint'];

    function StorypointDeleteController($uibModalInstance, entity, Storypoint) {
        var vm = this;

        vm.storypoint = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Storypoint.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
