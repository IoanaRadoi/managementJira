(function() {
    'use strict';

    angular
        .module('managementJiraApp')
        .controller('ProjectreleaseController', ProjectreleaseController);

    ProjectreleaseController.$inject = ['$scope', '$state', 'Projectrelease'];

    function ProjectreleaseController ($scope, $state, Projectrelease) {
        var vm = this;

        vm.projectreleases = [];

        loadAll();

        function loadAll() {
            Projectrelease.query(function(result) {
                vm.projectreleases = result;
            });
        }

       $scope.myJson = {
             type : "bar",
             title:{
               backgroundColor : "transparent",
               fontColor :"black",
               text : "Hello world"
             },
             backgroundColor : "white",
             series : [
               {
                 values : [1,2,3,4],
                 backgroundColor : "#4DC0CF"
               }
             ]
           };

//         $scope.addValues = function(){
//           var val = Math.floor((Math.random() * 10));
//           console.log(val);
//           $scope.myJson.series[0].values.push(val);
//         }
    }
})();
