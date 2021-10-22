import DxList, { DxItemDragging } from "devextreme-vue/list";

export default {
  components: {
    DxList,
    DxItemDragging,
  },
  data: () => ({
    dialog: false,
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
    },
  }),

  created() {
    this.initialize();
  },

  methods: {
    async initialize() {
      await this.axios
        .get("/v1/historiaUsuario/" + this.$store.state.LoginStore.idProyecto)
        .then(
          (response) =>
            (this.backlog = response.data.data.filter((x) => x.idSprint == 0))
        );

      await this.axios
        .get("/v1/sprint")
        .then(
          (response) =>
            (this.sprints = response.data.data.filter(
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

    async onAdd(e) {
      let historiaUsuario = e.itemData;
      let idSprint = this.sprintActual.idSprint;

      var body = {
        nombre: historiaUsuario.nombre,
        descripcion: historiaUsuario.descripcion,
        idSprint: idSprint,
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
            this.backlog.historias = this[e.toData];
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
      this.sprintActual.historias = this[e.fromData];
    },

    async onAddBacklog(e) {
      let historiaUsuario = e.itemData;

      var body = {
        nombre: historiaUsuario.nombre,
        descripcion: historiaUsuario.descripcion,
        idSprint: null,
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
            this.sprintActual.historias = this[e.toData];
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
      this.backlog = this[e.fromData];
    },

    existeSprintIniciado() {
      let fechaActual = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);

      if (
        this.sprints.filter(
          (x) =>
            fechaActual >=
              new Date(x.fechaInicio).toISOString().substr(0, 10) &&
            fechaActual <= new Date(x.fechaFin).toISOString().substr(0, 10)
        ).length > 0
      )
        return true;
      return false;
    },

    existeSprintIniciadoBySprint() {
      let fechaActual = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);

      if (
        fechaActual >=
          new Date(this.sprintActual.fechaInicio).toISOString().substr(0, 10) &&
        fechaActual <=
          new Date(this.sprintActual.fechaFin).toISOString().substr(0, 10)
      )
        return true;
      return false;
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
      let idSprint = this.sprintActual.idSprint;

      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
        fechaInicio: this.formatDate(this.editedItem.fechaInicio),
        fechaFin: this.formatDate(this.editedItem.fechaFin),
      };

      await this.axios
        .put("/v1/sprint/" + idSprint, data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            Object.assign(this.sprints[this.editedIndex], this.editedItem);
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
        fechaFin: this.formatDate(this.editedItem.fechaFin),
        idProyecto: this.$store.state.LoginStore.idProyecto,
      };

      await this.axios
        .post("/v1/sprint/", data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.editedItem.historiasActual = [];
            this.editedItem.idSprint = response.data.data.idSprint;
            this.editedItem.fechaInicio = new Date(
              this.editedItem.fechaInicio
            ).toISOString();
            this.editedItem.fechaFin = new Date(
              this.editedItem.fechaFin
            ).toISOString();
            this.sprints.push(this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async iniciarSprint() {
      if (this.sprintActual.historiasUsuario.length == 0) {
        console.log("No se puede iniciar un sprint sin historias de usuario.");
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
        fechaFin: this.formatDate(this.editedItem.fechaFin),
      };

      await this.axios
        .put("/v1/sprint/" + idSprint, data)
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

    completarSprint() {},
  },

  watch: {
    sprintActual() {
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
