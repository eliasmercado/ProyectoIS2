import 'devextreme/dist/css/dx.common.css';
import 'devextreme/dist/css/dx.light.css';
import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import Vuex from "vuex";
import axios from "axios";
import Vueaxios from "vue-axios";
import router from "@/router/index";
import store from "./store/index";

Vue.use(Vuex);
Vue.use(Vueaxios, axios);

const baseURL = "http://localhost:8180/agile-sys-web/api";

axios.defaults.baseURL = baseURL;

Vue.config.productionTip = false;

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
