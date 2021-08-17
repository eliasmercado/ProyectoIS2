export default {
  data: () => ({
    dialog: false,
    dialogEliminar: false,
    dialogPermiso: false,
    validForm: true,
    expanded: [],

    listaPermisos: [],
    selectedPermisos: [],
    singleSelect: false,
    headersPermiso: [
      { text: "Descripción", align: "start", value: "descripcion" },
    ],
    headers: [
      { text: "Descripción", align: "start", value: "descripcion" },
      { text: "Permisos", value: "permisos", sortable: false },
      { text: "Acciones", value: "actions", sortable: false },
    ],
    roles: [],
    editedIndex: -1,
    deletedIndex: -1,
    rolPermisoIndex: -1,
    editedItem: {
      idRol: 0,
      descripcion: "",
    },
    defaultItem: {
      idRol: 0,
      descripcion: "",
    },

    descripcionRules: [
      (v) => !!v || "Descripcion es requerido",
      (v) => v.length <= 40 || "Debe tener un máximo de 40 caracteres",
    ],
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Rol" : "Editar Rol";
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
/*       this.axios
        .get("/api/v1/rol/")
        .then((response) => (this.roles = response.data));

      this.axios
        .get("/api/v1/permiso/")
        .then((response) => (this.listaPermisos = response.data)); */

      this.roles = [
        {
          idRol: 1,
          descripcion: "Developer",
          permisos: [
            {
              idPermiso: 2,
              descripcion: "Product Owner",
            },
          ],
        },

        {
          idRol: 2,
          descripcion: "Scrum Master",
        },

        {
          idRol: 3,
          descripcion: "Product Owner",
        },
      ];

      this.listaPermisos = [
        {
          idPermiso: 1,
          descripcion: "Permisos de Administracion de Proyectos",
        },
        {
          idPermiso: 2,
          descripcion: "Permisos de Administracion de Usuarios",
        },
        {
          idPermiso: 3,
          descripcion: "Permisos de Administracion de Roles",
        },
        {
          idPermiso: 4,
          descripcion: "Permisos de Administracion de Permisos",
        },
      ];
    },

    editItem(item) {
      this.editedIndex = this.roles.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    getDeleteItemIndex(item) {
      this.deletedIndex = this.roles.indexOf(item);
      this.dialogEliminar = true;
    },

    async deleteItem() {
      var itemTable = this.roles[this.deletedIndex];
      const index = this.roles.indexOf(itemTable);
      let idRol = index + 1;

      await this.axios
        .delete("/v1/rol/" + idRol.toString())
        .then((response) => {
          this.roles.splice(index, 1);
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });

      this.dialogEliminar = false;
    },

    getRolPermisoItemIndex(item) {
      this.rolPermisoIndex = this.roles.indexOf(item);
      this.selectedPermisos = item.permisos;
      this.dialogPermiso = true;
    },

    addPermiso() {
      this.roles[this.rolPermisoIndex].permisos = this.selectedPermisos;
      var itemTable = this.roles[this.rolPermisoIndex];
      console.log(itemTable);
      this.dialogPermiso = false;
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
        this.saveEditItem();
      } else {
        this.saveNewItem();
      }
      this.close();
    },

    async saveEditItem() {
      let idRol = this.editedItem.idRol;
      var data = {
        descripcion: this.editedItem.descripcion,
      };

      await this.axios
        .put("/v1/rol/" + idRol.toString(), data)
        .then((response) => {
          Object.assign(this.roles[this.editedIndex], this.editedItem);
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async saveNewItem() {
      var data = {
        descripcion: this.editedItem.descripcion,
      };

      await this.axios
        .post("/v1/rol/", data)
        .then((response) => {
          this.editedItem.idUsuario = response.data.data.idRol;
          this.roles.push(this.editedItem);
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },
  },
};
