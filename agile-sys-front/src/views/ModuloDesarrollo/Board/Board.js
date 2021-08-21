import draggable from "vuedraggable";
import TaskCard from "@/components/TaskCard.vue";
export default {
  name: "App",
  components: {
    TaskCard,
    draggable,
  },
  data() {
    return {
      columns: [
        {
          title: "POR HACER",
          tasks: [
            {
              id: 1,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              type: "Feature Request",
            },
            {
              id: 2,
              title: "Provide documentation on integrations",
              date: "Sep 12",
            },
            {
              id: 3,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              type: "Design",
            },
            {
              id: 4,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              type: "Feature Request",
            },
            {
              id: 5,
              title: "Test checkout flow",
              date: "Sep 15",
              type: "QA",
            },
          ],
        },
        {
          title: "EN CURSO",
          tasks: [
            {
              id: 9,
              title: "Provide documentation on integrations",
              date: "Sep 12",
            },
            {
              id: 10,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              type: "Design",
            },
            {
              id: 11,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              type: "Feature Request",
            },
            {
              id: 12,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              type: "Design",
            },
            {
              id: 13,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              type: "Feature Request",
            },
          ],
        },
        {
          title: "FINALIZADO",
          tasks: [
            {
              id: 14,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              type: "Feature Request",
            },
            {
              id: 15,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              type: "Design",
            },
            {
              id: 16,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              type: "Feature Request",
            },
          ],
        },
      ],
    };
  },

  methods: {
    onDragEnd() {},
    onStart() {},
  },
};