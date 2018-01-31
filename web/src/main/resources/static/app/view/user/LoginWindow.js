Ext.define('Monolith.view.user.LoginWindow', {
    extend: 'Ext.window.Window',
    xtype: 'loginwindow',

    requires: [
        'Monolith.controller.user.LoginWindowController',
        'Ext.form.Panel'
    ],

    controller: 'login',
    bodyPadding: 10,
    title: 'Login Window',
    closable: true,
    autoShow: true,
    modal : true,

    items: {
        xtype: 'form',
        reference: 'form',
        items: [{
            xtype: 'textfield',
            name: 'username',
            fieldLabel: 'Username',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'password',
            inputType: 'password',
            fieldLabel: 'Password',
            allowBlank: false
        }, {
            xtype: 'displayfield',
            hideEmptyLabel: false,
            value: "Username or password can't be blank"
        }],
        buttons: [{
            text: 'Login',
            formBind: true,
            iconCls: 'x-fa fa-play-circle',
            listeners: {
                click: 'onLoginClick'
            }
        }]
    }
});