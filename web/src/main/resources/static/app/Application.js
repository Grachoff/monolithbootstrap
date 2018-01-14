var app, viewport;
Ext.define('Monolith.Application', {
    extend: 'Ext.app.Application',
    name: 'Monolith',
    autoCreateViewport: 'Monolith.view.Main',
    requires: [
        'Ext.*',
        'Monolith.*'   // tell Cmd to include all app classes
    ],
    launch: function () {
        // Ext.Msg.alert('Monolith App', 'Welcome to Monolith Application.');
        app = Monolith.getApplication();
        var form = Ext.create('Monolith.view.main.Main');
        var form2 = Ext.create('Monolith.view.main.Second');
        var west=Ext.create('Ext.Panel', {
            title: 'Правая панель',
            width: 450,
            region: 'west',
            items: [form, form2]
        });
        Ext.Viewport.add(west);

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
