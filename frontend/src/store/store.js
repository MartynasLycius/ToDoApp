import Vue from 'vue';
import Vuex from 'vuex'

Vue.use(Vuex);

export const store = new Vuex.Store({
    state: {
        component: {
            navBar: {
                menus: [
                    {id: 1, title: 'TODOs', icon: 'mdi-format-list-bulleted-square', route: '/'},
                    {id: 2, title: 'NEW TODO', icon: 'mdi-playlist-plus', route: '/add-item'},
                ]
            },
            dataTable : {
                headers: [
                    {text: 'Date', value: 'startDate', dataType: "Date", width: '20%'},
                    {text: 'Name', align: 'start', sortable: true, value: 'itemName', width: '25%'},
                    {text: 'Description', align: 'start', value: 'description', sortable: false, width: '40%', class:"text-center"},
                    {text: 'Is Done', align: 'center', value: 'isDone', sortable: true, width: '10%'},
                    {text: 'Actions', align: 'end', value: 'actions', sortable: false, width: '10%'},
                ]
            }
        },
        views : {
            validation: {
                rules: {
                    required: (v) => { return !!v || 'This field is required'},
                },
            },
        }
    },
    getters: {
        getMenus: state => {
            return state.component.navBar.menus;
        },
        getValidationRules: state => {
            return state.views.validation.rules;
        },
        getDataTableHeaders: state => {
            return state.component.dataTable.headers;
        }
    },

});
