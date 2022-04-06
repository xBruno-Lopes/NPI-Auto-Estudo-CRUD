<template>
  <div class="submit-form mt-3 mx-auto">
    <p class="headline">Adicionar Aluno</p>
    <div v-if="!submitted">
      <v-form ref="form" lazy-validation>
        <v-text-field
            v-model="aluno.nome"
            :rules="[(v) => !!v || 'Nome é obrigatório']"
            label="Nome"
            required
        ></v-text-field>
        <v-text-field
            v-model="aluno.curso"
            :rules="[(v) => !!v || 'Curso é obrigatório']"
            label="Curso"
            required
        ></v-text-field>
      </v-form>
      <v-btn color="primary" class="mt-3" @click="saveAluno">Adicionar</v-btn>
    </div>
    <div v-else>
      <v-card class="mx-auto">
        <v-card-title>
          Aluno adicionado!
        </v-card-title>
        <v-card-subtitle>
          Clique no botão abaixo para adicionar um novo aluno.
        </v-card-subtitle>
        <v-card-actions>
          <v-btn color="success" @click="newAluno">Add</v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>
<script>
import AlunoDataService from "../services/AlunoDataService";
export default {
  name: "add-aluno",
  data() {
    return {
      aluno: {
        id: null,
        nome: "",
        curso: "",
        ativo: false,
      },
      submitted: false,
    };
  },
  methods: {
    saveAluno() {
      var data = {
        nome: this.aluno.nome,
        curso: this.aluno.curso,
      };
      AlunoDataService.create(data)
          .then((response) => {
            this.aluno.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch((e) => {
            console.log(e);
          });
    },
    newAluno() {
      this.submitted = false;
      this.aluno = {};
    },
  },
};
</script>
<style>
.submit-form {
  max-width: 300px;
}
</style>