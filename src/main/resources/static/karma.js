// name, [dependencies]
var karmaApp = angular.module("karmaApp", []);

karmaApp.controller("KarmaCtrl", function KarmaCtrl($scope, $http) {
    var max_rows = 500;
    $scope.highestFirst = 'true';
    $http.get("service/top/" + max_rows).success(function(data) {
        $scope.karma = data;
    });
});
