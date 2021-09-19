export default {
  data: () => ({
    dialog: false,
    dialogEliminar: false,
    validForm: true,
    itemsMiembros: [],
    headers: [
      { text: "Nombre", align: "start", value: "nombre" },
      { text: "Descripcion", align: "start", value: "descripcion" },
      { text: "Fecha de Inicio", align: "start", value: "fechaInicio" },
      { text: "Fecha de FIn", align: "start", value: "fechaFin" },
      { text: "Estado", align: "start", value: "estado" },
      { text: "Miembros", align: "start", value: "usuarios" },
      { text: "Acciones", value: "actions", sortable: false },
    ],
    projects: [],
    editedIndex: -1,
    deletedIndex: -1,
    editedItem: {
      idProyecto: 0,
      nombre: "",
      descripcion: "",
      fechaInicio: "",
      fechaFin: "",
      estado: "",
      usuarios: [],
    },
    defaultItem: {
      idProyecto: 0,
      nombre: "",
      descripcion: "",
      fechaInicio: "",
      fechaFin: "",
      estado: "",
      usuarios: [],
    },
    nameRules: [(v) => !!v || "Nombre es requerido"],
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Usuario" : "Editar Usuario";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },

  created() {
    this.initialize();
    this.initializeMembers();
  },

  methods: {
    async initialize() {
      this.axios
        .get("/v1/proyecto/")
        .then((response) => (this.projects = response.data.data));
    },

    initializeMembers() {
      this.axios
        .get("/v1/usuario/")
        .then((response) => (this.itemsMiembros = response.data.data));
    },

    editItem(item) {
      this.editedIndex = this.projects.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    getDeleteItemIndex(item) {
      this.deletedIndex = this.projects.indexOf(item);
      this.dialogEliminar = true;
    },

    async deleteItem() {
      var itemTable = this.projects[this.deletedIndex];
      const index = this.projects.indexOf(itemTable);
      let idProyecto = itemTable.idProyecto;

      await this.axios
        .delete("/v1/proyecto/" + idProyecto.toString())
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.projects.splice(index, 1);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });

      this.dialogEliminar = false;
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
      this.$refs.form.resetValidation();
      this.close();
    },

    async saveEditItem() {
      let idProyecto = this.editedItem.idProyecto;
      var data = {
        nombre: this.editedItem.nombre,
        fechaInicio: this.editedItem.fechaInicio,
        descripcion: this.editedItem.descripcion,
      };

      await this.axios
        .put("/v1/proyecto/" + idProyecto.toString(), data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            Object.assign(this.projects[this.editedIndex], this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async saveNewItem() {
      var data = {
        nombre: this.editedItem.nombre,
        fechaInicio: this.editedItem.fechaInicio,
        descripcion: this.editedItem.descripcion,
      };

      await this.axios
        .post("/v1/proyecto/", data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.editedItem.idProyecto = response.data.data.idProyecto;
            this.projects.push(this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },
  },
};
