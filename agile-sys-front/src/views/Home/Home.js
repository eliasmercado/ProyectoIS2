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
  }),

  methods: {
    cargarProyectoActivo() {
      let idProyecto = this.$store.state.LoginStore.idProyecto;
      let url = `/v1/proyecto/${idProyecto}`;

      this.axios.get(url).then((result) => {
        this.nombreProyecto = result.data.data.nombre;
        this.descripcionProyecto = result.data.data.descripcion;
        this.fechaInicioProyecto = result.data.data.fechaInicio;
      });
    },
  },

  mounted() {
    this.cargarProyectoActivo();
  },
};
