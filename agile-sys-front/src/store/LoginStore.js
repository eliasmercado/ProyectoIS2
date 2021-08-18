import axios from "axios";
import router from "@/router/index";

let apiUrl = "http://localhost:8180/agile-sys-web";

const state = {
  idUsuario: null,
  usuario: "",
  nombres: "",
  apellidos: "",
  email: "",
  idRol: null,
  rol: "",
  idProyecto: null,
  error: false,
  errorMessage: "",
};

const mutations = {
  AUTH_SUCCESS(state, payload) {
    state.idUsuario = payload.idUsuario;
    state.usuario = payload.usuario;
    state.nombres = payload.nombres;
    state.apellidos = payload.apellidos;
    state.email = payload.email;
    state.idRol = payload.idRol;
    state.rol = payload.rol;
    state.idProyecto = payload.idProyecto;
  },

  AUTH_ERROR(state, message) {
    state.error = true;
    state.errorMessage = message;
  },

  LOGOUT(state) {
    state.idUsuario = null;
    state.usuario = "";
    state.nombres = "";
    state.apellidos = "";
    state.email = "";
    state.idRol = null;
    state.rol = "";
    state.idProyecto = null;
    state.error = false;
    state.errorMessage = "";
  },
};

const actions = {
  authUser({ commit }, credentials) {
    return new Promise((resolve, reject) => {
      axios
        .post(apiUrl + "/api/v1/login", credentials)
        .then((response) => {
          if ("error" in response.data) {
            commit("AUTH_ERROR", response.data.error.message);
            reject();
          } else {
            commit("AUTH_SUCCESS", response.data.data);
            router.push("/").catch();
            resolve();
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    });
  },

  logout({ commit }) {
    commit("LOGOUT");
    router.push("/login").catch();
  },
};

export default {
  state,
  mutations,
  actions,
};
