<template>
  <div>
    <v-row class="mt-3">
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
        <v-card max-width="500" class="rounded-xl" elevation="3">
          <div class="white--text align-end" style="background: #1976d2">
            <v-card-title>Sistema de Gestion de Proyectos</v-card-title>
          </div>
          <v-card-text>
            <p>04-08-2021</p>
            <div class="text--primary text-body-1">
              Sistema para gestion de proyectos con metodologias agiles
            </div>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="primary accent-4" to="/backlog">
              IR AL PROYECTO
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>

      <v-col md="5" xl="4">
        <v-card max-width="600" height="300" class="rounded-xl">
          <div class="white--text align-end" style="background: #1976d2">
            <v-card-title>Mis asignaciones</v-card-title>
          </div>
          <v-card-text>
            <div class="text--primary"></div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-row v-else class="mt-12">
      <div class="text-h4">No tenes proyectos en curso</div>
    </v-row>

    <br />
    <br />
    <br />
    <v-row justify="center">
      <v-card width="100%" class="rounded-xl">
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

    <div class="btn-flotante">
      <v-btn color="primary">Crear Proyecto</v-btn>
    </div>

  </div>
</template>

<script src="./Home.js"></script>

<style src="./Home.css"></style>