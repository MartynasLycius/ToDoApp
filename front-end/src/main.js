import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import RestClient from './utilities/rest.client';
import EventBus from './event.bus';

const restClient = new RestClient(process.env.VUE_APP_BASE_URL);

Vue.prototype.$restClient = restClient;
Vue.prototype.$eventBus = EventBus;

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app');
