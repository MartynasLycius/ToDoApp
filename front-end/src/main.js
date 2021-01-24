import Vue from 'vue'
import VueFilterDateFormat from 'vue-filter-date-format';
import App from './App.vue'
import vuetify from './plugins/vuetify';
import RestClient from './utilities/rest.client';

const restClient = new RestClient(process.env.BASE_URL);

Vue.use(VueFilterDateFormat);
Vue.config.productionTip = false;
Vue.prototype.$restClient = restClient;

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app');
