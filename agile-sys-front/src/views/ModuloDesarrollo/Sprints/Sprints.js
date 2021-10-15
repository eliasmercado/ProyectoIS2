import DxList, { DxItemDragging } from "devextreme-vue/list";

export default {
  components: {
    DxList,
    DxItemDragging,
  },
  data: () => ({
    dialog: false,
    validForm: true,
    nameRules: [(v) => !!v || "Nombre es requerido"],
    descripcionRules: [(v) => !!v || "Descripción es requerido"],
    editedIndex: -1,
    menu: false,
    menu1: false,
    date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    backlog: [],
    sprints: [],
    sprintActual: null,
    historiasActual: [],
    validacionIniciar: null,
    validacionCompletar: null,
    editedItem: {
      id: 0,
      nombre: "",
      descripcion: "",
      fechaInicio: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      fechaFin: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      idProyecto: "",
    },
    defaultItem: {
      id: 0,
      nombre: "",
      descripcion: "",
      fechaInicio: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      fechaFin: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      idProyecto: "",
    },
  }),

  created() {
    this.initialize();
  },

  methods: {
    initialize() {
      this.backlog = [
        {
          id: 1,
          descripcion: "Historia 1",
        },
        {
          id: 2,
          descripcion: "Historia 2",
        },
        {
          id: 3,
          descripcion: "Historia 3",
        },
      ];

      this.sprints = [
        {
          id: 1,
          nombre: "Sprint 1",
          fechaInicio: "2021-10-11",
          fechaFin: "2021-10-11",
          historias: [{ id: 1, descripcion: "Historia 1" }],
        },
        {
          id: 2,
          nombre: "Sprint 2",
          fechaInicio: "2021-10-11",
          fechaFin: "09-11-2021",
          historias: [{ id: 2, descripcion: "Historia 2" }],
        },
        {
          id: 3,
          nombre: "Sprint 3",
          fechaInicio: "2021-10-11",
          fechaFin: "2021-10-11",
          historias: [],
        },
      ];
    },

    editItem(item) {
      try {
        this.dialog = true;
        this.editedIndex = this.sprints.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.$refs.form.resetValidation();
      } catch (e) {}
    },

    onDragStart(e) {
      e.itemData = this[e.fromData][e.fromIndex];
    },

    onAdd(e) {
      let idHistoria = e.itemData;
      let idSprint = this.sprintActual.id;
      const data = [...this[e.toData]];
      data.splice(e.toIndex, 0, e.itemData);
      this[e.toData] = data;
      this.sprintActual.historias = data;
    },

    onRemove(e) {
      const data = [...this[e.fromData]];
      data.splice(e.fromIndex, 1);
      this[e.fromData] = data;
      this.backlog = data;
    },

    existeSprintIniciado() {
      let fechaActual = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);

      if (
        this.sprints.filter(
          (x) =>
            fechaActual >=
              new Date(new Date(this.formatDateSprint(x.fechaInicio)))
                .toISOString()
                .substr(0, 10) &&
            fechaActual <=
              new Date(new Date(this.formatDateSprint(x.fechaFin)))
                .toISOString()
                .substr(0, 10)
        ).length > 0
      )
        return true;
      return false;
    },

    existeSprintIniciadoBySprint() {
      let fechaActual = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);
      if (
        fechaActual >=
          new Date(
            new Date(this.formatDateSprint(this.sprintActual.fechaInicio))
          )
            .toISOString()
            .substr(0, 10) &&
        fechaActual <=
          new Date(new Date(this.formatDateSprint(this.sprintActual.fechaFin)))
            .toISOString()
            .substr(0, 10)
      )
        return true;
      return false;
    },

    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${day}/${month}/${year}`;
    },

    formatDateSprint(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${day}/${month}/${year}`;
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
      this.close();
    },

    async saveEditItem() {
      let idSprint = this.sprintActual.id;

      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
        fechaInicio: this.editedItem.fechaInicio,
        fechaFin: this.editedItem.fechaFin,
      };

      console.log("idSprint " + idSprint);
      console.log(data);
      Object.assign(this.sprints[this.editedIndex], this.editedItem);
    },

    async saveNewItem() {
      var data = {
        nombre: this.editedItem.nombre,
        descripcion: this.editedItem.descripcion,
        fechaInicio: this.editedItem.fechaInicio,
        fechaFin: this.editedItem.fechaFin,
        idProyecto: this.$store.state.LoginStore.idProyecto,
      };
      console.log(data);
      this.editedItem.historias = [];
      this.editedItem.id = 5;
      this.sprints.push(this.editedItem);
    },
  },

  watch: {
    sprintActual() {
      if (this.existeSprintIniciadoBySprint()) {
        this.validacionCompletar = true;
        this.validacionIniciar = false;
      } else if (this.existeSprintIniciado()) {
        this.validacionCompletar = false;
        this.validacionIniciar = false;
      } else {
        this.validacionCompletar = false;
        this.validacionIniciar = true;
      }

      this.historiasActual = this.sprintActual.historias;
    },

    dialog(val) {
      val || this.close();
    },
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Sprint" : "Editar Sprint";
    },

    computedDateFormattedFecInicio() {
      return this.formatDate(this.editedItem.fechaInicio);
    },

    computedDateFormattedFecFin() {
      return this.formatDate(this.editedItem.fechaFin);
    },
  },
};
