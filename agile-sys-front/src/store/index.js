import Vue from "vue"; // This module's Vue is not the same instance as that referenced in main.js
import Vuex from "vuex";
import LoginStore from "./LoginStore.js";
import createPersistedState from "vuex-persistedstate";

// Required before creating new store instance
Vue.use(Vuex);
const store = new Vuex.Store({
  state: {},
  getter: {},
  mutations: {},
  actions: {},
  modules: {
    LoginStore: LoginStore,
  },
  plugins: [createPersistedState(
    {
      paths: ['LoginStore'],
    }
  )],
});
export default store;
