<template>
  <v-container>
    <v-row class="text-center">

      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">
          Todo Item
        </h1>
        <create-todo></create-todo>

        <v-data-table
            :headers="headersForTodoTable"
            :items-per-page=numberOfTodo
            :items="todoList"
            class="elevation-1"
            :hide-default-footer="true"
            no-data-text="Todo didn't create yet..">

          <template v-slot:item.action="{item}">
            <div class="action-button-container">
              <v-chip outlined
                      @click="editTodo(item)"
                      color="info">Edit
              </v-chip>
              <v-chip
                  outlined
                  color="success"
                  style="margin-left: 15px"
                  @click="doneTodo(item)">Done
              </v-chip>
            </div>
          </template>
        </v-data-table>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import CreateTodo from '@/components/CreateTodo';

  export default {
    name: 'Todo',
    components: {
      CreateTodo
    },
    created() {
      this.getTodoList();
    },
    updated() {
      this.$eventBus.$on(this.$evenBusConstant.REFRESH_TODO_LIST, () => {
        this.getTodoList();
      });
    },
    data() {
      return {
        numberOfTodo: 0,
        headersForTodoTable: [
          {
            text: 'Item Name',
            align: 'start',
            sortable: false,
            width: '20%',
            value: 'itemName',
            class: 'table-header-text'
          },
          {text: 'Description', value: 'description', width: '45%', class: 'table-header-text'},
          {text: 'Date', value: 'date', width: '15%', class: 'table-header-text'},
          {text: 'Action', value: 'action', width: '20%', class: 'table-header-text'}
        ],
        todoList: []
      };
    },
    methods: {
      async editTodo(item) {
        await new Promise(resolve => {
          this.$eventBus.$emit(this.$evenBusConstant.SHOW_TODO_FORM);
          resolve();
        });
        this.$eventBus.$emit(this.$evenBusConstant.PASS_TODO_ITEM_FOR_EDIT, item);
      },

      async doneTodo(item) {
        let confirmed = await this.$feedback.getConfirmation();
        if (!confirmed) return;

        this.$restClient.delete('delete/'+item.id)
           .then(({data}) => {
             if (data.httpStatusCode === this.$httpStatusCode.OK) {
               this.getTodoList();
             }
             this.$feedback.showSuccessMessage(data.message);
           }).catch(() => {
              this.$feedback.showFailed('Something went wrong. Please try again!');
           });
      },

      getTodoList() {
        this.$restClient.get('list')
           .then(({data}) => {
             this.todoList = data.data;
             this.numberOfTodo = parseInt(this.todoList.length);
           })
           .catch(() => {
             this.$feedback.showFailed('Something went wrong. Please try again!');
           });
      }
    }
  };
</script>
