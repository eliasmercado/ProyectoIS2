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
                <v-list-item-title class="title"
                  >Elías Mercado</v-list-item-title
                >
                <v-list-item-subtitle>Development Team</v-list-item-subtitle>
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
        </v-navigation-drawer>

        <v-app-bar :clipped-left="true" app class="appBar" dark>
          <v-app-bar-nav-icon
            @click.stop="drawer = !drawer"
          ></v-app-bar-nav-icon>
          <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
            <span class="hidden-sm-and-down">Agile-Sys</span>
          </v-toolbar-title>
          <v-spacer></v-spacer>

          <v-btn icon large to="./login">
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
};
</script>

<style>
.appBar {
  background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 0%, rgba(1,147,241,1) 0%, rgba(6,79,167,1) 0%, rgba(1,147,241,1) 100%, rgba(0,212,255,1) 100%) !important;
}
</style>