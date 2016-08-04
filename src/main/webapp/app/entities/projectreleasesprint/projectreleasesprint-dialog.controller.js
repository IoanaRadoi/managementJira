(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleasesprintDialogController', ProjectreleasesprintDialogController);

    ProjectreleasesprintDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectreleasesprint', 'Projectrelease', 'Sprint'];

    function ProjectreleasesprintDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectreleasesprint, Projectrelease, Sprint) {
        var vm = this;

        vm.projectreleasesprint = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projectreleases = Projectrelease.query();
        vm.sprints = Sprint.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectreleasesprint.id !== null) {
                Projectreleasesprint.update(vm.projectreleasesprint, onSaveSuccess, onSaveError);
            } else {
                Projectreleasesprint.save(vm.projectreleasesprint, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:projectreleasesprintUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
