<template>
  <v-container>
    <v-row class="text-center">

      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">
          Todo Item
        </h1>


        <v-data-table
            :headers="headersForTodoTable"
            :items="todoList"
            class="elevation-1"
            :hide-default-footer="true"
            no-data-text="Todo didn't create yet.."
        >

          <template v-slot:item.action="{item}">
            <div class="action-button-container">
              <v-chip outlined
                      @click="editTodo(item)"
                      class="action-button">Edit
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
  export default {
    name: 'Todo',

    data: () => ({

      headersForTodoTable: [
        {
          text: 'Item Name',
          align: 'start',
          sortable: false,
          width: '20%',
          value: 'itemName',
          class: 'table-header-text'
        },
        {text: 'Description', value: 'description', width: '50%', class: 'table-header-text'},
        {text: 'Date', value: 'date', width: '10%', class: 'table-header-text'},
        {text: 'Action', value: 'action', width: '20%', class: 'table-header-text'}
      ],

      todoList: [
        {
          itemName: 'Tuli school',
          description: 'Govement primary school',
          date: new Date()
        },
        {
          itemName: 'Remove TV',
          description: 'Needed to clean house',
          date: new Date()
        },
        {
          itemName: 'Repair Car',
          description: 'It is regular house keeping work',
          date: new Date()
        }
      ],

    }),
    methods: {
      editTodo(item) {
        console.log(item);
      },

      deleteTodo(item) {
        console.log(item);
      }
    }
  }
</script>
