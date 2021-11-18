<template>
  <v-form v-model="isValid">
    <div class="todo_form">
      <span v-if="this.pageInUpdateState==false" class="todo_form_title">Create Todo</span>
      <span v-if="this.pageInUpdateState==true" class="todo_form_title">Update Todo</span>
      <v-text-field
          v-model="todo.itemName"
          label="Enter task name"
          :rules="itemNameRules"
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
            :rules="dateSelectionRules"
            v-bind="attrs"
            v-on="on"
        ></v-text-field>
      </template>
        <v-date-picker
            v-model="todo.date"
            @input="menu2 = false">
        </v-date-picker>
      </v-menu>
      <div @click="fireFileUploadEvent">
        <v-file-input
            id="uploadFile"
            style="width: 100%; margin-top: 10px;width: 100%;margin-top: 10px; margin-right: 100px;"
            @change="handleImageUpload"
            @click:clear="handleFileClear"
            accept="image/png, image/jpeg, image/jpg"
            label="Upload Your Logo"
            prepend-icon="mdi-paperclip"
            small-chips
            show-size
            required
            :rules="generalRules"
            dense
            outlined>
        </v-file-input>
      </div>

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
               class="primary"
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
          noteImage: '',
        },
        dialog: false,
        isValid: false,
        pageInUpdateState: false,
        itemNameRules: [
          (v) => !!v || 'Todo Item Name is required'
        ],
        dateSelectionRules: [
          (v) => !!v || "Date is required"
        ],
        generalRules: [
          v => !!v || 'Input is required',
        ]
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
           .catch(({data}) => {
             this.$feedback.showFailed(data.message);
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
           .catch(({data}) => {
             this.$feedback.showFailed(data.message);
             this.changeDialogStatus();
           });
      },
      fireFileUploadEvent() {
        //document.getElementById('uploadFile').click();
      },
      handleImageUpload(file) {
        if (window.event.type !== 'change') return;
        if (!file) return;

        let extension = file.type.split('/')[1];
        console.log(extension);

        //this.persistLogoFile(file);

        let reader = new FileReader();

        reader.readAsDataURL(file)
        reader.onload = () => {
          let base64Image = reader.result;
          base64Image = base64Image.split(',')[1]
          this.todo.noteImage = base64Image;
          console.log(this.todo.noteImage);
          // this.$store.commit('setPageInfoProperty', {key: 'logo', value: base64Image});
        };
      },
      handleFileClear() {
        // this.$store.commit('setPageInfoProperty', {key: 'logo', value: ''});
        // this.$store.commit('setLogoFile', {})
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
  .banner-image-div {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 80%;
    border: solid 1px #f3f3f3;
    background-color: #f3f3f3;
  }


</style>
