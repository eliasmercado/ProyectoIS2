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
      fechaInicio: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      fechaFin: "",
      estado: "",
      usuarios: [],
    },
    defaultItem: {
      idProyecto: 0,
      nombre: "",
      descripcion: "",
      fechaInicio: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      fechaFin: "",
      estado: "",
      usuarios: [],
    },
    nameRules: [(v) => !!v || "Nombre es requerido"],
    descripcionRules: [(v) => !!v || "Descripción es requerido"],
    menu: false,
    date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    accionesMiembrosProyecto: [],
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Proyecto" : "Editar Proyecto";
    },

    computedDateFormatted() {
      return this.formatDate(this.editedItem.fechaInicio);
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
      await this.axios
        .get("/v1/proyecto/")
        .then((response) => (this.projects = response.data.data));
    },

    async initializeMembers() {
      await this.axios
        .get("/v1/usuario/")
        .then((response) => (this.itemsMiembros = response.data.data));
    },

    editItem(item) {
      this.editedIndex = this.projects.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.accionesMiembrosProyecto = this.editedItem.usuarios;
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
      this.accionesMiembrosProyecto = [];
      this.$refs.form.resetValidation();
      this.close();
    },

    async saveEditItem() {
      let idProyecto = this.editedItem.idProyecto;
      var data = {
        nombre: this.editedItem.nombre,
        fechaInicio: this.formatDate(this.editedItem.fechaInicio),
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

      await this.asignarMiembros(idProyecto);
      await this.desasignarMiembros();
      Object.assign(this.projects[this.editedIndex], this.editedItem);
    },

    async saveNewItem() {
      let ok = false;
      var data = {
        nombre: this.editedItem.nombre,
        fechaInicio: this.formatDate(this.editedItem.fechaInicio),
        descripcion: this.editedItem.descripcion,
      };

      await this.axios
        .post("/v1/proyecto/", data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            ok = true;
            this.editedItem.idProyecto = response.data.data.idProyecto;
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });

      if (ok) {
        await this.asignarMiembros(this.editedItem.idProyecto);
        this.editedItem.estado = "Iniciado";
        this.projects.push(this.editedItem);
      }
    },

    async asignarMiembros(idProyecto) {
      let auxUsuarios = [];
      let usuarioAsignado;

      for (var index in this.accionesMiembrosProyecto) {
        auxUsuarios = this.editedItem.usuarios.filter(
          (usuario) =>
            usuario.idUsuario == this.accionesMiembrosProyecto[index].idUsuario
        );

        if (auxUsuarios.length == 0) {
          var data = {
            idProyecto: idProyecto,
            idUsuario: this.accionesMiembrosProyecto[index].idUsuario,
          };

          await this.axios
            .post("/v1/usuario-proyecto", data)
            .then((response) => {
              if ("error" in response.data) {
                console.error(
                  "Ocurrio un error inesperado al agregar el usuario al proyecto."
                );
              } else {
                usuarioAsignado = this.itemsMiembros.filter(
                  (nuevoMiembro) =>
                    nuevoMiembro.idUsuario ==
                    this.accionesMiembrosProyecto[index].idUsuario
                );
                usuarioAsignado[0].idUsuarioProyecto =
                  response.data.data.idUsuarioProyecto;
                this.editedItem.usuarios.push(usuarioAsignado[0]);
              }
            })
            .catch((error) => {
              console.error("Ocurrio un error inesperado", error);
            });
        }
      }
    },

    async desasignarMiembros() {
      let auxUsuarios = [];
      let usuariosDesasignados = [];

      for (var index in this.editedItem.usuarios) {
        auxUsuarios = this.accionesMiembrosProyecto.filter(
          (usuario) =>
            usuario.idUsuario == this.editedItem.usuarios[index].idUsuario
        );

        if (auxUsuarios.length == 0) {
          usuariosDesasignados.push(this.editedItem.usuarios[index]);
        }
      }
      await this.desasignarMiembrosApi(usuariosDesasignados);
    },

    async desasignarMiembrosApi(usuariosDesasignados) {
      for (var index in usuariosDesasignados) {
        await this.axios
          .delete(
            "/v1/usuario-proyecto/" +
              usuariosDesasignados[index].idUsuarioProyecto
          )
          .then((response) => {
            if ("error" in response.data) {
              console.error(
                "Ocurrio un error inesperado al desasignar el usuario al proyecto."
              );
            } else {
              var indexBorrar = this.editedItem.usuarios.findIndex(
                (usuario) =>
                  usuario.idUsuario == usuariosDesasignados[index].idUsuario
              );

              this.editedItem.usuarios.splice(indexBorrar, 1);
            }
          })
          .catch((error) => {
            console.error("Ocurrio un error inesperado", error);
          });
      }
    },

    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${day}/${month}/${year}`;
    },
  },
};
