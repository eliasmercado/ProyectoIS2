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
      descripcion: "",
    },
    defaultItem: {
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
      this.roles = [
        {
          descripcion: "Developer",
        },

        {
          descripcion: "Scrum Master",
        },

        {
          descripcion: "Product Owner",
        },
      ];

      this.listaPermisos = [
        {
          id: 1,
          descripcion: "Permisos de Administracion de Proyectos",
        },
        {
          id: 2,
          descripcion: "Permisos de Administracion de Usuarios",
        },
        {
          id: 3,
          descripcion: "Permisos de Administracion de Roles",
        },
        {
          id: 4,
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

    deleteItem() {
      var itemTable = this.roles[this.deletedIndex];
      const index = this.roles.indexOf(itemTable);
      this.roles.splice(index, 1);
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
        Object.assign(this.roles[this.editedIndex], this.editedItem);
      } else {
        this.roles.push(this.editedItem);
      }
      this.close();
    },
  },
};
