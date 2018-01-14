<script>
    var AppProperties = {
        name: 'MonolithBootstrapApplication',
        profiles: '${activeprofiles}',
        version: '${version}',
        username: '${username}',
        roles: [${roles}]
    };
    var metas = document.getElementsByTagName('meta');

    for (var i=0; i<metas.length; i++) {
        if (metas[i].getAttribute("name")) AppProperties[metas[i].getAttribute("name")] = metas[i].getAttribute("content");
    }
</script>

<#if usecompressedresources == "true">

<link rel="stylesheet" href="wro/all.css?version=${version}"/>
<script type="text/javascript" src="wro/all.js?version=${version}"></script>

<#else>

<link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
<link rel="stylesheet" href="css/app/security/loginform.css"/>
<script type="text/javascript" src="js/libs/jquery.min.js"></script>
<script type="text/javascript" src="js/libs/angular.js"></script>
<script type="text/javascript" src="js/libs/bootstrap/bootstrap.bundle.js"></script>

<script type="text/javascript" src="js/app/main.js"></script>
<script type="text/javascript" src="js/app/security/login-controller.js"></script>
<script type="text/javascript" src="js/app/security/register-controller.js"></script>
<script type="text/javascript" src="js/app/security/recaptcha-directive.js"></script>

</#if>