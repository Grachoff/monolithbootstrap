Ext.define('Monolith.view.main.LoginBlock', {
    extend: 'Ext.button.Button',
    xtype: 'loginblock',
    width: 220,
    margin: '10px 10px',
    listeners: {
        added: function(a, b, c) {
            this.changeState(false);
            this.authStore = Ext.getStore('Monolith.store.user.Auth');
        }

    },
    openLoginWindow: function () {
        this.loginWindow = Ext.create('Monolith.view.user.LoginWindow');
    },
    openRegisterWindow: function () {
        alert("Not implemented yet");
    },
    openProfileWindow: function () {
        alert("Not implemented yet");
    },
    doLogout: function () {
        alert("Not implemented yet");
    },
    changeState: function (isLoggedIn) {
        var items = isLoggedIn ?
            [
                {iconCls: 'x-fa fa-times-circle', text: 'Log out', handler: this.doLogout},
                {iconCls: 'x-fa fa-address-card', text: 'Profile', handler: this.openProfileWindow}
            ]
            :
            [
                {iconCls: 'x-fa fa-play-circle', text: 'Log in', handler: this.openLoginWindow},
                {iconCls: 'x-fa fa-plus-circle', text: 'Register', handler: this.openRegisterWindow}
            ];

        this.setMenu({items: items});
        this.setText('You are not logged in');
    },
});
