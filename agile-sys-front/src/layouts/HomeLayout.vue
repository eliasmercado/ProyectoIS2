<template>
  <div id="app">
    <v-app id="inspire">
      <v-app id="inspire">
        <v-navigation-drawer v-model="drawer" app class="navBar" dark>
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

          <v-list shaped>
            <v-list-item to="/">
              <v-list-item-action>
                <v-icon>mdi-home</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title class="text-body-1 font-weight-medium">
                  Inicio
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <div v-for="item in items" :key="item.id">
              <v-subheader class="font-weight-bold">{{
                item.title
              }}</v-subheader>
              <template v-for="childItem in item.childItems">
                <v-list-item :key="childItem.text" link :to="childItem.to">
                  <v-list-item-action>
                    <v-icon>{{ childItem.icon }}</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title class="text-body-2 font-weight-medium">
                      {{ childItem.text }}
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </template>
              <v-divider></v-divider>
            </div>
          </v-list>
          <template v-slot:append>
            <div class="pa-2">
              <v-btn @click="logoutUser" class="primary" block>Logout</v-btn>
            </div>
          </template>
        </v-navigation-drawer>

        <v-toolbar flat color="appBarHomeLayout" dark>
          <v-app-bar-nav-icon
            @click.stop="drawer = !drawer"
          ></v-app-bar-nav-icon>

          <v-toolbar-title>
            <v-avatar tile> <img src="@/assets/logo-AS.png" /> </v-avatar>
            <span class="text-h5 textStyle font-weight-bold ml-2"
              >Agile Sys</span
            >
          </v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn @click="logoutUser" icon>
            <v-icon>mdi-logout</v-icon>
          </v-btn>
        </v-toolbar>

        <router-view></router-view>
      </v-app>
    </v-app>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data: () => ({
    dialog: false,
    drawer: null,
    modulos: [
      {
        id: 1,
        title: "SEGURIDAD",
        childItems: [
          { icon: "mdi-account", text: "Usuarios", to: "/seguridad/usuarios" },
          {
            icon: "mdi-account-key",
            text: "Permisos",
            to: "/seguridad/permisos",
          },
          { icon: "mdi-account-cog", text: "Roles", to: "/seguridad/roles" },
        ],
      },

      {
        id: 2,
        title: "PROYECTO",
        childItems: [
          { icon: "mdi-clipboard-edit", text: "Administrar", to: "/proyecto/administrar" },
        ],
      },

      {
        id: 3,
        title: "DESARROLLO",
        childItems: [
          { icon: "mdi-clipboard-text", text: "Backlog", to: "/backlog" },
          { icon: "mdi-view-week-outline", text: "Tablero", to: "/board" },
          { icon: "mdi-account-group", text: "Miembros", to: "/members" },
          { icon: "mdi-cog", text: "Settings", to: "/settings" },
          { icon: "mdi-help-circle", text: "Ayuda", to: "/help" },
        ],
      },
    ],

    items: [],
  }),

  methods: {
    ...mapActions(["logout", "getMenuPermiso"]),

    logoutUser() {
      this.logout();
    },

    async verificarPermisoModulos() {
      let idRol = this.$store.state.LoginStore.idRol;
      let modulosPermiso = [];

      await this.axios
        .get("/v1/modulo-usuario/" + idRol.toString())
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            modulosPermiso = response.data.data;
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });

      this.modulos.forEach((modulo) => {
        modulosPermiso.forEach((moduloPermiso) => {
          if (
            moduloPermiso.nombreModulo.toUpperCase() ==
            modulo.title.toUpperCase()
          ) {
            this.items.push(modulo);
          }
        });
      });
    },
  },

  mounted() {
    this.verificarPermisoModulos();
  },
};
</script>

<style lang="scss">
$nav-bar-color: #263238;
$app-bar-color: #1976d2;
.appBarHomeLayout {
  background: $app-bar-color !important;
  max-height: 10%;
}
.navBar {
  background-color: $nav-bar-color !important;
}
.textStyle {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
</style>