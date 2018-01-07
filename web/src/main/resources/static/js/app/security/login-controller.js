angularApp
.controller("LoginCtrl", function ($scope, $http) {
    $scope.auth = {};
    $scope.sendForm = function(auth) {
        $http({
            method: "POST",
            url: "/login",
            data: $.param(auth),
            headers: { "Content-Type" : "application/x-www-form-urlencoded" }

        }).then(
            function(data) {
                window.alert("Login succesful");
            },
            function(error) {
                window.alert("Login failed");
            }
        )
    }
})
