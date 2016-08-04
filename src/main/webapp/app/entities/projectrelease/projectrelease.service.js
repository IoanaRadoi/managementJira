(function() {
    'use strict';
    angular
        .module('managementJiraApp')
        .factory('Projectrelease', Projectrelease);

    Projectrelease.$inject = ['$resource'];

    function Projectrelease ($resource) {
        var resourceUrl =  'api/projectreleases/:id';

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
