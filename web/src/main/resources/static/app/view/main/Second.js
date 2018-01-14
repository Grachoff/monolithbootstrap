Ext.define('Monolith.view.main.Second', {
    extend: 'Ext.form.Panel',
    // xtype: 'layout-form',

    fullscreen: true,
    items: [
        {
            xtype: 'textfield',
            name: 'name',
            label: 'Name2'
        },
        {
            xtype: 'emailfield',
            name: 'email',
            label: 'Email2'
        },
        {
            xtype: 'passwordfield',
            name: 'password',
            label: 'Password'
        }
    ]
});