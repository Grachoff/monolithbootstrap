
Ext.define('Monolith.view.main.List', {
    extend: 'Ext.grid.Panel',
    xtype: 'mainlist',

    requires: [
        'Monolith.store.Personnel'
    ],

    title: 'Personnel',

    store: {
        type: 'personnel'
    },
    scrollable: 'vertical',
    columns: [
        { text: 'Name',  dataIndex: 'name', flex: 1 },
        { text: 'Email', dataIndex: 'email', flex: 2 },
        { text: 'Phone', dataIndex: 'phone', flex: 2 },
        { text: 'Phone2', dataIndex: 'phone2', flex: 2 },
        { text: 'Phone3', dataIndex: 'phone3', flex: 2 },
        { text: 'Phone4', dataIndex: 'phone4', flex: 2 },
        { text: 'Phone5', dataIndex: 'phone5', flex: 2 },
    ],

    selModel: 'checkboxmodel',

    listeners: {
        select: 'onItemSelected'
    },
    height: 800,
    width: 1400
});
