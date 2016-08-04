(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectreleasesprint', {
            parent: 'entity',
            url: '/projectreleasesprint',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Projectreleasesprints'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectreleasesprint/projectreleasesprints.html',
                    controller: 'ProjectreleasesprintController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('projectreleasesprint-detail', {
            parent: 'entity',
            url: '/projectreleasesprint/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Projectreleasesprint'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectreleasesprint/projectreleasesprint-detail.html',
                    controller: 'ProjectreleasesprintDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Projectreleasesprint', function($stateParams, Projectreleasesprint) {
                    return Projectreleasesprint.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectreleasesprint',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectreleasesprint-detail.edit', {
            parent: 'projectreleasesprint-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectreleasesprint/projectreleasesprint-dialog.html',
                    controller: 'ProjectreleasesprintDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectreleasesprint', function(Projectreleasesprint) {
                            return Projectreleasesprint.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectreleasesprint.new', {
            parent: 'projectreleasesprint',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectreleasesprint/projectreleasesprint-dialog.html',
                    controller: 'ProjectreleasesprintDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                capacity: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectreleasesprint', null, { reload: true });
                }, function() {
                    $state.go('projectreleasesprint');
                });
            }]
        })
        .state('projectreleasesprint.edit', {
            parent: 'projectreleasesprint',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectreleasesprint/projectreleasesprint-dialog.html',
                    controller: 'ProjectreleasesprintDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectreleasesprint', function(Projectreleasesprint) {
                            return Projectreleasesprint.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectreleasesprint', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectreleasesprint.delete', {
            parent: 'projectreleasesprint',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectreleasesprint/projectreleasesprint-delete-dialog.html',
                    controller: 'ProjectreleasesprintDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectreleasesprint', function(Projectreleasesprint) {
                            return Projectreleasesprint.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectreleasesprint', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
