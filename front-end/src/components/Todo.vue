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
            no-data-text="Todo didn't create yet.."
        >

          <template v-slot:item.action="{item}">
            <div class="action-button-container">
              <v-chip outlined
                      @click="editTodo(item)"
                      class="primary">Edit
              </v-chip>
              <v-chip
                  outlined
                  style="margin-left: 15px"
                  @click="deleteTodo(item)"
                  class="action-button">Delete
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
    components:{
      CreateTodo
    },
    created() {
      this.getTodoList();
    },
    updated(){
      this.$eventBus.$on(this.$evenBusConstant.REFRESH_TODO_LIST, ()=>{
       this.getTodoList();
      });
    },
    data(){
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
      }
    },
    methods: {
      async editTodo(item) {
        await new Promise(resolve => {
          this.$eventBus.$emit(this.$evenBusConstant.SHOW_TODO_FORM);
          resolve();
        });
        this.$eventBus.$emit(this.$evenBusConstant.PASS_TODO_ITEM_FOR_EDIT, item);
      },

      deleteTodo(item) {
        console.log(item);
      },

      getTodoList() {
        this.$restClient.get('list')
           .then(({data}) => {
             this.todoList = data.data;
             this.numberOfTodo = parseInt(this.todoList.length);
           })
           .catch(({response}) => {
             console.log(response);
           });
      },
    }
  }
</script>
