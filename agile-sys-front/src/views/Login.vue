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
                <v-toolbar color="transparent" flat>
                  <v-toolbar-title class="font-weight-bold text-h5"
                    >Iniciar sesión</v-toolbar-title
                  >
                </v-toolbar>
                <v-card-text>
                  <v-form>
                    <v-text-field
                      label="Usuario"
                      v-model="user"
                      prepend-icon="mdi-account"
                      type="text"
                      autocomplete="off"
                    ></v-text-field>

                    <v-text-field
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
                  <v-btn @click="login" color="primary">Iniciar sesion</v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
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
  }),

  methods: {
    ...mapActions(["authUser"]),
    login() {
      var credentials = {
        user: this.user,
        password: this.password,
      };

      this.authUser(credentials);
    },
  },
};
</script>

<style>
</style>