(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('YearDialogController', YearDialogController);

    YearDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Year'];

    function YearDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Year) {
        var vm = this;

        vm.year = entity;
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
            if (vm.year.id !== null) {
                Year.update(vm.year, onSaveSuccess, onSaveError);
            } else {
                Year.save(vm.year, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:yearUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
