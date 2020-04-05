import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import { store } from "./store/store";
import DatetimePicker from 'vuetify-datetime-picker';

Vue.config.productionTip = false;
Vue.use(DatetimePicker);


new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app')
