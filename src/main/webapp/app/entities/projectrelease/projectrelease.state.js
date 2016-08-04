(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectrelease', {
            parent: 'entity',
            url: '/projectrelease',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Projectreleases'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectrelease/projectreleases.html',
                    controller: 'ProjectreleaseController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('projectrelease-detail', {
            parent: 'entity',
            url: '/projectrelease/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Projectrelease'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectrelease/projectrelease-detail.html',
                    controller: 'ProjectreleaseDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Projectrelease', function($stateParams, Projectrelease) {
                    return Projectrelease.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectrelease',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectrelease-detail.edit', {
            parent: 'projectrelease-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrelease/projectrelease-dialog.html',
                    controller: 'ProjectreleaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectrelease', function(Projectrelease) {
                            return Projectrelease.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectrelease.new', {
            parent: 'projectrelease',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrelease/projectrelease-dialog.html',
                    controller: 'ProjectreleaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectrelease', null, { reload: true });
                }, function() {
                    $state.go('projectrelease');
                });
            }]
        })
        .state('projectrelease.edit', {
            parent: 'projectrelease',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrelease/projectrelease-dialog.html',
                    controller: 'ProjectreleaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectrelease', function(Projectrelease) {
                            return Projectrelease.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectrelease', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectrelease.delete', {
            parent: 'projectrelease',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrelease/projectrelease-delete-dialog.html',
                    controller: 'ProjectreleaseDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectrelease', function(Projectrelease) {
                            return Projectrelease.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectrelease', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
