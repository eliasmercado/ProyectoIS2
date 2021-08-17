export default {
  data: () => ({
    dialog: false,
    dialogEliminar: false,
    validForm: true,
    headers: [
      { text: "Nombres", align: "start", value: "nombres" },
      { text: "Apellidos", align: "start", value: "apellidos" },
      { text: "Rol", align: "start", value: "rol.descripcion" },
      { text: "Email", align: "start", value: "email" },
      { text: "Teléfono", align: "start", value: "telefono" },
      { text: "Usuario", align: "start", value: "usuario" },
      { text: "Acciones", value: "actions", sortable: false },
    ],
    users: [],
    roles: [],
    editedIndex: -1,
    deletedIndex: -1,
    editedItem: {
      idUsuario: 0,
      nombre: "",
      apellido: "",
      email: "",
      telefono: "",
      usuario: "",
      rol: {},
    },
    defaultItem: {
      idUsuario: 0,
      nombre: "",
      apellido: "",
      email: "",
      telefono: "",
      usuario: "",
      rol: {},
    },
    emailRules: [
      (v) => !!v || "Email es requerido",
      (v) => /.+@.+/.test(v) || "Debe tener un formato válido",
      (v) => v.length <= 255 || "Debe tener un máximo de 255 caracteres",
    ],

    nameRules: [(v) => !!v || "Nombre es requerido"],
    lastNameRules: [(v) => !!v || "Apellido es requerido"],
    userRules: [
      (v) => !!v || "Usuario es requerido",
      (v) => v.length <= 10 || "Debe tener un máximo de 40 caracteres",
    ],
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
  },

  methods: {
    initialize() {
      this.axios
        .get("/api/v1/usuario/")
        .then((response) => (this.users = response.data));

      this.axios
        .get("/api/v1/rol/")
        .then((response) => (this.roles = response.data));
    },

    editItem(item) {
      this.editedIndex = this.users.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    getDeleteItemIndex(item) {
      this.deletedIndex = this.users.indexOf(item);
      this.dialogEliminar = true;
    },

    async deleteItem() {
      var itemTable = this.users[this.deletedIndex];
      const index = this.users.indexOf(itemTable);
      let idUsuario = index + 1;

      await this.axios
        .delete("/api/v1/usuario/" + idUsuario.toString())
        .then((response) => {
          this.users.splice(index, 1);
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

    save() {
      if (!this.$refs.form.validate()) return;
      if (this.editedIndex > -1) {
        //Edita el usuario
        this.saveEditItem();
      } else {
        //Inserta un usuario
        this.saveNewItem();
      }
      this.close();
    },

    async saveEditItem() {
      let idUsuario = this.editedItem.idUsuario;
      var data = {
        usuario: this.editedItem.usuario,
        nombres: this.editedItem.nombres,
        apellidos: this.editedItem.apellidos,
        telefono: this.editedItem.telefono,
        email: this.editedItem.email,
        idRol: this.editedItem.rol.idRol,
      };

      await this.axios
        .put("/v1/usuario/" + idUsuario.toString(), data)
        .then((response) => {
          Object.assign(this.users[this.editedIndex], this.editedItem);
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async saveNewItem() {
      var data = {
        usuario: this.editedItem.usuario,
        nombres: this.editedItem.nombres,
        apellidos: this.editedItem.apellidos,
        telefono: this.editedItem.telefono,
        email: this.editedItem.email,
        idRol: this.editedItem.rol.idRol,
      };
      await this.axios
        .post("/v1/usuario/", data)
        .then((response) => {
          this.editedItem.idUsuario = response.data.data.idUsuario;
          this.users.push(this.editedItem);
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },
  },
};
