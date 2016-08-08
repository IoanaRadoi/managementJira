(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
                .state('piechart', {
                    parent: 'admin',
                    url: '/piechart',
                    data: {
                        authorities: ['ROLE_USER']

                    },
                    views: {
                        'content@': {
                            templateUrl: 'app/admin/piechart/piechart.html',
                            controller: 'PieChartController',
                            controllerAs: 'vm'
                        }
                    },
                    resolve: {
                    }
                })
    }

})();
