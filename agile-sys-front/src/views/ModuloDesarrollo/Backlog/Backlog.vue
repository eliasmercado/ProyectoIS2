<template>
  <div>
    <div class="text-h4 text-center font-weight-medium">Backlog</div>
    <br />

    <v-dialog v-model="dialog" max-width="500px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on"
          >Crear historia</v-btn
        >
      </template>
      <v-card>
        <v-form ref="form" v-model="validForm" lazy-validation>
          <v-card-title>
            <span class="headline">{{ formTitle }}</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    autocomplete="off"
                    v-model="editedItem.nombre"
                    label="Nombre"
                    :rules="nameRules"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="12" md="12">
                  <v-text-field
                    autocomplete="off"
                    v-model="editedItem.descripcion"
                    label="Descripción"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="save">Guardar</v-btn>
            <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <v-card max-width="auto" class="mt-2">
      <v-list v-for="historia in historiasUsuario" :key="historia.id">
        <v-list-item>
          <v-list-item-avatar size="30">
            <v-icon color="primary"> mdi-book </v-icon>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title
              ><span class="font-weight-medium">US-{{ historia.id }} - </span
              ><span> {{ historia.nombre }}</span></v-list-item-title
            >
          </v-list-item-content>

          <v-list-item-action>
            <v-row>
              <v-btn icon>
                <v-icon color="primary" @click="editItem(historia)"
                  >mdi-pencil</v-icon
                >
              </v-btn>
              <v-btn icon>
                <v-icon color="primary" @click="getDeleteItemIndex(historia)">mdi-delete</v-icon>
              </v-btn>
            </v-row>
          </v-list-item-action>
        </v-list-item>
        <v-divider></v-divider>
      </v-list>
    </v-card>

    <v-dialog v-model="dialogEliminar" persistent width="560">
      <v-card>
        <v-card-title class="headline"
          >Está seguro que desea eliminar este elemento?</v-card-title
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="deleteItem">Aceptar</v-btn>
          <v-btn color="blue darken-1" text @click="dialogEliminar = false"
            >Cancelar</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script src="./Backlog.js"></script>

<style>
</style>