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

      this.axios
        .get(url)
        .then((response) => {
          if (!"error" in response.data) {
            this.nombreProyecto = response.data.data.nombre;
            this.descripcionProyecto = response.data.data.descripcion;
            this.fechaInicioProyecto = response.data.data.fechaInicio;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },

  mounted() {
    this.cargarProyectoActivo();
  },
};
