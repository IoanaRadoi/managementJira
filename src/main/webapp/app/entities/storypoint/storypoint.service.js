(function() {
    'use strict';
    angular
        .module('managementJiraApp')
        .factory('Storypoint', Storypoint);

    Storypoint.$inject = ['$resource'];

    function Storypoint ($resource) {
        var resourceUrl =  'api/storypoints/:id';

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
