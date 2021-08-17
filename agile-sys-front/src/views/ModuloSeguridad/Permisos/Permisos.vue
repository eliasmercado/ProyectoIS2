<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="permisos"
      class="elevation-1"
      item-key="descripcion"
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-toolbar-title>Permisos</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on"
                >Agregar</v-btn
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
                      <v-col cols="12" sm="12" md="6">
                        <v-text-field
                          autocomplete="off"
                          v-model="editedItem.descripcion"
                          label="Descripción"
                          :rules="descripcionRules"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="12" md="6">
                        <v-select
                          :items="modulos"
                          v-model="editedItem.modulo"
                          label="Módulo"
                          item-text="nombreModulo"
                          item-value="idModulo"
                          return-object
                        ></v-select>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="save"
                    >Guardar</v-btn
                  >
                  <v-btn color="blue darken-1" text @click="close"
                    >Cancelar</v-btn
                  >
                </v-card-actions>
              </v-form>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>

      <template v-slot:[`item.actions`]="{ item }">
        <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>

        <v-dialog v-model="dialogEliminar" persistent width="560">
          <template v-slot:activator="{ on, attrs }">
            <v-icon
              small
              v-bind="attrs"
              v-on="on"
              @click="getDeleteItemIndex(item)"
            >
              mdi-delete
            </v-icon>
          </template>
          <v-card>
            <v-card-title class="headline"
              >Está seguro que desea eliminar este elemento?</v-card-title
            >
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="deleteItem"
                >Aceptar</v-btn
              >
              <v-btn color="blue darken-1" text @click="dialogEliminar = false"
                >Cancelar</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>
      </template>
    </v-data-table>
  </div>
</template>

<script src="./Permisos.js"></script>

<style src="./Permisos.css"></style>