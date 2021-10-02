export default {
  data: () => ({
    dialog: false,
    dialogEliminar: false,
    validForm: true,
    editedIndex: -1,
    editedItem: {
      id: 0,
      nombre: "",
      descripcion: "",
    },
    defaultItem: {
      id: 0,
      nombre: "",
      descripcion: "",
    },
    historiasUsuario: [
      {
        id: 1,
        nombre: "Primera Historia",
        descripcion: "",
      },
      {
        id: 2,
        nombre: "Segunda Historia",
        descripcion: "",
      },
      {
        id: 3,
        nombre: "Tercera Historia",
        descripcion: "",
      },
      {
        id: 4,
        nombre: "Cuarta Historia",
        descripcion: "",
      },
    ],
    nameRules: [(v) => !!v || "Nombre es requerido"],
  }),

  methods: {
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

    save() {
      if (!this.$refs.form.validate()) return;
      if (this.editedIndex > -1) {
        //Edita la historia
        this.saveEditItem();
      } else {
        //Inserta la historia
        this.saveNewItem();
      }
      this.$refs.form.resetValidation();
      this.close();
    },

    saveEditItem() {
      let idHistoria = this.editedItem.id;
      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
      };
      console.log(data);
      console.log(idHistoria);

      Object.assign(this.historiasUsuario[this.editedIndex], this.editedItem);
    },

    saveNewItem() {
      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
        idProyecto: this.$store.state.LoginStore.idProyecto,
      };

      console.log(data);
      this.editedItem.id = 5;
      this.historiasUsuario.push(this.editedItem);
    },

    getDeleteItemIndex(item) {
      this.deletedIndex = this.historiasUsuario.indexOf(item);
      this.dialogEliminar = true;
    },

    deleteItem() {
      var itemList = this.historiasUsuario[this.deletedIndex];
      let idHistoria = itemList.id;

      console.log(itemList);
      console.log(idHistoria);

      this.historiasUsuario.splice(this.deletedIndex, 1);

      this.dialogEliminar = false;
    },
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Proyecto" : "Editar Proyecto";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },
};
