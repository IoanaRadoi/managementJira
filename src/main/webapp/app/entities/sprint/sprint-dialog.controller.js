(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('SprintDialogController', SprintDialogController);

    SprintDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Sprint'];

    function SprintDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Sprint) {
        var vm = this;

        vm.sprint = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.sprint.id !== null) {
                Sprint.update(vm.sprint, onSaveSuccess, onSaveError);
            } else {
                Sprint.save(vm.sprint, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:sprintUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
