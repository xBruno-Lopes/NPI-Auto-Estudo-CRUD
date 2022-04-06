import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router(
    {
        mode: "history",
        routes: [
            {
                path: "/",
                alias: "/alunos",
                name: "alunos",
                component: () => import("./components/AlunoList")
            },
            {
                path: "/alunos/:id",
                name: "alunos-detalhes",
                component: () => import("./components/Aluno")
            },
            {
                path: "/add",
                name: "add",
                component: () => import("./components/AddAluno")
            }
        ]
    }
);