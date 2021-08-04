<template>
  <div>
    <v-app id="inspire">
      <v-app-bar :clipped-left="true" app class="appBar" dark>
        <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
          <span class="hidden-sm-and-down">Agile-Sys</span>
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
</style>