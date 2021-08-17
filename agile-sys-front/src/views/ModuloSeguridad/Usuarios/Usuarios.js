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
      nombre: "",
      apellido: "",
      email: "",
      telefono: "",
      usuario: "",
      rol: {},
    },
    defaultItem: {
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
      this.users = [
        {
          idUsuario: 1,
          nombres: "Elias Valentin",
          apellidos: "Mercado Lopez",
          email: "eliasmerc23@gmail.com",
          telefono: "0972250212",
          usuario: "emercado",
          rol: {
            idRol: 1,
            descripcion: "Developer",
          },
        },
      ];

      this.roles = [
        { idRol: 1, descripcion: "Analista de Sistemas" },
        { idRol: 2, descripcion: "Desarrollador" },
      ];
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

    deleteItem() {
      var itemTable = this.users[this.deletedIndex];
      const index = this.users.indexOf(itemTable);
      this.users.splice(index, 1);
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
        Object.assign(this.users[this.editedIndex], this.editedItem);
      } else {
        this.users.push(this.editedItem);
      }
      this.close();
    },
  },
};
