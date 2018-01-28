Ext.define('Monolith.model.user.Auth', {
    fields: ['username', 'jwtToken'],
    extend: 'Ext.data.Model',
    idProperty: 'username',
    proxy: {
        type: 'localstorage',
        id  : 'auth'
    }
});