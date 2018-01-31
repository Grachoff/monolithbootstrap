/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('Monolith.Application', {
    extend: 'Ext.app.Application',

    name: 'Monolith',

    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },
    init: function () {
        Ext.appInstance = this;
    },
    launch: function () {
    },
    setAuth: function(auth) {
        localStorage.setItem('auth', auth ? JSON.stringify(auth) : null);
        this.changeApplicationState();
    },
    removeAuth: function () {
        localStorage.removeItem('auth');
        this.changeApplicationState();
    },
    getAuth: function() {
        var auth = localStorage.getItem('auth');
        return auth ? JSON.parse(auth) : null;
    },
    changeApplicationState: function () {
        var headers = {};
        var auth = this.getAuth();
        if (auth) headers["Authorization"] = "Bearer " + auth.jwtToken;
        Ext.Ajax.setDefaultHeaders(headers);
        Ext.appInstance.mainController.redrawMenu();
    },
    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
