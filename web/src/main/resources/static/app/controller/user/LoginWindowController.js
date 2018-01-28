Ext.define('Monolith.controller.user.LoginWindowController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',
    stores: ['Monolith.store.user.Auth'],
    init: function() {
        this.authStore = Ext.getStore('Monolith.store.user.Auth');

        console.log(this.authStore);
    },
    onLoginClick: function() {
        var user = Ext.create('Monolith.model.user.Auth', {
            username: '1',
            jwtToken: '2'
        });
        this.authStore.add(user);
        this.authStore.sync();

        // Remove Login Window
        this.getView().destroy();

        // Add the main view to the viewport
        Ext.create({
            xtype: 'app-main'
        });

    }
});