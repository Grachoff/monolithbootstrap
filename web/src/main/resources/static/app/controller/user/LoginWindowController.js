Ext.define('Monolith.controller.user.LoginWindowController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',
    stores: ['Monolith.store.user.Auth'],
    init: function() {
        this.username = this.getView().down('[name=username]');
        this.password = this.getView().down('[name=password]');
    },
    onLoginClick: function() {
        this.doLogin(this.username.getValue(), this.password.getValue());
    },
    loginSuccess: function (response, opts) {
        var obj = Ext.decode(response.responseText);
        Ext.appInstance.setAuth(
            {
                username: this.username.getValue(),
                jwtToken: obj.token,
                authorities: obj.authorities
            });
        Ext.appInstance.authChanged();
        this.getView().enable();
        this.getView().destroy();
    },
    loginFailure: function (response, opts) {
        Ext.Msg.alert('Fail', 'Bad credentials!');
        this.getView().enable();
    },
    doLogin: function (username, password) {
        this.getView().disable();
        Ext.Ajax.request( {
            url: '/auth',
            method: 'POST',
            jsonData: {username: username, password: password}
        }).then(this.loginSuccess.bind(this), this.loginFailure.bind(this));
    }
});