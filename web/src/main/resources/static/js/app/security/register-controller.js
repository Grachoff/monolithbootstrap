angularApp
.controller("RegisterCtrl", function ($scope, $http) {
    $scope.auth = {};
    $scope.sendForm = function(auth) {
        $http({
            method: "POST",
            url: "/register",
            data: $.param(auth),
            headers: { "Content-Type" : "application/x-www-form-urlencoded" }

        }).then(
            function(data) {
                window.alert("Registration succesful");
            },
            function(error) {
                window.alert("Registration failed");
            }
        )
    }
})