Ext.define('Monolith.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-main',

    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',

        'Monolith.controller.main.MainController',
        'Monolith.model.main.MainModel',
        'Monolith.view.main.List',
        'Monolith.view.main.LoginBlock'
    ],

    controller: 'main',
    viewModel: 'main',

    ui: 'navigation',

    tabBarHeaderPosition: 0,
    titleRotation: 0,
    tabRotation: 0,
    header: {items: [{id: "LoginButton", xtype: 'loginblock'}]},
    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        }
    },
    responsiveConfig: {
        tall: {
            headerPosition: 'top'
        },
        wide: {
            headerPosition: 'left'
        }
    },

    defaults: {
        bodyPadding: 20,
        tabConfig: {
            plugins: 'responsive',
            responsiveConfig: {
                wide: {
                    iconAlign: 'left',
                    textAlign: 'left',
                    width: 240
                },
                tall: {
                    iconAlign: 'top',
                    textAlign: 'center',
                    width: 120
                }
            }
        }
    },
    items: [{
        title: 'Home',
        tabName: 'home',
        iconCls: 'fa-home',
        // The following grid shares a store with the classic version's grid as well!
        bind: {
            html: '{mainText}'
        }
    }, {
        title: 'Users',
        tabName: 'users',
        iconCls: 'fa-user',
        items: [{
            xtype: 'mainlist'
        }]
    }, {
        title: 'Groups',
        tabName: 'groups',
        iconCls: 'fa-users',
        bind: {
            html: '{loremIpsum}'
        }
    }, {
        title: 'Settings',
        tabName: 'settings',
        iconCls: 'fa-cog',
        bind: {
            html: '{loremIpsum}'
        }
    }]


});
