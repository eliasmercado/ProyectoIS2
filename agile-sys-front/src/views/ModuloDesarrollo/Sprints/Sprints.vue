<template>
  <div>
    <v-tabs color="primary" right>
      <v-tab>Activos</v-tab>
      <v-tab>Completados</v-tab>
      <v-tab-item>
        <v-row>
          <v-col>
            <v-select
              v-model="sprintActual"
              :items="sprints"
              item-value="id"
              item-text="nombre"
              label="Selecciona el sprint"
              return-object
            ></v-select>
          </v-col>
          <v-col>
            <v-dialog v-model="dialog" max-width="500px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="primary"
                  dark
                  class="mt-5"
                  v-bind="attrs"
                  v-on="on"
                  small
                  >Crear Sprint</v-btn
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
                            :disabled="sprintActual ? sprintActual.iniciado ? true : false : false"
                          >
                            <template v-slot:activator="{ on, attrs }">
                              <v-text-field
                                v-model="computedDateFormattedFecInicio"
                                label="Fecha de Inicio"
                                persistent-hint
                                prepend-icon="mdi-calendar"
                                readonly
                                v-bind="attrs"
                                v-on="on"
                              ></v-text-field>
                            </template>
                            <v-date-picker
                              v-model="editedItem.fechaInicio"
                              no-title
                              @input="menu = false"
                            ></v-date-picker>
                          </v-menu>
                        </v-col>
                        <v-col cols="12" sm="12" md="12">
                          <v-menu
                            v-model="menu1"
                            :close-on-content-click="false"
                            transition="scale-transition"
                            offset-y
                            max-width="290px"
                            min-width="auto"
                            :disabled="true"
                          >
                            <template v-slot:activator="{ on, attrs }">
                              <v-text-field
                                v-model="computedDateFormattedFecFin"
                                label="Fecha de Fin"
                                persistent-hint
                                prepend-icon="mdi-calendar"
                                readonly
                                v-bind="attrs"
                                v-on="on"
                              ></v-text-field>
                            </template>
                            <v-date-picker
                              v-model="editedItem.fechaFin"
                              no-title
                              @input="menu1 = false"
                            ></v-date-picker>
                          </v-menu>
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
          </v-col>
          <v-col>
            <v-btn color="primary" dark class="mt-5" small
              >Finalizar Proyecto</v-btn
            >
          </v-col>
        </v-row>
        <br />
        <br />
        <div id="list-demo">
          <div class="widget-container">
            <div>
              <v-row class="font-weight-bold ml-2">Backlog</v-row>
              <br />
              <DxList
                :data-source="backlog"
                :repaint-changes-only="true"
                key-expr="id"
                displayExpr="descripcion"
                height="600"
                :focusStateEnabled="false"
                :activeStateEnabled="false"
              >
                <DxItemDragging
                  data="backlog"
                  :allow-reordering="true"
                  :on-drag-start="onDragStart"
                  :on-add="onAddBacklog"
                  :on-remove="onRemoveBacklog"
                  group="tasks"
                />
              </DxList>
            </div>

            <div>
              <v-row v-if="sprintActual">
                <div class="font-weight-bold ml-5">
                  {{ sprintActual.nombre }}
                </div>
                <v-spacer></v-spacer>
                <v-btn
                  v-if="!validacionCompletar"
                  :disabled="validacionIniciar == false"
                  class="mr-2"
                  color="primary"
                  x-small
                  @click="iniciarSprint"
                  >Iniciar Sprint</v-btn
                >
                <v-btn
                  @click="completarSprint"
                  v-if="validacionCompletar"
                  class="mr-2"
                  color="primary"
                  x-small
                  >Completar Sprint</v-btn
                >
                <v-btn
                  class="mr-5"
                  color="primary"
                  x-small
                  @click="editItem(sprintActual)"
                  >Editar</v-btn
                >
              </v-row>
              <br />
              <DxList
                v-if="sprintActual"
                :data-source="historiasActual"
                :repaint-changes-only="true"
                key-expr="id"
                displayExpr="descripcion"
                height="600"
                :focusStateEnabled="false"
                :activeStateEnabled="false"
              >
                <DxItemDragging
                  data="historiasActual"
                  :allow-reordering="true"
                  :on-drag-start="onDragStart"
                  :on-add="onAdd"
                  :on-remove="onRemove"
                  group="tasks"
                />
              </DxList>
            </div>
          </div>
        </div>
      </v-tab-item>

      <v-tab-item>
        <v-expansion-panels focusable>
          <v-expansion-panel v-for="(item, i) in sprintsCompletados" :key="i">
            <v-expansion-panel-header>{{
              item.nombre
            }}</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-list
                v-for="historia in item.historiasUsuario"
                :key="historia.idHistoriaUsuario"
              >
                <v-list-item>
                  <v-list-item-avatar size="30">
                    <v-icon color="primary"> mdi-book </v-icon>
                  </v-list-item-avatar>

                  <v-list-item-content>
                    <v-list-item-title
                      ><span class="font-weight-medium"
                        >US-{{ historia.idHistoriaUsuario }} - </span
                      ><span> {{ historia.nombre }}</span></v-list-item-title
                    >
                  </v-list-item-content>
                  <!-- 
                  <v-list-item-action>
                    <v-row>
                      <v-btn icon>
                        <v-icon color="primary" @click="editItem(historia)"
                          >mdi-pencil</v-icon
                        >
                      </v-btn>
                      <v-btn icon>
                        <v-icon
                          color="primary"
                          @click="getDeleteItemIndex(historia)"
                          >mdi-delete</v-icon
                        >
                      </v-btn>
                    </v-row>
                  </v-list-item-action> -->
                </v-list-item>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-tab-item>
    </v-tabs>

    <v-dialog v-model="dialogMiembros" persistent width="300">
      <v-card>
        <v-card-title class="headline">Agregar Responsable</v-card-title>
        <br />
        <v-card-text>
          <v-select
            v-model="idUsuarioResponsable"
            :items="usuariosMiembros"
            :item-text="(item) => `${item.nombres} ${item.apellidos}`"
            item-value="idUsuario"
          ></v-select>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="agregarMiembros"
            >Aceptar</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script src="./Sprints.js"></script>

<style>
.widget-container {
  display: flex;
  justify-content: center;
}

.widget-container > * {
  height: 400px;
  width: 40%;
  padding: 10px;
}

.dx-scrollview-content {
  min-height: 380px;
}
</style>
