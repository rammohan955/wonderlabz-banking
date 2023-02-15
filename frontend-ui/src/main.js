import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './vuetify'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';

//axios
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.config.productionTip = false
//axios
Vue.use(VueAxios, axios)
//TODO configure url based on env
axios.defaults.baseURL = "http://localhost:8080/";
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';


new Vue({
  router,
  vuetify,
  render: function (h) { return h(App) }
}).$mount('#app')