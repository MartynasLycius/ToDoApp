import axios from 'axios';
import BACKEND_API from '../resource/properties'

export default {

    getToDos() {
        console.log(BACKEND_API.baseUrl);
        return axios.get(BACKEND_API.baseUrl + '/v1/todos?page=0&size='+ ( 2147483648/2) );
    },
    createToDo(todoItem) {

        let headers = {
            'Content-Type': 'application/json'
        };
        return axios.post(BACKEND_API.baseUrl + '/v1/todos', todoItem, {headers: headers});
    },
    updateToDo(todoItem) {
        let headers = {
            'Content-Type': 'application/json'
        };
        return axios.put(BACKEND_API.baseUrl + '/v1/todos/' + todoItem.id, todoItem, {headers: headers});
    },
    getTodoById(id) {
        return axios.get(BACKEND_API.baseUrl + '/v1/todos/' + id);
    },
    deleteTodoById(id) {
        return axios.delete(BACKEND_API.baseUrl + '/v1/todos/' + id);
    }
}
