import Vue from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router'

//axios
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.config.productionTip = false
//axios
Vue.use(VueAxios, axios)

//TODO configure url based on env
axios.defaults.baseURL = "http://localhost:8080/api/v1"
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')