Ext.define('Monolith.view.main.LoginBlock', {
    extend: 'Ext.button.Button',
    xtype: 'loginblock',
    width: 220,
    margin: '10px 10px',
    listeners: {
        added: function(a, b, c) {
            Ext.asap(this.init, this);
        }

    },
    init: function() {
        this.changeState();
        Ext.appInstance.authChanged = this.changeState.bind(this);
    },
    openLoginWindow: function () {
        this.loginWindow = Ext.create('Monolith.view.user.LoginWindow');
    },
    openRegisterWindow: function () {
        alert("Not implemented yet");
    },
    openProfileWindow: function () {
        Ext.Ajax.request( {
            url: '/user'
        }).then(function (response, opts) {
            var obj = Ext.decode(response.responseText);
            console.dir(obj);
        });
    },
    doLogout: function () {
        Ext.appInstance.removeAuth();
        Ext.appInstance.authChanged();
        Ext.Msg.alert('Logout', 'You has been logged out.');
 },
    changeState: function () {
        var auth = Ext.appInstance.getAuth();
        Ext.appInstance.changeApplicationState();
        var items = auth ?
            [
                {iconCls: 'x-fa fa-times-circle', text: 'Log out', handler: this.doLogout},
                {iconCls: 'x-fa fa-user', text: 'Profile', handler: this.openProfileWindow}
            ]
            :
            [
                {iconCls: 'x-fa fa-play-circle', text: 'Log in', handler: this.openLoginWindow},
                {iconCls: 'x-fa fa-plus-circle', text: 'Register', handler: this.openRegisterWindow}
            ];

        this.setMenu({items: items});
        this.setText(auth ? auth.username : 'You are not logged in');
    },
});
