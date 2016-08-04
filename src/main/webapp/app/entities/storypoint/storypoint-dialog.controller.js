(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('StorypointDialogController', StorypointDialogController);

    StorypointDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Storypoint', 'Projectreleasesprint', 'Efforttype', 'Item'];

    function StorypointDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Storypoint, Projectreleasesprint, Efforttype, Item) {
        var vm = this;

        vm.storypoint = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projectreleasesprints = Projectreleasesprint.query();
        vm.efforttypes = Efforttype.query();
        vm.items = Item.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.storypoint.id !== null) {
                Storypoint.update(vm.storypoint, onSaveSuccess, onSaveError);
            } else {
                Storypoint.save(vm.storypoint, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('managementJiraApp:storypointUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
