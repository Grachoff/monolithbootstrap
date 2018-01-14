Ext.define('Monolith.view.main.Main', {
    extend: 'Ext.form.Panel',
    // xtype: 'layout-form',

    fullscreen: true,
    items: [
        {
            xtype: 'textfield',
            name: 'name',
            label: 'Name1'
        },
        {
            xtype: 'emailfield',
            name: 'email',
            label: 'Email1'
        },
        {
            xtype: 'passwordfield',
            name: 'password',
            label: 'Password'
        }
    ]
});