'use strict';

describe('Controller Tests', function() {

    describe('Projectrelease Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockProjectrelease, MockProject, MockReleasejira, MockYear;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockProjectrelease = jasmine.createSpy('MockProjectrelease');
            MockProject = jasmine.createSpy('MockProject');
            MockReleasejira = jasmine.createSpy('MockReleasejira');
            MockYear = jasmine.createSpy('MockYear');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Projectrelease': MockProjectrelease,
                'Project': MockProject,
                'Releasejira': MockReleasejira,
                'Year': MockYear
            };
            createController = function() {
                $injector.get('$controller')("ProjectreleaseDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'managementJiraApp:projectreleaseUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
