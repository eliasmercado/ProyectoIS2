import { DxScrollView } from "devextreme-vue/scroll-view";
import { DxSortable } from "devextreme-vue/sortable";

export default {
  components: {
    DxScrollView,
    DxSortable,
  },
  data: () => ({
    comentario: "",
    dialog: false,
    historias: [],
    usuarios: [],
    statuses: ["TO DO", "DOING", "DONE"],
    statusesBD: [],
    usuariosMap: {},
    lists: [],
    indexAnterior: null,
    indexActual: null,
    historiaActual: {},
    sprint: [],
  }),

  methods: {
    async initializeUsuarios() {
      await this.axios
        .get("/v1/usuario/")
        .then((response) => (this.usuarios = response.data.data));

      this.usuarios.forEach((usuario) => {
        this.usuariosMap[usuario.idUsuario] =
          usuario.nombres + " " + usuario.apellidos;
      });
    },

    async initialize() {
      await this.axios
        .get("/v1/fase/")
        .then((response) => (this.statusesBD = response.data.data.sort()));

      await this.axios
        .get("/v1/sprint")
        .then(
          (response) =>
            (this.sprint = response.data.data.filter(
              (x) => x.idProyecto == this.$store.state.LoginStore.idProyecto
            ))
        );

      this.sprint = this.sprint.filter((x) => x.iniciado && !x.completado)[0];

      for (var k in this.sprint.historiasUsuario) {
        this.historias.push(this.sprint.historiasUsuario[k]);
      }

      this.actualizarKanban();
    },

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
      if (
        e.itemData.idUsuarioResponsable !=
        this.$store.state.LoginStore.idUsuario
      )
        return;

      this.indexAnterior = this.indexActual;
      this.indexActual = listIndex;
      e.fromData.splice(e.fromIndex, 1);
      e.toData.splice(e.toIndex, 0, e.itemData);
      this.historiaActual = e.itemData;
      this.dialog = true;
    },

    async actualizarHistoria() {
      let idHistoriaUsuario = this.historiaActual.idHistoriaUsuario;
      let idFase = this.obtenerIdFaseByFase(this.statuses[this.indexActual]);

      var data = {
        nombre: this.historiaActual.nombre,
        descripcion: this.historiaActual.descripcion,
        idFase: idFase,
        idSprint: this.historiaActual.idSprint,
        idUsuarioResponsable: this.historiaActual.idUsuarioResponsable,
      };

      await this.axios
        .put("/v1/historiaUsuario/" + idHistoriaUsuario.toString(), data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.dialog = false;
            this.comentario = "";
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    obtenerIdFaseByFase(fase) {
      return this.statusesBD.filter(
        (x) => x.fase.toUpperCase() == fase.toUpperCase()
      )[0].idFase;
    },

    revertirCambio() {
      /*       let item = this.lists[this.indexAnterior];
      console.log("item" + item);
      console.log("index actual" + this.indexActual);
      console.log("index anterior" + this.indexAnterior);
      this.lists[this.indexAnterior] = this.lists[this.indexActual];
      this.lists[this.indexActual] = item; */
    },

    getStatus(historia, estado) {
      let status = new Map();
      status.set("TO DO", "status-1");
      status.set("DOING", "status-2");
      status.set("DONE", "status-3");

      return status.get(estado.toUpperCase());
    },

    obtenerFaseByIdFase(idFase) {
      return this.statusesBD.filter((x) => x.idFase == idFase)[0].fase;
    },

    actualizarKanban() {
      this.statuses.forEach((status) => {
        this.lists.push(
          this.historias.filter(
            (historia) =>
              this.obtenerFaseByIdFase(historia.idFase).toUpperCase() ===
              status.toUpperCase()
          )
        );
      });
    },

    verDetallesHistoria(historia) {
      console.log(historia);
    }
  },

  mounted() {
    this.initializeUsuarios();
    this.initialize();
  },
};
