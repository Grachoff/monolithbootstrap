Ext.define('Monolith.store.user.Auth', {
    extend: 'Ext.data.Store',
    alias: 'store.auth',
    model: 'Monolith.model.user.Auth',
    autoSync: true,
    autoLoad: true
});
