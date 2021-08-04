<template>
  <div>
    <v-card class="mx-auto" max-width="500" outlined elevation="1">
      <v-list-item three-line>
        <v-list-item-content>
          <p class="text-body-1">{{ task.title }}</p>
        </v-list-item-content>

        <v-list-item-avatar tile size="20" color="grey"></v-list-item-avatar>
      </v-list-item>

      <v-card-actions>
        <v-icon>mdi-alpha-e-circle</v-icon>
        <v-spacer></v-spacer>
        <v-chip>asds</v-chip>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
import Badge from "./Badge.vue";
export default {
  components: {
    Badge,
  },
  props: {
    task: {
      type: Object,
      default: () => ({}),
    },
  },
  computed: {
    badgeColor() {
      const mappings = {
        Design: "purple",
        "Feature Request": "teal",
        Backend: "blue",
        QA: "green",
        default: "teal",
      };
      return mappings[this.task.type] || mappings.default;
    },
  },
};
</script>
