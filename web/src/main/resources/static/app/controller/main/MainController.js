Ext.define('Monolith.controller.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',
    init: function(view) {
        Ext.appInstance.mainController = this;
        this.currentView = view;
        this.redrawMenu();
    },
    onItemSelected: function (sender, record) {
        console.log(sender);
        console.log(record);
        // Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    },
    redrawMenu: function () {
        this.currentView.items.items.forEach(this.changeVisibility.bind(this));
    },
    changeVisibility: function (menuItem) {
        var auth = Ext.appInstance.getAuth();
        menuItem.tab.setVisible(this.checkAvailability(menuItem.tabName, auth));


    },
    checkAvailability: function (name, auth) {

        if (this.menuAvailability[name].includes('ALL')) return true;
        if (this.menuAvailability[name].includes('AUTH') && auth) {
            return true;
        }
        if (this.menuAvailability[name].includes('NONAUTH') && auth) {
            return false;
        }
        l(auth);
        if (auth) {
            for (var i=0; i< auth.authorities.length; i++) {
                if (this.menuAvailability[name].includes(auth.authorities[i].authority)) return true;
            }
        }

        return false;
    },
    menuAvailability: {
        home: ['ALL'],
        users: ['ROLE_ADMIN'],
        groups: ['ROLE_ADMIN'],
        settings: ['AUTH']

    }

});
