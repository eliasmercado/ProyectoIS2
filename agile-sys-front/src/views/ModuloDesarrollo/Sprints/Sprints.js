import DxList, { DxItemDragging } from "devextreme-vue/list";

export default {
  components: {
    DxList,
    DxItemDragging,
  },
  data: () => ({
    dialog: false,
    dialogMiembros: false,
    idUsuarioResponsable: null,
    eventoActual: null,
    usuariosMiembros: [],
    validForm: true,
    nameRules: [(v) => !!v || "Nombre es requerido"],
    descripcionRules: [(v) => !!v || "Descripción es requerido"],
    editedIndex: -1,
    menu: false,
    menu1: false,
    date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    backlog: [],
    sprints: [],
    sprintsCompletados: [],
    sprintActual: null,
    historiasActual: [],
    validacionIniciar: null,
    validacionCompletar: null,
    editedItem: {
      id: 0,
      nombre: "",
      descripcion: "",
      fechaInicio: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      fechaFin: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      idProyecto: "",
      idUsuarioResponsable: null,
    },
    defaultItem: {
      id: 0,
      nombre: "",
      descripcion: "",
      fechaInicio: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      fechaFin: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      idProyecto: "",
      idUsuarioResponsable: null,
    },
  }),

  created() {
    this.initialize();
  },

  methods: {
    async initialize() {
      this.sprintActuaL = null;
      await this.axios
        .get("/v1/historiaUsuario/" + this.$store.state.LoginStore.idProyecto)
        .then((response) => {
          if ("error" in response.data) {
            this.backlog = [];
          } else {
            this.backlog = response.data.data.filter((x) => x.idSprint == 0);
          }
        });

      await this.axios
        .get("/v1/sprint")
        .then((response) => (this.sprints = response.data.data));

      this.sprintsCompletados = this.sprints.filter(
        (x) =>
          x.idProyecto == this.$store.state.LoginStore.idProyecto &&
          x.completado
      );

      this.sprints = this.sprints.filter(
        (x) =>
          x.idProyecto == this.$store.state.LoginStore.idProyecto &&
          !x.completado
      );

      await this.axios
        .get("/v1/usuario")
        .then(
          (response) =>
            (this.usuariosMiembros = response.data.data.filter(
              (x) => x.idProyecto == this.$store.state.LoginStore.idProyecto
            ))
        );
    },

    editItem(item) {
      try {
        this.dialog = true;
        this.editedIndex = this.sprints.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.$refs.form.resetValidation();
      } catch (e) {}
    },

    onDragStart(e) {
      e.itemData = this[e.fromData][e.fromIndex];
    },

    onAdd(e) {
      this.eventoActual = e;
      this.dialogMiembros = true;
    },

    agregarMiembros() {
      if (this.idUsuarioResponsable == null) {
        alert("Por favor agregue el responsable de la historia.");
        return;
      }
      this.sprintActual.idUsuarioResponsable = this.idUsuarioResponsable;
      this.actualizarHistoria(this.eventoActual);
    },

    async actualizarHistoria(e) {
      let historiaUsuario = e.itemData;
      let idSprint = this.sprintActual.idSprint;

      var body = {
        nombre: historiaUsuario.nombre,
        descripcion: historiaUsuario.descripcion,
        idSprint: idSprint,
        idFase: historiaUsuario.idFase,
        idUsuarioResponsable: this.sprintActual.idUsuarioResponsable,
      };

      await this.axios
        .put(
          "/v1/historiaUsuario/" + historiaUsuario.idHistoriaUsuario.toString(),
          body
        )
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            const data = [...this[e.toData]];
            data.splice(e.toIndex, 0, e.itemData);
            this[e.toData] = data;
            this.sprintActual.historiasUsuario = data;
            this.dialogMiembros = false;
            this.idUsuarioResponsable = null;
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    onRemove(e) {
      const data = [...this[e.fromData]];
      data.splice(e.fromIndex, 1);
      this[e.fromData] = data;
      this.sprintActual.historiasUsuario = data.length == 0 ? [] : data;
    },

    async onAddBacklog(e) {
      let historiaUsuario = e.itemData;

      var body = {
        nombre: historiaUsuario.nombre,
        descripcion: historiaUsuario.descripcion,
        idSprint: null,
        idFase: historiaUsuario.idFase,
      };

      await this.axios
        .put(
          "/v1/historiaUsuario/" + historiaUsuario.idHistoriaUsuario.toString(),
          body
        )
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            const data = [...this[e.toData]];
            data.splice(e.toIndex, 0, e.itemData);
            this[e.toData] = data;
            this.backlog = data;
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    onRemoveBacklog(e) {
      const data = [...this[e.fromData]];
      data.splice(e.fromIndex, 1);
      this[e.fromData] = data;
      this.backlog = data.length == 0 ? [] : data;
    },

    existeSprintIniciado() {
      let fechaActual = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);

      for (var index in this.sprints) {
        if (this.sprints[index].iniciado && !this.sprints[index].completado)
          return true;
      }
      return false;
    },

    existeSprintIniciadoBySprint() {
      if (this.sprintActual.iniciado && !this.sprintActual.completado)
        return true;
      return false;
    },

    sumarDias(fecha, dias) {
      fecha.setDate(fecha.getDate() + dias);
      return fecha.toISOString().substr(0, 10);
    },

    formatDate(date) {
      if (!date) return null;

      date = new Date(date).toISOString().substring(0, 10);

      const [year, month, day] = date.split("-");
      return `${day}/${month}/${year}`;
    },

    formatDateSprint(date) {
      if (!date) return null;
      date = new Date(date).toISOString().substring(0, 10);

      const [year, month, day] = date.split("-");
      return `${day}/${month}/${year}`;
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
      this.$refs.form.resetValidation();
    },

    async save() {
      if (!this.$refs.form.validate()) return;
      if (this.editedIndex > -1) {
        //Edita el proyecto
        await this.saveEditItem();
      } else {
        //Inserta un proyecto
        await this.saveNewItem();
      }
      this.close();
    },

    async saveEditItem() {
      let idSprint = this.editedItem.idSprint;

      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
        fechaInicio: this.formatDate(this.editedItem.fechaInicio),
        fechaFin: this.formatDate(
          this.sumarDias(new Date(this.editedItem.fechaInicio), 14)
        ),
        iniciado: this.editedItem.iniciado,
        completado: this.editedItem.completado,
      };

      await this.axios
        .put("/v1/sprint/" + idSprint, data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            Object.assign(this.sprints[this.editedIndex], this.editedItem);
            this.sprintActual = this.editedItem;
            this.initialize();
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async saveNewItem() {
      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
        fechaInicio: this.formatDate(this.editedItem.fechaInicio),
        fechaFin: this.formatDate(
          this.sumarDias(new Date(this.editedItem.fechaInicio), 14)
        ),
        idProyecto: this.$store.state.LoginStore.idProyecto,
      };

      await this.axios
        .post("/v1/sprint/", data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.initialize();
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async iniciarSprint() {
      if (this.sprintActual.historiasUsuario.length == 0) {
        alert("No se puede iniciar un sprint sin historias de usuario.");
        return;
      }

      let idSprint = this.sprintActual.idSprint;

      let fechaActual = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);

      var data = {
        nombre: this.sprintActual.nombre,
        descripcion: this.sprintActual.descripcion,
        fechaInicio: this.formatDate(fechaActual),
        fechaFin: this.sumarDias(new Date(fechaActual), 14),
        iniciado: true,
        completado: this.sprintActual.completado,
      };

      await this.axios
        .put("/v1/sprint/" + idSprint, data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.sprintActual.fechaInicio = fechaActual;
            this.sprintActual.iniciado = true;
            this.recargarValidaciones();
            this.initialize();
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    verificarFaseHistorias(historias) {
      for (var index in historias) {
        if (historias[index].idFase != 3) return true;
      }
      return false;
    },

    async completarSprint() {
      if (this.verificarFaseHistorias(this.sprintActual.historiasUsuario)) {
        alert(
          "Se deben finalizar todas las historias de usuario para completar el sprint."
        );
        return;
      }

      let idSprint = this.sprintActual.idSprint;

      var data = {
        nombre: this.sprintActual.nombre,
        descripcion: this.sprintActual.descripcion,
        fechaInicio: this.formatDate(this.sprintActual.fechaInicio),
        fechaFin: this.formatDate(
          this.sumarDias(new Date(this.editedItem.fechaInicio), 14)
        ),
        iniciado: this.sprintActual.iniciado,
        completado: true,
      };

      await this.axios
        .put("/v1/sprint/" + idSprint, data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.sprintActual.completado = true;
            this.recargarValidaciones();
            this.sprintActual = null;
            this.initialize();
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    recargarValidaciones() {
      if (!this.sprintActual) {
        this.validacionCompletar = false;
        this.validacionIniciar = true;
        return;
      }

      if (this.existeSprintIniciadoBySprint()) {
        this.validacionCompletar = true;
        this.validacionIniciar = false;
      } else if (this.existeSprintIniciado()) {
        this.validacionCompletar = false;
        this.validacionIniciar = false;
      } else {
        this.validacionCompletar = false;
        this.validacionIniciar = true;
      }

      this.historiasActual = this.sprintActual.historiasUsuario;
    },
  },

  watch: {
    sprintActual() {
      this.recargarValidaciones();
    },

    dialog(val) {
      val || this.close();
    },
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Sprint" : "Editar Sprint";
    },

    computedDateFormattedFecInicio() {
      return this.formatDate(this.editedItem.fechaInicio);
    },

    computedDateFormattedFecFin() {
      return this.formatDate(this.editedItem.fechaFin);
    },
  },
};
