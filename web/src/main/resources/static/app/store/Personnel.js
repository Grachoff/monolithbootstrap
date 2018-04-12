function getItems() {
    var itemsInit = [
        { name: 'Jean Luc', email: "jeanluc.picard@enterprise.com", phone: "555-111-1111", phone2: "555-111-1111", phone3: "555-111-1111", phone4: "555-111-1111", phone5: "555-111-1111"   },
        { name: 'Worf',     email: "worf.moghsson@enterprise.com",  phone: "555-222-2222", phone2: "555-111-1111", phone3: "555-111-1111", phone4: "555-111-1111", phone5: "555-111-1111" },
        { name: 'Deanna',   email: "deanna.troi@enterprise.com",    phone: "555-333-3333", phone2: "555-111-1111", phone3: "555-111-1111", phone4: "555-111-1111", phone5: "555-111-1111" },
        { name: 'Data',     email: "mr.data@enterprise.com",        phone: "555-444-4444", phone2: "555-111-1111", phone3: "555-111-1111", phone4: "555-111-1111", phone5: "555-111-1111" },
        { name: 'Huyata',     email: "mr.data@enterprise.com",        phone: "555-444-666", phone2: "555-111-1111", phone3: "555-111-1111", phone4: "555-111-1111", phone5: "555-111-1111" }
    ];
    var items = [], counter = 0;
    for (var j = 0; j < 100000; j++) {
        for (var i = 0; i < itemsInit.length; i++) {
            var tmpObj = Ext.clone(itemsInit[i]);
            tmpObj.name += counter;
            items.push(tmpObj);

            counter++;
        }
    }
    console.log(items);
    return items;
}

Ext.define('Monolith.store.Personnel', {
    extend: 'Ext.data.Store',

    alias: 'store.personnel',
    // autoLoad: true,
    fields: [
        'id', 'username', 'email', 'phone'
    ],

    data: {items: getItems()},
    // proxy: {
    //     type: 'rest',
    //     headers: Ext.Ajax.getDefaultHeaders(),
    //     url : 'users',
    //     reader: {
    //         type: 'json',
    //         root: 'userList',
    //         totalProperty: 'total'
    //     }
    // }
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            rootProperty: 'items'
        }
    }
});
