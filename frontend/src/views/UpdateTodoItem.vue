<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="add-item">

        <v-container class="mb-0 pb-0">
            <v-row justify="center" class="mb-0 pb-0">
                <v-col cols="10" sm="12" md="10" lg="10" xl="10" class="mb-0 pb-0">
                    <v-card class="mt-4">

                        <v-toolbar color="primary" cards dark flat>
                            <v-tooltip top>
                                <template v-slot:activator="{ on }">
                                    <v-btn icon :to="'/'" v-on="on">
                                        <v-icon>mdi-arrow-left</v-icon>
                                    </v-btn>
                                </template>
                                <span>Back to ToDo(s)</span>
                            </v-tooltip>

                            <v-card-title class="title font-weight-regular">Update ToDo</v-card-title>
                            <v-spacer></v-spacer>
                        </v-toolbar>

                        <v-form ref="form" v-model="form" class="pa-4 pt-6">

                            <v-text-field v-model="todoItem.itemName" :rules="[rules.required]" filled color="primary"
                                          label="Name"></v-text-field>
                            <v-datetime-picker v-model="todoItem.startDate" color="deep-purple"
                                               label="Pick a date & time"
                                               elevation="20"
                                               requred
                                               dateFormat="dd-MM-yyyy"
                                               :datePickerProps='{color : "primary"}'
                                               :timePickerProps='{color : "primary"}'
                                               :textFieldProps='{color : "primary", filled:true, rules:[rules.required]}'>
                                <v-btn text icon slot="dateIcon">
                                    <v-icon>mdi-calendar</v-icon>
                                </v-btn>
                                <v-btn text icon slot="timeIcon">
                                    <v-icon>mdi-clock-outline</v-icon>
                                </v-btn>
                            </v-datetime-picker>
                            <v-textarea v-model="todoItem.description" auto-grow filled color="primary"
                                        label="Description"
                                        rows="1"></v-textarea>
                            <v-checkbox v-model="todoItem.isDone" label="Is done?"></v-checkbox>
                        </v-form>

                        <v-divider></v-divider>


                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn :disabled="!form" class="white--text" color="primary accent-4" depressed
                                   @click="updateTodoItem">
                                Submit
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>

        <v-row justify="center">
            <v-dialog v-model="errorDialog" persistent max-width="290">
                <v-alert prominent type="error" class="mb-0">
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

    export default {
        data: () => ({
            form: false,
            todoItem: {
                id: undefined,
                description: undefined,
                itemName: undefined,
                startDate: undefined,
                isDone: undefined,
            },
            rules: undefined,
            errorDialog: false,
        }),
        created() {
            this.rules = this.$store.getters.getValidationRules;

            let id = this.$route.query.id;
            ToDoService.getTodoById(id).then((response) => {
                this.todoItem = response.data.data;
                if (this.todoItem.startDate) {
                    this.todoItem.startDate = new Date(this.todoItem.startDate);
                }
            }).catch((err) => {
                console.log(err);
            });
        },
        methods: {
            updateTodoItem: function () {
                ToDoService.updateToDo(this.todoItem).then((response) => {
                    if (response.status === 200) {
                        console.log("Status: " + response.data.status);
                        this.$router.push('/');
                    } else {
                        this.errorDialog = true;
                    }
                }).catch((err) => {
                    this.errorDialog = true;
                    console.log(err);
                });
            }
        }
    }
</script>