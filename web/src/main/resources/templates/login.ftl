<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login page template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "common-header.ftl">
</head>
<body ng-app="Monolith" ng-controller="LoginCtrl">
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h1 class="page-header">Login page template</h1>
            <form style="margin-bottom: 30px" name="form" autocomplete="off" novalidate ng-submit="form.$valid && sendForm(auth)">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email"
                           class="form-control"
                           id="exampleInputEmail1"
                           placeholder="Email"
                           required
                           ng-model="auth.email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password"
                           class="form-control"
                           id="exampleInputPassword1"
                           placeholder="Password"
                           required
                           ng-model="auth.password">
                </div>
                <div>
                    <button type="submit" class="btn btn-default" style="margin-top:30px" ng-disabled="form.$invalid">
                        Submit
                    </button>
                    <a href="register">Register</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>