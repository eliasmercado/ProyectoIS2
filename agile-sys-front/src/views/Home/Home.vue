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
                  {{ new Date().toISOString(fechaInicioProyecto).slice(0, 10) }}
                </p>
                <div class="text--primary text-body-1">
                  {{ descripcionProyecto }}
                </div>
              </v-card-text>
              <v-card-actions>
                <v-btn text color="primary accent-4" to="/backlog">
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
              <v-card-text>
                <div class="text--primary"></div>
              </v-card-text>
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
        <v-tabs v-model="tab" background-color="primary" dark>
          <v-tab v-for="item in items" :key="item.tab">
            {{ item.tab }}
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item v-for="item in items" :key="item.tab">
            <v-card flat>
              <v-card-text>{{ item.content }}</v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </v-row>
  </div>
</template>

<script src="./Home.js"></script>

<style src="./Home.css"></style>