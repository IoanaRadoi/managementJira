(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('efforttype', {
            parent: 'entity',
            url: '/efforttype',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Efforttypes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/efforttype/efforttypes.html',
                    controller: 'EfforttypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('efforttype-detail', {
            parent: 'entity',
            url: '/efforttype/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Efforttype'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/efforttype/efforttype-detail.html',
                    controller: 'EfforttypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Efforttype', function($stateParams, Efforttype) {
                    return Efforttype.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'efforttype',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('efforttype-detail.edit', {
            parent: 'efforttype-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/efforttype/efforttype-dialog.html',
                    controller: 'EfforttypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Efforttype', function(Efforttype) {
                            return Efforttype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('efforttype.new', {
            parent: 'efforttype',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/efforttype/efforttype-dialog.html',
                    controller: 'EfforttypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('efforttype', null, { reload: true });
                }, function() {
                    $state.go('efforttype');
                });
            }]
        })
        .state('efforttype.edit', {
            parent: 'efforttype',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/efforttype/efforttype-dialog.html',
                    controller: 'EfforttypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Efforttype', function(Efforttype) {
                            return Efforttype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('efforttype', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('efforttype.delete', {
            parent: 'efforttype',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/efforttype/efforttype-delete-dialog.html',
                    controller: 'EfforttypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Efforttype', function(Efforttype) {
                            return Efforttype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('efforttype', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
