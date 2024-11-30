import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ArticlesView from "../views/ArticlesView.vue";
import ArticleView from "../views/ArticleView.vue";
import AboutView from "../views/AboutView.vue";
import LoginView from "../views/LoginView.vue";

const router = createRouter({
  history: createWebHistory(""),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      meta: { requiresAuth: true },
    },
    {
      path: "/articles",
      name: "articles",
      component: ArticlesView,
      meta: { requiresAuth: true },
    },
    {
      path: "/article/:id",
      name: "article",
      component: ArticleView,
      meta: { requiresAuth: true },
    },
    {
      path: "/about",
      name: "about",
      component: AboutView,
      meta: { requiresAuth: true },
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
  ],
});

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");
  
  if (to.path === "/login") {
    if (token) {
      next("/");
    } else {
      next();
    }
    return;
  }
  
  if (!token) {
    next({
      path: "/login",
      query: { redirect: to.fullPath },
    });
  } else {
    next();
  }
});

export default router;