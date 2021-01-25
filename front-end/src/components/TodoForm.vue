<template>
  <v-form v-model="isValid">
    <div class="todo_form">
      <span v-if="this.pageInUpdateState==false" class="todo_form_title">Create Todo</span>
      <span v-if="this.pageInUpdateState==true" class="todo_form_title">Update Todo</span>
      <v-text-field
          v-model="todo.itemName"
          label="Enter task name"
          outlined
          dense
          required
          style="width: 80%">
      </v-text-field>

      <v-textarea
          outlined
          v-model="todo.description"
          style="width: 80%"
          label="Enter description"
          value="">
      </v-textarea>

      <v-menu
          v-model="menu2"
          :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="auto">
      <template v-slot:activator="{ on, attrs }">
        <v-text-field
            v-model="todo.date"
            prepend-icon="mdi-calendar"
            label="Pick a date"
            style="width: 80%; margin-top: -20px"
            readonly
            v-bind="attrs"
            v-on="on"
        ></v-text-field>
      </template>
        <v-date-picker
            v-model="todo.date"
            @input="menu2 = false">
        </v-date-picker>
      </v-menu>

      <v-row style="margin-bottom: 60px;margin-top: 10px">
        <v-btn style="margin-right: 10px"
            depressed
            color="error"
            @click="getCancel">
          Cancel
        </v-btn>

        <v-btn v-if="this.pageInUpdateState==false"
               @click="createTodo"
               color="#e2136e"
               class="primary"
               style="color: #FFFFFF"
               :disabled="!isValid">Create
        </v-btn>

        <v-btn v-if="this.pageInUpdateState==true"
               @click="updateTodo"
               color="#e2136e"
               style="color: #FFFFFF"
               :disabled="!isValid">Update
        </v-btn>
      </v-row>
    </div>
  </v-form>
</template>

<script>

  export default {
    name: 'TodoForm',
    props: {
      changeDialogStatus: Function
    },
    data() {
      return {
        menu2: false,
        todo: {
          id: '',
          itemName: '',
          description:'',
          date: '',
        },
        dialog: false,
        isValid: false,
        pageInUpdateState: false,
      };
    },
    methods: {
      getCancel() {
        this.changeDialogStatus();
        this.resetForm();
      },
      resetForm() {
        this.todo.id = '';
        this.todo.itemName = '';
        this.todo.date = '';
        this.todo.description = '';
        this.pageInUpdateState = false;
      },
      createTodo() {
        this.$restClient.post('create', this.todo)
           .then(({data}) => {
             console.log(this.$httpStatusCode.OK);
             if (data.httpStatusCode === this.$httpStatusCode.OK) {
               this.changeDialogStatus();
               this.resetForm();
               this.$eventBus.$emit(this.$evenBusConstant.REFRESH_TODO_LIST);
             }
             this.$feedback.showSuccessMessage(data.message);
           })
           .catch(() => {
             this.$feedback.showFailed('Something went wrong. Please try again!');
             this.changeDialogStatus();
           });
      },
      updateTodo() {
        this.$feedback.showLoading();
        this.$restClient.put('update', this.todo)
           .then(({data}) => {
             if (data.httpStatusCode === this.$httpStatusCode.OK) {
               this.changeDialogStatus();
               this.resetForm();
               this.$eventBus.$emit(this.$evenBusConstant.REFRESH_TODO_LIST);
             }
             this.$feedback.showSuccessMessage(data.message);
           })
           .catch(() => {
             this.$feedback.showFailed('Something went wrong. Please try again!');
             this.changeDialogStatus();
           });
      },
    },
    updated() {
      let copyPayload;
      this.$eventBus.$on(this.$evenBusConstant.PASS_TODO_ITEM_FOR_EDIT, (payload) => {
        copyPayload = Object.assign({}, payload);
        if (!this.$validation.isEmptyObject(copyPayload)) {
          this.todo = copyPayload;
          this.pageInUpdateState = true;
        }
      });
    },
  };
</script>

<style scoped>

  .todo_form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .todo_form_title {
    font-size: 22px;
    font-weight: normal;
    font-stretch: normal;
    font-style: normal;
    line-height: 0.82;
    letter-spacing: normal;
    text-align: center;
    color: #464646;
    margin-top: 50px;
    margin-bottom: 30px;
  }

</style>
