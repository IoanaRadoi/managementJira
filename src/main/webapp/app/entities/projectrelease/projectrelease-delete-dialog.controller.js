(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleaseDeleteController',ProjectreleaseDeleteController);

    ProjectreleaseDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectrelease'];

    function ProjectreleaseDeleteController($uibModalInstance, entity, Projectrelease) {
        var vm = this;

        vm.projectrelease = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectrelease.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
