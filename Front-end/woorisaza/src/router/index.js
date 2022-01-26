import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Guide from "../views/Guide.vue"
import MyBoard from "../views/MySaza/MySaza.vue";
import MyPage from "../views/MySaza/MyPage.vue"
import MyArticleDetail from "../views/MySaza/MySaza.vue";
import Board from "../views/ThisSaza/ThisSaza.vue";
import ArticleDetail from "../views/ThisSaza/ThisSaza.vue";
import ArticleWrite from "../views/ThisSaza/ThisSaza.vue";
import Request from "../views/Request/Request.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/guide",
    name: "Guide",
    component: Guide,
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
  },
  {
    path: "/board/:articleno",
    name: "ArticleDetail",
    component: ArticleDetail,
  },
  {
    path: "/board/write",
    name: "ArticleWrite",
    component: ArticleWrite,
  },
  {
    path: "/request/:articleno",
    name: "Request",
    component: Request,
  },
  {
    path: "/myboard",
    name: "MyBoard",
    component: MyBoard,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/myboard/:articleno",
    name: "MyArticleDetail",
    component: MyArticleDetail,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
