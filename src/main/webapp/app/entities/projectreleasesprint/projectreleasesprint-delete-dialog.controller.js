(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleasesprintDeleteController',ProjectreleasesprintDeleteController);

    ProjectreleasesprintDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectreleasesprint'];

    function ProjectreleasesprintDeleteController($uibModalInstance, entity, Projectreleasesprint) {
        var vm = this;

        vm.projectreleasesprint = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectreleasesprint.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
