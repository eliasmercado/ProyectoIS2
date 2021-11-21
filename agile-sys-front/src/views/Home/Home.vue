<template>
  <div class="div-1">
    <br />
    <br />
    <v-row justify="center">
      <span class="welcomeStyle">
        Bienvenido
        {{ $store.state.LoginStore.nombres }}
        {{ $store.state.LoginStore.apellidos }}
      </span>
    </v-row>
    <br />
    <br />
    <v-row
      justify="center"
      v-if="
        $store.state.LoginStore.idProyecto &&
        $store.state.LoginStore.idProyecto !== 0
      "
    >
      <v-col md="5" xl="4">
        <v-hover>
          <template v-slot="{ hover }">
            <v-card :elevation="hover ? 10 : 3" max-width="500">
              <div class="white--text align-end" style="background: #ff9800">
                <v-card-title>{{ nombreProyecto }}</v-card-title>
              </div>
              <v-card-text>
                <p>
                  {{ fechaInicioProyecto }}
                </p>
                <div class="text--primary text-body-1">
                  {{ descripcionProyecto }}
                </div>
              </v-card-text>
              <v-card-actions>
                <v-btn text color="primary accent-4" to="/desarrollo/backlog">
                  IR AL PROYECTO
                </v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-hover>
      </v-col>

      <v-col md="5" xl="4">
        <v-hover>
          <template v-slot="{ hover }">
            <v-card :elevation="hover ? 10 : 3" max-width="600" height="300">
              <div class="white--text align-end" style="background: #009688">
                <v-card-title>Mis asignaciones</v-card-title>
              </div>
              <v-virtual-scroll
                bench="0"
                :items="asignacionesEnCurso"
                height="231"
                item-height="64"
              >
                <template v-slot:default="{ item }">
                  <v-list-item :key="item.idHistoriaUsuario">
                    <v-list-item-avatar size="30">
                      <v-icon color="primary"> mdi-book </v-icon>
                    </v-list-item-avatar>

                    <v-list-item-content>
                      <v-list-item-title>
                        <strong>US-{{ item.idHistoriaUsuario }} -</strong>
                        {{ item.nombre }}
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>

                  <v-divider></v-divider>
                </template>
              </v-virtual-scroll>
            </v-card>
          </template>
        </v-hover>
      </v-col>
    </v-row>

    <v-row v-else justify="center">
      <div class="text-h4 font-weight-medium" style="color: #ffff">
        No tenes proyectos activos
      </div>
    </v-row>

    <br />
    <br />
    <br />
    <v-row justify="center">
      <v-card width="90%" class="rounded-xl">
        <v-tabs v-if="verTabs" v-model="tab" background-color="primary" dark>
          <v-tab v-for="item in items" :key="item.tab">
            {{ item.tab }}
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item>
            <v-card flat>
              <v-virtual-scroll
                bench="0"
                :items="proyectosTerminados"
                max-height="300"
                item-height="64"
              >
                <template v-slot:default="{ item }">
                  <v-list-item :key="item.idProyecto">
                    <v-list-item-content>
                      <v-list-item-title>
                        <strong>US-{{ item.nombre }} -</strong>
                        {{ item.descripcion }}
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>

                  <v-divider></v-divider>
                </template>
              </v-virtual-scroll>
            </v-card>
          </v-tab-item>

          <v-tab-item>
            <v-card flat>
              <v-virtual-scroll
                bench="0"
                :items="asignacionesTerminadas"
                max-height="300"
                item-height="64"
              >
                <template v-slot:default="{ item }">
                  <v-list-item :key="item.idHistoriaUsuario">
                    <v-list-item-avatar size="30">
                      <v-icon color="primary"> mdi-book </v-icon>
                    </v-list-item-avatar>

                    <v-list-item-content>
                      <v-list-item-title>
                        <strong>US-{{ item.idHistoriaUsuario }} -</strong>
                        {{ item.nombre }}
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>

                  <v-divider></v-divider>
                </template>
              </v-virtual-scroll>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </v-row>
  </div>
</template>

<script src="./Home.js"></script>

<style src="./Home.css"></style>