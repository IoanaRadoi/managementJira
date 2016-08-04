(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ManageraccountDialogController', ManageraccountDialogController);

    ManageraccountDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Manageraccount', 'Project'];

    function ManageraccountDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Manageraccount, Project) {
        var vm = this;

        vm.manageraccount = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projects = Project.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.manageraccount.id !== null) {
                Manageraccount.update(vm.manageraccount, onSaveSuccess, onSaveError);
            } else {
                Manageraccount.save(vm.manageraccount, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:manageraccountUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
