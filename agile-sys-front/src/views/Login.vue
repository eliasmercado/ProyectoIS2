<template>
  <div>
    <v-app>
      <div class="ripple-background">
        <div class="circle xxlarge shade1"></div>
        <div class="circle xlarge shade2"></div>
        <div class="circle large shade3"></div>
        <div class="circle mediun shade4"></div>
        <div class="circle small shade5"></div>
      </div>
      <v-main>
        <v-container class="fill-height" fluid>
          <v-row align="center" justify="center">
            <v-col cols="12" sm="8" md="3">
              <v-card
                class="elevation-8 rounded-xl"
                height="500"
                color="rgb(255, 255, 255, 0.1)"
                dark
              >
                <br />
                <br />
                <br />
                <v-toolbar color="transparent" flat>
                  <v-row justify="center">
                    <v-toolbar-title class="font-weight-bold text-h4"
                      ><div class="fuenteTitulo">
                        Bienvenido
                      </div></v-toolbar-title
                    >
                  </v-row>
                </v-toolbar>
                <v-card-text>
                  <v-form>
                    <v-text-field
                      @keyup.enter="login"
                      label="Usuario"
                      v-model="user"
                      prepend-icon="mdi-account"
                      type="text"
                      autocomplete="off"
                    ></v-text-field>

                    <v-text-field
                      @keyup.enter="login"
                      id="password"
                      v-model="password"
                      label="Contraseña"
                      prepend-icon="mdi-lock"
                      type="password"
                      autocomplete="off"
                    ></v-text-field>
                  </v-form>
                </v-card-text>
                <v-card-actions class="justify-center">
                  <v-btn outlined @click="login" class="white--text"
                    >Iniciar sesion</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>

        <v-snackbar
          color="error"
          v-model="$store.state.LoginStore.error"
          :timeout="timeout"
        >
          {{ $store.state.LoginStore.errorMessage }}
        </v-snackbar>
      </v-main>
    </v-app>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data: () => ({
    user: null,
    password: null,
    response: null,
    timeout: 2000,
  }),

  methods: {
    ...mapActions(["authUser"]),
    login() {
      var credentials = {
        usuario: this.user,
        password: this.password,
      };

      this.authUser(credentials);
    },
  },
};
</script>

<style>
.fuenteTitulo {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif !important;
}
</style>