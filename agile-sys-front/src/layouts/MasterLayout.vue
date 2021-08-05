<template>
  <div>
    <v-app id="inspire">
      <v-navigation-drawer v-model="drawer" clipped app>
        <v-list>
          <v-list-item>
            <v-list-item-avatar>
              <v-img src="../assets/user-icon.png"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title class="title">
                {{ $store.state.LoginStore.nombres.split(" ")[0] }}
                {{ $store.state.LoginStore.apellidos.split(" ")[0] }}
              </v-list-item-title>
              <v-list-item-subtitle>{{
                $store.state.LoginStore.rol
              }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list dense>
          <template v-for="item in items">
            <v-list-item :key="item.text" link :to="item.to">
              <v-list-item-action>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>
                  {{ item.text }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-list>

        <template v-slot:append>
          <div class="pa-12">
            <v-btn class="primary" to="/">Volver al inicio</v-btn>
          </div>
        </template>
      </v-navigation-drawer>

      <v-app-bar :clipped-left="true" app class="appBar" dark>
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
          <v-avatar tile>
            <img src="@/assets/logo-AS.png" />
          </v-avatar>
          <span class="text-h5 textStyle font-weight-bold ml-2">Agile Sys</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>

        <v-btn @click="logoutUser" icon large>
          <v-icon>mdi-logout</v-icon>
        </v-btn>
      </v-app-bar>

      <v-container>
        <router-view></router-view>
      </v-container>
    </v-app>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data: () => ({
    dialog: false,
    drawer: null,
    items: [
      { icon: "mdi-clipboard-text", text: "Backlog", to: "/backlog" },
      { icon: "mdi-view-week-outline", text: "Tablero", to: "/board" },
      { icon: "mdi-account-group", text: "Miembros", to: "/members" },
      { icon: "mdi-cog", text: "Settings", to: "/settings" },
      { icon: "mdi-help-circle", text: "Ayuda", to: "/help" },
    ],
  }),

  methods: {
    ...mapActions(["logout"]),

    logoutUser() {
      this.logout();
    },
  },
};
</script>

<style>
.appBar {
  background: linear-gradient(
    90deg,
    rgba(2, 0, 36, 1) 0%,
    rgba(9, 9, 121, 1) 0%,
    rgba(1, 147, 241, 1) 0%,
    rgba(6, 79, 167, 1) 0%,
    rgba(1, 147, 241, 1) 100%,
    rgba(0, 212, 255, 1) 100%
  ) !important;
}

.textStyle {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
</style>