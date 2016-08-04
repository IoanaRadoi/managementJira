(function() {
    'use strict';
    angular
        .module('managementJiraApp')
        .factory('Releasejira', Releasejira);

    Releasejira.$inject = ['$resource'];

    function Releasejira ($resource) {
        var resourceUrl =  'api/releasejiras/:id';

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
