import React, { useState, useEffect } from "react";
import TodoInput from "./TodoInput";
import Todo from "./Todo";
import { RiHeartFill } from "react-icons/ri";
import { AiFillGithub } from "react-icons/ai";
import {
  startExecuteTodo_delete,
  startExecuteInc_deletes,
  startExecuteUpdate_todo_complete,
  startExecuteAdd_todo,
  startFetchList_todos,
  startExecuteUpdate_todo_text,
} from "./GraphCall";
import {context} from "./context";


function TodoView() {


  const [todos, setTodos] = useState([
    //    {todo_complete: false, todo_id: 0, todo_text: ""}
  ]);

  const populateTodos = async () => {
    if (context.count === 0) {
      context.count=1
      
      //get remote data list
      let data = await startFetchList_todos();
      if (data !== undefined && data.todo !== undefined) {
        setTodos(data.todo);
      }
      console.log(data);
    }
  };

  const addTodo = async (todo) => {
    if (!todo.todo_text || /^\s*$/.test(todo.todo_text)) {
      return;
    }
    todo.todo_complete = false;
    todo.todo_id = Math.floor(Math.random()*10e10);

    const newTodos = [todo, ...todos];
    setTodos(newTodos);

    //add into the remote
    let data = await startExecuteAdd_todo(todo.todo_text);
    if (data !== undefined && data.insert_todo !== undefined) {
      todo.todo_id = data.insert_todo.returning[0].todo_id;
    }
    console.log(...todos);
  };

  const updateTodo = (todoId, newValue) => {
    if (!newValue.todo_text || /^\s*$/.test(newValue.todo_text)) {
      return;
    }

    setTodos((prev) =>
      prev.map((item) => (item.todo_id === todoId ? newValue : item))
    );

    //update the remote data
    startExecuteUpdate_todo_text(todoId, newValue.todo_text);
  };

  const removeTodo = async (id) => {
    const removedArr = [...todos].filter((todo) => todo.todo_id !== id);
    setTodos(removedArr);

    //update the remote data
    let data = await startExecuteTodo_delete(id);
    if (
      data !== undefined &&
      data.delete_todo !== undefined &&
      data.delete_todo.returning.length > 0
    ) {
      startExecuteInc_deletes();
    }
  };

  const completeTodo = (id) => {
    let updatedTodos = todos.map((todo) => {
      if (todo.todo_id === id) {
        //update the remote data
        startExecuteUpdate_todo_complete(id, !todo.todo_complete);
        todo.todo_complete = !todo.todo_complete;
      }
      return todo;
    });
    setTodos(updatedTodos);
  };

  useEffect(() => {
    if (context.count === 0) {
      populateTodos();
    }
  });

  return (
    <>
      <h1>Add task to your todo's list</h1>
      <h3>
        ...made with <RiHeartFill className="red-icon" /> at&nbsp;
        <a href="https://github.com/omar391" target="_blank" rel="noreferrer">
          <AiFillGithub className="red-icon" />
          /omar391
        </a>
      </h3>
      <TodoInput onSubmit={addTodo} />
      <Todo
        todos={todos}
        completeTodo={completeTodo}
        removeTodo={removeTodo}
        updateTodo={updateTodo}
      />
    </>
  );
}

export default TodoView;
