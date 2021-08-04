import VueRouter from "vue-router";
import Vue from "vue";
import Home from "@/views/Home/Home.vue";
import Backlog from "@/views/Backlog/Backlog.vue";
import Board from "@/views/Board/Board.vue";
import Members from "@/views/Members/Members.vue";
import Settings from "@/views/Settings/Settings.vue";
import Login from "@/views/Login.vue";
import MasterLayout from "@/layouts/MasterLayout.vue";
import LoginLayout from "@/layouts/LoginLayout.vue";
import HomeLayout from "@/layouts/HomeLayout.vue";
import store from "@/store/index";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: HomeLayout,
    children: [
      {
        path: "/",
        name: "Home",
        component: Home,
        meta: { requiresAuth: true },
      },
    ],
  },

  {
    path: "/",
    component: MasterLayout,
    children: [
      {
        path: "/backlog",
        name: "Backlog",
        component: Backlog,
        meta: { requiresAuth: true, requiresProject: true },
      },
      {
        path: "/board",
        name: "Board",
        component: Board,
        meta: { requiresAuth: true, requiresProject: true },
      },
      {
        path: "/members",
        name: "Members",
        component: Members,
        meta: { requiresAuth: true, requiresProject: true },
      },
      {
        path: "/settings",
        name: "Settings",
        component: Settings,
        meta: { requiresAuth: true, requiresProject: true },
      },
    ],
  },

  {
    path: "/",
    component: LoginLayout,
    children: [
      {
        path: "/login",
        name: "Login",
        component: Login,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    // 1. si requiere autenticacion verifica que el usuario este logeado en el sistema
    // 2. si no esta logeado hace un next al login.
    // 3. si ya se encuentra logeado verifica si requiere que el usuario tenga un proyecto activo.
    // 4. si tiene un proyecto activo va a la pagina solicitada.
    // 5. si el usuario no tiene un proyecto activo va a la pagina de inicio.
    if (!loggedIn()) {
      next({
        path: "/login",
      });
    } else if (to.matched.some((record) => record.meta.requiresProject)) {
      if (!getProject()) {
        next({
          path: "/",
        });
      } else {
        next();
      }
    } else {
      next();
    }
  } else {
    next(); //Siempre que no requiera autenticacion va a la pagina solicitada.
  }
});

function loggedIn() {
  //Si idUsuario es null o vacio no hay usuario logeado
  if (
    !store.state.LoginStore.idUsuario ||
    store.state.LoginStore.idUsuario == 0
  )
    return false;
  return true;
}

function getProject() {
  if (
    !store.state.LoginStore.idProyecto ||
    store.state.LoginStore.idProyecto == 0
  )
    return false;
  return true;
}

export default router;
