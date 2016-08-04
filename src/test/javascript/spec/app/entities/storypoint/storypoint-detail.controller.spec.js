'use strict';

describe('Controller Tests', function() {

    describe('Storypoint Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockStorypoint, MockProjectreleasesprint, MockEfforttype, MockItem;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockStorypoint = jasmine.createSpy('MockStorypoint');
            MockProjectreleasesprint = jasmine.createSpy('MockProjectreleasesprint');
            MockEfforttype = jasmine.createSpy('MockEfforttype');
            MockItem = jasmine.createSpy('MockItem');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Storypoint': MockStorypoint,
                'Projectreleasesprint': MockProjectreleasesprint,
                'Efforttype': MockEfforttype,
                'Item': MockItem
            };
            createController = function() {
                $injector.get('$controller')("StorypointDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'managementJiraApp:storypointUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
