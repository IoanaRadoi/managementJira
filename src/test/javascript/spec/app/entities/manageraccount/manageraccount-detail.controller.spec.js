'use strict';

describe('Controller Tests', function() {

    describe('Manageraccount Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockManageraccount, MockProject;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockManageraccount = jasmine.createSpy('MockManageraccount');
            MockProject = jasmine.createSpy('MockProject');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Manageraccount': MockManageraccount,
                'Project': MockProject
            };
            createController = function() {
                $injector.get('$controller')("ManageraccountDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'managementJiraApp:manageraccountUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
