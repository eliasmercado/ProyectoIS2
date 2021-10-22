export default {
  data: () => ({
    dialog: false,
    dialogEliminar: false,
    validForm: true,
    editedIndex: -1,
    editedItem: {
      idHistoriaUsuario: 0,
      nombre: "",
      descripcion: "",
    },
    defaultItem: {
      idHistoriaUsuario: 0,
      nombre: "",
      descripcion: "",
    },
    historiasUsuario: [],
    nameRules: [(v) => !!v || "Nombre es requerido"],
  }),

  created() {
    this.initialize();
  },

  methods: {
    initialize() {
      this.axios
        .get("/v1/historiaUsuario/" + this.$store.state.LoginStore.idProyecto)
        .then((response) => (this.historiasUsuario = response.data.data.filter(x => x.idSprint == 0)));
    },

    editItem(item) {
      this.editedIndex = this.historiasUsuario.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
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
        //Edita la historia
        await this.saveEditItem();
      } else {
        //Inserta la historia
        await this.saveNewItem();
      }
      this.$refs.form.resetValidation();
      this.close();
    },

    async saveEditItem() {
      let idHistoriaUsuario = this.editedItem.idHistoriaUsuario;
      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
      };

      await this.axios
        .put("/v1/historiaUsuario/" + idHistoriaUsuario.toString(), data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            Object.assign(
              this.historiasUsuario[this.editedIndex],
              this.editedItem
            );
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
        idProyecto: this.$store.state.LoginStore.idProyecto,
      };

      await this.axios
        .post("/v1/historiaUsuario/", data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.editedItem.idHistoriaUsuario =
              response.data.data.idHistoriaUsuario;
            this.historiasUsuario.push(this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    getDeleteItemIndex(item) {
      this.deletedIndex = this.historiasUsuario.indexOf(item);
      this.dialogEliminar = true;
    },

    async deleteItem() {
      var itemList = this.historiasUsuario[this.deletedIndex];
      let idHistoriaUsuario = itemList.idHistoriaUsuario;

      await this.axios
        .delete("/v1/historiaUsuario/" + idHistoriaUsuario.toString())
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.historiasUsuario.splice(this.deletedIndex, 1);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });

      this.dialogEliminar = false;
    },
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nueva Historia" : "Editar Historia";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },
};
