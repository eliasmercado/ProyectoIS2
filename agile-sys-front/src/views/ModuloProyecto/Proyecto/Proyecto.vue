<template>
  <div>
    <v-data-table :headers="headers" :items="projects" class="elevation-1">
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-toolbar-title>Proyectos</v-toolbar-title>
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
                          :rules="descripcionRules"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="12" md="12">
                        <v-menu
                          v-model="menu"
                          :close-on-content-click="false"
                          transition="scale-transition"
                          offset-y
                          max-width="290px"
                          min-width="auto"
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                              v-model="computedDateFormatted"
                              label="Fecha de Inicio"
                              persistent-hint
                              prepend-icon="mdi-calendar"
                              readonly
                              v-bind="attrs"
                              v-on="on"
                            ></v-text-field>
                          </template>
                          <v-date-picker
                            v-model="date"
                            no-title
                            @input="menu = false"
                          ></v-date-picker>
                        </v-menu>
                      </v-col>

                      <v-col cols="12" sm="12" md="12">
                        <v-combobox
                          v-model="editedItem.usuarios"
                          :items="itemsMiembros"
                          label="Agregar Miembros"
                          multiple
                          chips
                          :item-text="
                            (item) => `${item.nombres} ${item.apellidos}`
                          "
                          item-value="idUsuario"
                        >
                          <template v-slot:selection="data">
                            <v-chip
                              :key="JSON.stringify(data.item.idUsuario)"
                              v-bind="data.attrs"
                              :input-value="data.selected"
                              :disabled="data.disabled"
                              @click:close="
                                data.parent.selectItem(data.item.nombres)
                              "
                            >
                              <v-avatar
                                class="accent white--text"
                                left
                                v-text="
                                  data.item.nombres.slice(0, 1).toUpperCase()
                                "
                              ></v-avatar>
                              {{ data.item.nombres }}
                            </v-chip>
                          </template>
                        </v-combobox>
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
      <template v-slot:[`item.usuarios`]="{ item }">
        <span
          class="ml-1"
          v-for="usuario in item.usuarios"
          :key="usuario.idUsuario"
        >
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-chip v-bind="attrs" v-on="on">
                <v-avatar
                  class="accent white--text"
                  left
                  v-text="usuario.nombres.slice(0, 1).toUpperCase()"
                ></v-avatar>
                {{ usuario.nombres.split(" ")[0] }}
              </v-chip>
            </template>
            <span>{{ usuario.nombres }} {{ usuario.apellidos }}</span>
          </v-tooltip>
        </span>
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

<script src="./Proyecto.js"></script>
