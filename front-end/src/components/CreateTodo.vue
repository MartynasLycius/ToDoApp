<template>
  <div class="text-center">
    <v-dialog
        v-model="dialog"
        width="535"
        @click:outside="outSideCloseOfTodoForm"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            color="primary"
            dark
            v-bind="attrs"
            v-on="on"
        >
          Add Todo
        </v-btn>
      </template>

      <v-card>
        <todo-form ref="todoFormRef" :change-dialog-status="changeDialogStatus"></todo-form>
      </v-card>
    </v-dialog>


  </div>
</template>

<script>
  import TodoForm from '@/components/TodoForm';

  export default {
    name: 'CreateTodo',
    components: {
      todoForm: TodoForm,
    },
    data() {
      return {
        dialog: false,
      };
    },
    methods: {
      changeDialogStatus() {
        this.dialog = false;
      },
      showTodoDialog() {
        this.dialog = true;
      },
      outSideCloseOfTodoForm(){
        this.$refs.todoFormRef.resetForm();
      }
    },
    mounted() {
      this.$eventBus.$on('showTodoDialog', () => {
        this.showTodoDialog();
      });
    }
  };
</script>

<style scoped>
</style>
