(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('EfforttypeDialogController', EfforttypeDialogController);

    EfforttypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Efforttype'];

    function EfforttypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Efforttype) {
        var vm = this;

        vm.efforttype = entity;
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
            if (vm.efforttype.id !== null) {
                Efforttype.update(vm.efforttype, onSaveSuccess, onSaveError);
            } else {
                Efforttype.save(vm.efforttype, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:efforttypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
