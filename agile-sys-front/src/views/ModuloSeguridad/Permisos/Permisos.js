export default {
  data: () => ({
    dialog: false,
    dialogEliminar: false,
    validForm: true,
    headers: [
      { text: "Descripción", align: "start", value: "descripcion" },
      {
        text: "Módulo",
        align: "start",
        value: "modulo.nombreModulo",
        sortable: false,
      },
      { text: "Acciones", value: "actions", sortable: false },
    ],
    permisos: [],
    modulos: [],
    editedIndex: -1,
    deletedIndex: -1,
    editedItem: {
      descripcion: "",
      módulo: {},
    },
    defaultItem: {
      descripcion: "",
      módulo: {},
    },

    descripcionRules: [
      (v) => !!v || "Descripcion es requerido",
      (v) => v.length <= 50 || "Debe tener un máximo de 50 caracteres",
    ],
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Permiso" : "Editar Permiso";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },

  created() {
    this.initialize();
  },

  methods: {
    initialize() {
      this.axios
        .get("/v1/permiso/")
        .then((response) => (this.permisos = response.data.data));

      this.modulos = [
        {
          idModulo: 1,
          nombreModulo: "Seguridad",
        },
        {
          idModulo: 2,
          nombreModulo: "Proyecto",
        },
        {
          idModulo: 3,
          nombreModulo: "Desarrollo",
        },
      ];
    },

    editItem(item) {
      this.editedIndex = this.permisos.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    getDeleteItemIndex(item) {
      this.deletedIndex = this.permisos.indexOf(item);
      this.dialogEliminar = true;
    },

    async deleteItem() {
      var itemTable = this.permisos[this.deletedIndex];
      const index = this.permisos.indexOf(itemTable);
      let idPermiso = itemTable.idPermiso;

      await this.axios
        .delete("/v1/permiso/" + idPermiso.toString())
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.permisos.splice(index, 1);
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
    },

    async save() {
      if (!this.$refs.form.validate()) return;
      if (this.editedIndex > -1) {
        //Edita el permiso
        await this.saveEditItem();
      } else {
        //Agrego un nuevo permiso
        await this.saveNewItem();
      }
      this.$refs.form.resetValidation();
      this.close();
    },

    async saveEditItem() {
      let idPermiso = this.editedItem.idPermiso;
      var data = {
        descripcion: this.editedItem.descripcion,
        idModulo: this.editedItem.modulo.idModulo,
      };

      await this.axios
        .put("/v1/permiso/" + idPermiso.toString(), data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            Object.assign(this.permisos[this.editedIndex], this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async saveNewItem() {
      var data = {
        descripcion: this.editedItem.descripcion,
        idModulo: this.editedItem.modulo.idModulo,
      };

      await this.axios
        .post("/v1/permiso/", data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.editedItem.idPermiso = response.data.data.idPermiso;
            this.permisos.push(this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },
  },
};
