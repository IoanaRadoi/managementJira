(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
                .state('statisticscharts', {
                    parent: 'admin',
                    url: '/statisticscharts',
                    data: {
                        authorities: ['ROLE_USER']

                    },
                    views: {
                        'content@': {
                            templateUrl: 'app/admin/statisticscharts/statisticscharts.html',
                            controller: 'StatisticsChartController',
                            controllerAs: 'vm'
                        }
                    },
                    resolve: {
                    }
                })
    }

})();
