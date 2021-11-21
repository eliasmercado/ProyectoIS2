export default {
  data: () => ({
    tab: null,
    nombreProyecto: "",
    descripcionProyecto: "",
    fechaInicioProyecto: "",
    items: [
      { tab: "Proyectos terminados", content: "" },
      { tab: "Asignaciones terminadas", content: "" },
    ],
    statuses: [],
    asignacionesEnCurso: [],
    asignacionesTerminadas: [],
    proyectosTerminados: [],
  }),

  methods: {
    async initialize() {
      let asignaciones = [];

      //cargamos la fase para buscar las asignaciones activas y terminadas del usuario
      await this.axios
        .get("/v1/fase/")
        .then((response) => (this.statuses = response.data.data.sort()));

      await this.axios
        .get("/v1/historiaUsuario/" + this.$store.state.LoginStore.idProyecto)
        .then((response) => {
          if ("error" in response.data) {
            this.asignacionesEnCurso = [];
            this.asignacionesTerminadas = [];
            console.error(response.data.error.message);
          } else {
            console.log(response.data.data);
            asignaciones = response.data.data.filter(x => x.idUsuarioResponsable == this.$store.state.LoginStore.idUsuario);
          }
        });

        this.asignacionesEnCurso = asignaciones.filter(x => this.obtenerFaseByIdFase(x.idFase).toUpperCase() != "DONE");
        this.asignacionesTerminadas = asignaciones.filter(x => this.obtenerFaseByIdFase(x.idFase).toUpperCase() === "DONE");

        console.log(this.asignacionesEnCurso);
        console.log(this.asignacionesTerminadas);
    },

    async cargarProyectoActivo() {
      let idProyecto = this.$store.state.LoginStore.idProyecto;
      let url = `/v1/proyecto/${idProyecto}`;

      await this.axios
        .get(url)
        .then((response) => {
          if ("error" in response.data) {
            console.error(response.data.error.message);
          } else {
            this.nombreProyecto = response.data.data.nombre;
            this.descripcionProyecto = response.data.data.descripcion;
            this.fechaInicioProyecto = this.formatDate(
              response.data.data.fechaInicio
            );
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },

    obtenerFaseByIdFase(idFase) {
      return this.statuses.filter((x) => x.idFase == idFase)[0].fase;
    },

    formatDate(date) {
      if (!date) return null;

      date = new Date(date).toISOString().substring(0, 10);

      const [year, month, day] = date.split("-");
      return `${day}/${month}/${year}`;
    },
  },

  mounted() {
    this.cargarProyectoActivo();
    this.initialize();
  },
};
