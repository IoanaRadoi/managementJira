(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleaseDialogController', ProjectreleaseDialogController);

    ProjectreleaseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectrelease', 'Project', 'Releasejira', 'Year'];

    function ProjectreleaseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectrelease, Project, Releasejira, Year) {
        var vm = this;

        vm.projectrelease = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projects = Project.query();
        vm.releasejiras = Releasejira.query();
        vm.years = Year.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectrelease.id !== null) {
                Projectrelease.update(vm.projectrelease, onSaveSuccess, onSaveError);
            } else {
                Projectrelease.save(vm.projectrelease, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:projectreleaseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
