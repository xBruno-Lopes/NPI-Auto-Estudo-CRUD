<template>
  <div v-if="currentAluno" class="edit-form py-3">
    <p class="headline">Editar Aluno</p>
    <v-form ref="form" lazy-validation>
      <v-text-field
          v-model="currentAluno.nome"
          :rules="[(v) => !!v || 'Nome é Obrigatório']"
          label="Nome"
          required
      ></v-text-field>
      <v-text-field
          v-model="currentAluno.curso"
          :rules="[(v) => !!v || 'Curso é Obrigatório']"
          label="Curso"
          required
      ></v-text-field>
      <label><strong>Status:</strong></label>
      {{ currentAluno.ativo ? "Ativo" : "Não Ativo" }}
      <v-divider class="my-5"></v-divider>
      <v-btn v-if="currentAluno.ativo"
             @click="updateAtivo(false)"
             color="primary" small class="mr-2"
      >
        Não Ativo
      </v-btn>
      <v-btn v-else
             @click="updateAtivo(true)"
             color="primary" small class="mr-2"
      >
        Ativo
      </v-btn>
      <v-btn color="error" small class="mr-2" @click="deleteAluno">
        Delete
      </v-btn>
      <v-btn color="success" small @click="updateAluno">
        Update
      </v-btn>
    </v-form>
    <p class="mt-3">{{ message }}</p>
  </div>
  <div v-else>
    <p>Por Favor clique em um aluno...</p>
  </div>
</template>
<script>
import AlunoDataService from "../services/AlunoDataService";
export default {
  name: "mAluno",
  data() {
    return {
      currentAluno: null,
      message: "",
    };
  },
  methods: {
    getAluno(id) {
      AlunoDataService.get(id)
          .then((response) => {
            this.currentAluno = response.data;
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
    },
    updateAtivo(status) {
      var data = {
        id: this.currentAluno.id,
        nome: this.currentAluno.nome,
        curso: this.currentAluno.curso,
        ativo: status,
      };
      AlunoDataService.update(this.currentAluno.id, data)
          .then((response) => {
            this.currentAluno.ativo = status;
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
    },
    updateAluno() {
      AlunoDataService.update(this.currentAluno.id, this.currentAluno)
          .then((response) => {
            console.log(response.data);
            this.message = "Aluno atualizado com sucesso!";
          })
          .catch((e) => {
            console.log(e);
          });
    },
    deleteAluno() {
      AlunoDataService.delete(this.currentAluno.id)
          .then((response) => {
            console.log(response.data);
            this.$router.push({ name: "alunos" });
          })
          .catch((e) => {
            console.log(e);
          });
    },
  },
  mounted() {
    this.message = "";
    this.getAluno(this.$route.params.id);
  },
};
</script>
<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>