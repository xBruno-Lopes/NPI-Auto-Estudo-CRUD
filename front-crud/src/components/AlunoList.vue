<template>
  <v-row align="center" class="list px-3 mx-auto">
    <v-col cols="12" md="8">
      <v-text-field v-model="nome" label="Procurar por Nome"></v-text-field>
    </v-col>
    <v-col cols="12" md="4">
      <v-btn small @click="searchNome">
        Procurar
      </v-btn>
    </v-col>
    <v-col cols="12" sm="12">
      <v-card class="mx-auto" tile>
        <v-card-title>Alunos</v-card-title>
        <v-data-table
            :headers="headers"
            :items="alunos"
            disable-pagination
            :hide-default-footer="true"
        >
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="editAlunos(item.id)">mdi-pencil</v-icon>
            <v-icon small @click="deleteAluno(item.id)">mdi-delete</v-icon>
          </template>
        </v-data-table>
        <v-card-actions v-if="alunos.length > 0">
          <v-btn small color="error" @click="removeAllAlunos">
            Remover Todos
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>
<script>
import AlunoDataService from "../services/AlunoDataService";
export default {
  name: "alunos-list",
  data() {
    return {
      alunos: [],
      nome: "",
      headers: [
        { text: "Nome", align: "start", sortable: false, value: "nome" },
        { text: "Curso", value: "curso", sortable: false },
        { text: "Status", value: "status", sortable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
    };
  },
  methods: {
    retrieveAlunos() {
      AlunoDataService.getAll()
          .then((response) => {
            this.alunos = response.data.map(this.getDisplayAlunos);
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
    },
    refreshList() {
      this.retrieveAlunos();
    },
    removeAllAlunos() {
      AlunoDataService.deleteAll()
          .then((response) => {
            console.log(response.data);
            this.refreshList();
          })
          .catch((e) => {
            console.log(e);
          });
    },
    searchNome() {
      AlunoDataService.findByNome(this.nome)
          .then((response) => {
            this.alunos = response.data.map(this.getDisplayAlunos);
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
    },
    editAlunos(id) {
      this.$router.push({ name: "alunos-detalhes", params: { id: id } });
    },
    deleteAluno(id) {
      AlunoDataService.delete(id)
          .then(() => {
            this.refreshList();
          })
          .catch((e) => {
            console.log(e);
          });
    },
    getDisplayAlunos(aluno) {
      return {
        id: aluno.id,
        nome: aluno.nome.length > 30 ? aluno.nome.substr(0, 30) + "..." : aluno.nome,
        curso: aluno.curso.length > 30 ? aluno.curso.substr(0, 30) + "..." : aluno.curso,
        status: aluno.ativo ? "Ativo" : "Não Ativo",
      };
    },
  },
  mounted() {
    this.retrieveAlunos();
  },
};
</script>
<style>
.list {
  max-width: 750px;
}
</style>