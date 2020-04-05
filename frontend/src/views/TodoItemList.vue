<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="todo-list">

        <v-container class="mb-0 pb-0">
            <v-row justify="center" class="mb-0 pb-0">
                <v-col cols="10" sm="12" md="10" lg="10" xl="10" class="mb-0 pb-0">
                    <v-card class="mt-4">
                        <v-toolbar color="primary" cards dark flat>
                            <v-btn icon>
                                <v-icon>mdi-arrow-down</v-icon>
                            </v-btn>
                            <v-card-title class="title font-weight-regular">ToDo(s)</v-card-title>
                            <v-spacer></v-spacer>

                            <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details
                                          class="mr-10"/>

                            <v-tooltip top>
                                <template v-slot:activator="{ on }">
                                    <v-btn class="mb-2" fab dark small color="primary" :to="'/add-item'" v-on="on">
                                        <v-icon dark>mdi-plus</v-icon>
                                    </v-btn>
                                </template>
                                <span>New ToDo</span>
                            </v-tooltip>

                        </v-toolbar>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>

        <v-container class="mt-0 pt-0">
            <v-row justify="center" class="mt-0 pt-0">
                <v-col cols="10" sm="12" md="10" lg="10" xl="10" class="mt-0 pt-0">
                    <v-card class="mt-4">
                        <v-data-table :headers="headers" :items="todoList" :search="search" fixed-header height="70vh">


                            <template v-slot:item.description="{ item }">
                                <v-layout style="text-align: justify" class="pl-0">
                                        {{ item.description }}
                                </v-layout>
                            </template>

                            <template v-slot:item.isDone="{ item }">
                                <v-layout justify-center>
                                <v-checkbox v-model="item.isDone" disabled></v-checkbox>
                                </v-layout>
                            </template>

                            <template v-slot:item.actions="{ item }">
                                <v-tooltip top>
                                    <template v-slot:activator="{ on }">
                                        <v-icon small class="mr-2" @click="editTodoItem(item)" v-on="on"> mdi-pencil</v-icon>
                                    </template>
                                    <span>Edit ToDo</span>
                                </v-tooltip>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on }">
                                        <v-icon small @click="deleteTodoItem(item)" v-on="on"> mdi-delete</v-icon>
                                    </template>
                                    <span>Delete ToDo</span>
                                </v-tooltip>

                            </template>

                            <template v-slot:no-data>
                                <v-alert outlined type="warning" prominent border="left" class="mt-5">
                                    No todo item found
                                </v-alert>

                            </template>
                        </v-data-table>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>


        <v-row justify="center">
            <v-dialog v-model="errorDialog" persistent max-width="290">
                <v-alert  prominent type="error" class="mb-0">
                    <v-row align="center">
                        <v-col class="grow">Oops! Something sent wrong!!</v-col>
                        <v-col class="shrink">
                            <v-btn @click="errorDialog = false">OK</v-btn>
                        </v-col>
                    </v-row>
                </v-alert>
            </v-dialog>
        </v-row>
    </div>
</template>

<script>

    import ToDoService from "../service/TodoService";
    import moment from 'moment';

    export default {
        name: "TodoList",
        components: {},
        data: () => ({
            search: "",
            errorDialog: false,
            headers: [],
            todoList: [],
        }),

        created() {

            this.headers =  this.$store.getters.getDataTableHeaders;

            ToDoService.getToDos().then((response) => {
                if (response.status === 200) {
                    this.todoList = response.data.data.map(item => {
                        item.startDate = moment(String(item.startDate)).format('DD MMMM, YYYY - hh:mm A');
                        return item;
                    });
                }else {
                    this.errorDialog = true;
                }
            }).catch((err) => {
                this.errorDialog = true;
                console.log(err);
            });
        },

        methods: {

            editTodoItem(item) {

                this.$router.push('/update-item?id=' + item.id)
            },
            deleteTodoItem(item) {

                /*const index = this.todoList.indexOf(item)
                confirm('Are you sure you want to delete this item?') && this.todoList.splice(index, 1);*/

                if(confirm('Are you sure you want to delete this item?')) {
                    ToDoService.deleteTodoById(item.id).then((response) => {
                        console.log("Status: " + response.status);
                        if (response.status === 204) {
                            console.log("DELETE SUCCESS : " + response.data.status);
                            const index = this.todoList.indexOf(item)
                            this.todoList.splice(index, 1);
                        } else {
                            this.errorDialog = true;
                        }
                    }).catch((err) => {
                        this.errorDialog = true;
                        console.log(err);
                    });
                }
            },

        },
    }
</script>

<style scoped>
    .isDoneItem{
        backround: read;
    }
</style>
