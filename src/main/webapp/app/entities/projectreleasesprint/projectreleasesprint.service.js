(function() {
    'use strict';
    angular
        .module('managementJiraApp')
        .factory('Projectreleasesprint', Projectreleasesprint);

    Projectreleasesprint.$inject = ['$resource'];

    function Projectreleasesprint ($resource) {
        var resourceUrl =  'api/projectreleasesprints/:id';

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
