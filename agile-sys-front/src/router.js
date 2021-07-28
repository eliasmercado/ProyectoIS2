import VueRouter from "vue-router";
import Vue from "vue";
import Home from "./views/Home/Home.vue";
import Backlog from "./views/Backlog/Backlog.vue";
import Board from "./views/Board/Board.vue";
import Members from "./views/Members/Members.vue";
import Settings from "./views/Settings/Settings.vue";
import Login from "./views/Login.vue";
import MasterLayout from "./layouts/MasterLayout.vue";
import LoginLayout from "./layouts/LoginLayout.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: MasterLayout,
    children: [
      {
        path: "/",
        name: "Home",
        component: Home,
      },
      {
        path: "/backlog",
        name: "Backlog",
        component: Backlog,
      },
      {
        path: "/board",
        name: "Board",
        component: Board,
      },
      {
        path: "/members",
        name: "Members",
        component: Members,
      },
      {
        path: "/settings",
        name: "Settings",
        component: Settings,
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

export default router;
