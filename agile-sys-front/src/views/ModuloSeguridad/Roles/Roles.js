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
      { text: "Descripción", align: "start", value: "rol" },
      { text: "Permisos", value: "permisos", sortable: false },
      { text: "Acciones", value: "actions", sortable: false },
    ],
    roles: [],
    editedIndex: -1,
    deletedIndex: -1,
    rolPermisoIndex: -1,
    editedItem: {
      idRol: 0,
      rol: "",
    },
    defaultItem: {
      idRol: 0,
      rol: "",
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
      this.axios
        .get("/v1/rol/")
        .then((response) => (this.roles = response.data.data));

      this.axios
        .get("/v1/permiso/")
        .then((response) => (this.listaPermisos = response.data.data));
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
      let idRol = itemTable.idRol;

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
      let permisosUsuario = this.roles[this.rolPermisoIndex].permisos;
      let permisosSeleccionados = this.selectedPermisos;
      let idRol = this.roles[this.rolPermisoIndex].idRol;

      if (permisosSeleccionados.length == 0) {
        permisosUsuario.forEach((permisoUsuario) => {
          let idPermiso = permisoUsuario.idPermiso;

          this.axios
            .delete(
              "/v1/rolPermiso/" + idRol.toString() + "/" + idPermiso.toString()
            )
            .then((response) => {
              if ("error" in response.data) {
                console.error(response.data.error.message);
              }
            })
            .catch((error) => {
              console.error("Ocurrio un error inesperado", error);
            });
        });
        //desselecciona todo de permisos usuario
      } else {
        this.desasignarPermiso(permisosUsuario, permisosSeleccionados, idRol);
        this.asignarPermiso(permisosUsuario, permisosSeleccionados, idRol);
      }

      this.roles[this.rolPermisoIndex].permisos = this.selectedPermisos;
      this.dialogPermiso = false;
    },

    asignarPermiso(permisosUsuario, permisosSeleccionados, idRol) {
      let bandera = false;

      permisosSeleccionados.forEach((permisoSeleccionado) => {
        bandera = false;
        for (var permiso in permisosUsuario) {
          if (
            permisoSeleccionado.idPermiso == permisosUsuario[permiso].idPermiso
          ) {
            bandera = true;
            break;
          }
          bandera = false;
        }
        if (!bandera) {
          var data = {
            idRol: idRol,
            idPermiso: permisoSeleccionado.idPermiso,
          };
          this.axios
            .post("/v1/rolPermiso/", data)
            .then((response) => {
              if ("error" in response.data) {
                console.error(response.data.error.message);
              }
            })
            .catch((error) => {
              console.error("Ocurrio un error inesperado", error);
            });
        }
      });
    },

    desasignarPermiso(permisosUsuario, permisosSeleccionados, idRol) {
      let bandera = false;

      permisosUsuario.forEach((permisoUsuario) => {
        bandera = false;
        for (var permiso in permisosSeleccionados) {
          if (
            permisoUsuario.idPermiso == permisosSeleccionados[permiso].idPermiso
          ) {
            bandera = true;
            break;
          }
          bandera = false;
        }
        if (!bandera) {
          let idPermiso = permisoUsuario.idPermiso;

          this.axios
            .delete(
              "/v1/rolPermiso/" + idRol.toString() + "/" + idPermiso.toString()
            )
            .then((response) => {
              if ("error" in response.data) {
                console.error(response.data.error.message);
              }
            })
            .catch((error) => {
              console.error("Ocurrio un error inesperado", error);
            });
        }
      });
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
        await this.saveEditItem();
      } else {
        await this.saveNewItem();
      }
      this.$refs.form.resetValidation();
      this.close();
    },

    async saveEditItem() {
      let idRol = this.editedItem.idRol;
      var data = {
        descripcion: this.editedItem.rol,
      };

      await this.axios
        .put("/v1/rol/" + idRol.toString(), data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            Object.assign(this.roles[this.editedIndex], this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },

    async saveNewItem() {
      var data = {
        descripcion: this.editedItem.rol,
      };

      await this.axios
        .post("/v1/rol/", data)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.editedItem.idUsuario = response.data.data.idRol;
            this.roles.push(this.editedItem);
          }
        })
        .catch((error) => {
          console.error("Ocurrio un error inesperado", error);
        });
    },
  },
};
