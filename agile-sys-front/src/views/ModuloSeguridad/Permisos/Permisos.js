export default {
  data: () => ({
    dialog: false,
    dialogEliminar: false,
    validForm: true,
    headers: [
      { text: "Descripción", align: "start", value: "descripcion" },
      { text: "Módulo", align: "start", value: "modulo.nombreModulo", sortable: false },
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
      this.permisos = [
        {
          idPermiso: 1,
          descripcion: "Administracion de Seguridad",
          modulo: {
            idModulo: 1,
            nombreModulo: "Seguridad",
          },
        },

        {
          idPermiso: 2,
          descripcion: "Administracion de Proyectos",
          modulo: {
            idModulo: 2,
            nombreModulo: "Proyecto",
          },
        },

        {
          idPermiso: 3,
          descripcion: "Equipo de Desarrollo",
          modulo: {
            idModulo: 3,
            nombreModulo: "Desarrollo",
          },
        },
      ];

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

    deleteItem() {
      var itemTable = this.permisos[this.deletedIndex];
      const index = this.permisos.indexOf(itemTable);
      this.permisos.splice(index, 1);
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
        Object.assign(this.permisos[this.editedIndex], this.editedItem);
      } else {
        this.permisos.push(this.editedItem);
      }
      this.close();
    },
  },
};
