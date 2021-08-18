<template>
  <div>
    <v-data-table :headers="headers" :items="users" class="elevation-1">
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-toolbar-title>Usuarios</v-toolbar-title>
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
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          autocomplete="off"
                          v-model="editedItem.nombres"
                          label="Nombres"
                          :rules="nameRules"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          autocomplete="off"
                          v-model="editedItem.apellidos"
                          label="Apellidos"
                          :rules="lastNameRules"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          autocomplete="off"
                          v-model="editedItem.usuario"
                          label="Usuario"
                          :rules="userRules"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="6">
                        <v-text-field
                          autocomplete="off"
                          v-model="editedItem.email"
                          label="Email"
                          :rules="emailRules"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          autocomplete="off"
                          v-model="editedItem.telefono"
                          label="Teléfono"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12" sm="6" md="7">
                        <v-select
                          :items="roles"
                          v-model="editedItem.rol"
                          label="Rol"
                          item-text="descripcion"
                          item-value="idRol"
                          return-object
                          clearable
                        >
                        </v-select>
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
        <v-icon small @click="getDeleteItemIndex(item)"> mdi-delete </v-icon>
      </template>
    </v-data-table>

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

<script src="./Usuarios.js"></script>

<style src="./Usuarios.css"></style>