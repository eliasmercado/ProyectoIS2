<template>
  <div>
    <br />
    <div v-if="!this.sprint">
      <div class="text-h5 text-center font-weight-medium">
        No hay Sprint activo
      </div>
    </div>
    <div v-else>
      <v-row justify="center">
        <div class="text-h5 text-center font-weight-medium">
          {{ sprint.nombre }}
        </div>
      </v-row>
      <v-row justify="center">
        <div id="kanban">
          <DxScrollView
            class="scrollable-board"
            direction="horizontal"
            show-scrollbar="always"
          >
            <DxSortable
              class="sortable-lists"
              item-orientation="horizontal"
              handle=".list-title"
              @reorder="onListReorder"
            >
              <div
                v-for="(list, listIndex) in lists"
                :key="statuses[listIndex]"
                class="list"
              >
                <div class="list-title dx-theme-text-color">
                  {{ statuses[listIndex] }}
                </div>
                <DxScrollView class="scrollable-list" show-scrollbar="always">
                  <DxSortable
                    :data="list"
                    class="sortable-cards"
                    group="tasksGroup"
                    @drag-start="onTaskDragStart($event, listIndex)"
                    @add="onTaskDrop($event, listIndex)"
                  >
                    <div
                      v-for="historia in list"
                      :key="historia.id"
                      class="
                        card
                        dx-card dx-theme-text-color dx-theme-background-color
                      "
                    >
                      <div
                        :class="[
                          'card-status',
                          getStatus(historia, statuses[listIndex]),
                        ]"
                      />
                      <div class="card-subject">{{ historia.nombre }}</div>
                      <div class="card-assignee">
                        {{ usuariosMap[historia.idUsuarioResponsable] }}
                      </div>
                    </div>
                  </DxSortable>
                </DxScrollView>
              </div>
            </DxSortable>
          </DxScrollView>
        </div>
      </v-row>
    </div>
    <v-dialog v-model="dialog" persistent width="560">
      <v-card>
        <v-card-title class="headline">Agregar Comentario</v-card-title>
        <br />
        <v-card-text>
          <v-textarea solo v-model="comentario"></v-textarea>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="actualizarHistoria"
            >Aceptar</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script src="./Board.js"></script>
<style src="./Board.css">
</style>
