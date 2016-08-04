(function() {
    'use strict';
    angular
        .module('managementJiraApp')
        .factory('Manageraccount', Manageraccount);

    Manageraccount.$inject = ['$resource'];

    function Manageraccount ($resource) {
        var resourceUrl =  'api/manageraccounts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
