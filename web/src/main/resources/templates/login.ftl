<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login page template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "common-header.ftl">
</head>
<body>
<div class="container loginform-container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <h1 class="page-header">Login page template</h1>
                <#if logout>
                    <div class="alert alert-success" role="alert">
                        You have been logged out succesfully.
                    </div>
                </#if>
                <#if error>
                    <div class="alert alert-danger" role="alert">
                        Invalid credentials.
                    </div>
                </#if>

                <form class="loginform-form" method="post" name="form" autocomplete="off">
                    <div class="form-group">
                        <label for="InputUserName">User name</label>
                        <input type="text"
                               name="username"
                               class="form-control"
                               id="InputUserName"
                               placeholder="User name"
                               required">
                    </div>
                    <div class="form-group">
                        <label for="InputPassword">Password</label>
                        <input type="password"
                               name="password"
                               class="form-control"
                               id="InputPassword"
                               placeholder="Password"
                               required>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary loginform-submit">
                            Submit
                        </button>
                        <a href="register">Register</a>
                    </div>
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>