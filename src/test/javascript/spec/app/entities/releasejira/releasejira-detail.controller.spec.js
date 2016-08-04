'use strict';

describe('Controller Tests', function() {

    describe('Releasejira Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockReleasejira;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockReleasejira = jasmine.createSpy('MockReleasejira');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Releasejira': MockReleasejira
            };
            createController = function() {
                $injector.get('$controller')("ReleasejiraDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'managementJiraApp:releasejiraUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
