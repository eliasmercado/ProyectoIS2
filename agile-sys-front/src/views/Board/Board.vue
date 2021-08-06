<template>
  <div>
    <v-row class="flex justify-center">
      <div
        v-for="column in columns"
        :key="column.title"
        class="bg-gray rounded-lg px-3 py-3 column-width rounded mr-4"
      >
        <p class="text-gray font-weight-medium caption">{{ column.title }}</p>
        <!-- Draggable component comes from vuedraggable. It provides drag & drop functionality -->
        <draggable
          :list="column.tasks"
          :animation="200"
          ghost-class="ghost-card"
          group="tasks"
          class="cursorMove"
          @end="onDragEnd"
          @start="onStart"
        >
          <!-- Each element from here will be draggable and animated. Note :key is very important here to be unique both for draggable and animations to be smooth & consistent. -->
          <task-card
            v-for="task in column.tasks"
            :key="task.id"
            :task="task"
            class="mt-3"
          ></task-card>
          <!-- </transition-group> -->
        </draggable>
      </div>
    </v-row>
  </div>
</template>

<script src="./Board.js"></script>

<style scoped src="./Board.css"></style>
