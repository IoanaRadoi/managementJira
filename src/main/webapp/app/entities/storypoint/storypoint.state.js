(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('storypoint', {
            parent: 'entity',
            url: '/storypoint',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Storypoints'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/storypoint/storypoints.html',
                    controller: 'StorypointController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('storypoint-detail', {
            parent: 'entity',
            url: '/storypoint/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Storypoint'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/storypoint/storypoint-detail.html',
                    controller: 'StorypointDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Storypoint', function($stateParams, Storypoint) {
                    return Storypoint.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'storypoint',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('storypoint-detail.edit', {
            parent: 'storypoint-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/storypoint/storypoint-dialog.html',
                    controller: 'StorypointDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Storypoint', function(Storypoint) {
                            return Storypoint.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('storypoint.new', {
            parent: 'storypoint',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/storypoint/storypoint-dialog.html',
                    controller: 'StorypointDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                storypoint: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('storypoint', null, { reload: true });
                }, function() {
                    $state.go('storypoint');
                });
            }]
        })
        .state('storypoint.edit', {
            parent: 'storypoint',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/storypoint/storypoint-dialog.html',
                    controller: 'StorypointDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Storypoint', function(Storypoint) {
                            return Storypoint.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('storypoint', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('storypoint.delete', {
            parent: 'storypoint',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/storypoint/storypoint-delete-dialog.html',
                    controller: 'StorypointDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Storypoint', function(Storypoint) {
                            return Storypoint.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('storypoint', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('storypoint.charts', {
                    parent: 'storypoint',
                    url: '/charts',
                    data: {
                        authorities: ['ROLE_USER']
                    },
                    onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                        $uibModal.open({
                            templateUrl: 'app/entities/storypoint/storypoint-charts.html',
                            controller: 'StorypointChartsController',
                            controllerAs: 'vm',
                            size: 'md',
                            resolve: {
                                entity: ['Storypoint', function(Storypoint) {
                                    return Storypoint.get({id : $stateParams.id}).$promise;
                                }]
                            }
                        }).result.then(function() {
                            $state.go('storypoint', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        });
                    }]
                });
    }

})();
