import Vue from 'vue'
import VueRouter from 'vue-router'
import TodoItemList from '../views/TodoItemList.vue'
import AddTodoItem from '../views/AddTodoItem.vue'
import UpdateTodoItem from '../views/UpdateTodoItem.vue'

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'TodoItemList',
        component: TodoItemList
    },
    {
        path: '/add-item',
        name: 'AddTodoItem',
        component: AddTodoItem

    },
    {
        path: '/update-item',
        name: 'UpdateTodoItem',
        component: UpdateTodoItem

    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router
