(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ReleasejiraDialogController', ReleasejiraDialogController);

    ReleasejiraDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Releasejira'];

    function ReleasejiraDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Releasejira) {
        var vm = this;

        vm.releasejira = entity;
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
            if (vm.releasejira.id !== null) {
                Releasejira.update(vm.releasejira, onSaveSuccess, onSaveError);
            } else {
                Releasejira.save(vm.releasejira, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:releasejiraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
