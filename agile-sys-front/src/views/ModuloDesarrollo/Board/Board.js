import { DxScrollView } from "devextreme-vue/scroll-view";
import { DxSortable } from "devextreme-vue/sortable";

export default {
  components: {
    DxScrollView,
    DxSortable,
  },
  data: () => ({
    dialog: false,
    historias: [
      {
        id: 1,
        idUsuarioAsignado: 1,
        nombre: "Historia 1",
        fase: "TO DO",
      },
      {
        id: 2,
        idUsuarioAsignado: 1,
        nombre: "Historia 2",
        fase: "TO DO",
      },      
      
      {
        id: 3,
        idUsuarioAsignado: 1,
        nombre: "Historia 3",
        fase: "TO DO",
      },
      {
        id: 4,
        idUsuarioAsignado: 1,
        nombre: "Historia 4",
        fase: "TO DO",
      },
      {
        id: 5,
        idUsuarioAsignado: 1,
        nombre: "Historia 5",
        fase: "DOING",
      },
      {
        id: 6,
        idUsuarioAsignado: 1,
        nombre: "Historia 6",
        fase: "DONE",
      },
    ],
    usuarios: [
      {
        idUsuario: 1,
        nombre: "Mario",
        apellido: "Villalba",
      },
    ],

    usuariosMap: {},
    lists: [],
    statuses: ["TO DO", "DOING", "DONE"],
    indexAnterior: null,
    indexActual: null,
    historiaActual: {},
  }),

  methods: {
    onListReorder(e) {
      const list = this.lists.splice(e.fromIndex, 1)[0];
      this.lists.splice(e.toIndex, 0, list);

      const status = this.statuses.splice(e.fromIndex, 1)[0];
      this.statuses.splice(e.toIndex, 0, status);
    },
    onTaskDragStart(e, listIndex) {
      this.indexActual = listIndex;
      e.itemData = e.fromData[e.fromIndex];
    },
    onTaskDrop(e, listIndex) {
      this.indexAnterior = this.indexActual;
      this.indexActual = listIndex;
      e.fromData.splice(e.fromIndex, 1);
      e.toData.splice(e.toIndex, 0, e.itemData);
      this.historiaActual = e.itemData;
      //this.dialog = true;
    },
    actualizarHistoria() {
      console.log(this.historiaActual);
      if (this.indexActual % 2 == 0) {
        this.revertirCambio();
      }
      this.dialog = false;
    },
    revertirCambio() {
      let item = this.lists[this.indexAnterior];
      this.lists[this.indexAnterior] = this.lists[this.indexActual];
      this.lists[this.indexActual] = item;
    },

    getStatus(historia, estado) {
      let status = new Map();
      status.set("TO DO", "status-1");
      status.set("DOING", "status-2");
      status.set("DONE", "status-3");

      return status.get(estado.toUpperCase());
    },

    actualizarKanban() {
      this.statuses.forEach((status) => {
        this.lists.push(
          this.historias.filter(
            (historia) => historia.fase.toUpperCase() === status.toUpperCase()
          )
        );
      });
    },
  },

  mounted() {
    this.usuarios.forEach((usuario) => {
      this.usuariosMap[usuario.idUsuario] = usuario.nombre + " " + usuario.apellido;
    });
    this.actualizarKanban();
  },
};
