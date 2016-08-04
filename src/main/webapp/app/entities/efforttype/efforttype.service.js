(function() {
    'use strict';
    angular
        .module('managementJiraApp')
        .factory('Efforttype', Efforttype);

    Efforttype.$inject = ['$resource'];

    function Efforttype ($resource) {
        var resourceUrl =  'api/efforttypes/:id';

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
