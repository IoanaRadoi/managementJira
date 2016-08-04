'use strict';

describe('Controller Tests', function() {

    describe('Projectreleasesprint Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockProjectreleasesprint, MockProjectrelease, MockSprint;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockProjectreleasesprint = jasmine.createSpy('MockProjectreleasesprint');
            MockProjectrelease = jasmine.createSpy('MockProjectrelease');
            MockSprint = jasmine.createSpy('MockSprint');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Projectreleasesprint': MockProjectreleasesprint,
                'Projectrelease': MockProjectrelease,
                'Sprint': MockSprint
            };
            createController = function() {
                $injector.get('$controller')("ProjectreleasesprintDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'managementJiraApp:projectreleasesprintUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
