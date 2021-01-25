import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import RestClient from './utilities/rest.client';
import FeedBack from './utilities/feed.back';
import EventBus from './event.bus';
import {eventBusConstant} from './constant/event.bus.constant';
import {httpStatusCode} from './constant/http.status.code.constanst';
import validation from './utilities/validation';

const restClient = new RestClient(process.env.VUE_APP_BASE_URL);

Vue.prototype.$restClient = restClient;
Vue.prototype.$eventBus = EventBus;
Vue.prototype.$evenBusConstant = eventBusConstant;
Vue.prototype.$feedback = FeedBack;
Vue.prototype.$httpStatusCode = httpStatusCode;
Vue.prototype.$validation = validation;

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app');
