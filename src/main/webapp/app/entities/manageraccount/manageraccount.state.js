(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('manageraccount', {
            parent: 'entity',
            url: '/manageraccount?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Manageraccounts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manageraccount/manageraccounts.html',
                    controller: 'ManageraccountController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('manageraccount-detail', {
            parent: 'entity',
            url: '/manageraccount/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Manageraccount'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manageraccount/manageraccount-detail.html',
                    controller: 'ManageraccountDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Manageraccount', function($stateParams, Manageraccount) {
                    return Manageraccount.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'manageraccount',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('manageraccount-detail.edit', {
            parent: 'manageraccount-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manageraccount/manageraccount-dialog.html',
                    controller: 'ManageraccountDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manageraccount', function(Manageraccount) {
                            return Manageraccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manageraccount.new', {
            parent: 'manageraccount',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manageraccount/manageraccount-dialog.html',
                    controller: 'ManageraccountDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                password: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('manageraccount', null, { reload: true });
                }, function() {
                    $state.go('manageraccount');
                });
            }]
        })
        .state('manageraccount.edit', {
            parent: 'manageraccount',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manageraccount/manageraccount-dialog.html',
                    controller: 'ManageraccountDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manageraccount', function(Manageraccount) {
                            return Manageraccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manageraccount', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manageraccount.delete', {
            parent: 'manageraccount',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manageraccount/manageraccount-delete-dialog.html',
                    controller: 'ManageraccountDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Manageraccount', function(Manageraccount) {
                            return Manageraccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manageraccount', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
