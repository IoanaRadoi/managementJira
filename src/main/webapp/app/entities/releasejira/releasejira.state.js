(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('releasejira', {
            parent: 'entity',
            url: '/releasejira',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Releasejiras'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/releasejira/releasejiras.html',
                    controller: 'ReleasejiraController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('releasejira-detail', {
            parent: 'entity',
            url: '/releasejira/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Releasejira'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/releasejira/releasejira-detail.html',
                    controller: 'ReleasejiraDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Releasejira', function($stateParams, Releasejira) {
                    return Releasejira.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'releasejira',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('releasejira-detail.edit', {
            parent: 'releasejira-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/releasejira/releasejira-dialog.html',
                    controller: 'ReleasejiraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Releasejira', function(Releasejira) {
                            return Releasejira.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('releasejira.new', {
            parent: 'releasejira',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/releasejira/releasejira-dialog.html',
                    controller: 'ReleasejiraDialogController',
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
                    $state.go('releasejira', null, { reload: true });
                }, function() {
                    $state.go('releasejira');
                });
            }]
        })
        .state('releasejira.edit', {
            parent: 'releasejira',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/releasejira/releasejira-dialog.html',
                    controller: 'ReleasejiraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Releasejira', function(Releasejira) {
                            return Releasejira.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('releasejira', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('releasejira.delete', {
            parent: 'releasejira',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/releasejira/releasejira-delete-dialog.html',
                    controller: 'ReleasejiraDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Releasejira', function(Releasejira) {
                            return Releasejira.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('releasejira', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
