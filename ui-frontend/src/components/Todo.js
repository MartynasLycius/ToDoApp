import React, { useState } from 'react';
import TodoInput from './TodoInput';
import { RiCloseCircleLine } from 'react-icons/ri';
import { TiEdit } from 'react-icons/ti';

const Todo = ({ todos, completeTodo, removeTodo, updateTodo }) => {
  const [edit, setEdit] = useState({
    id: null,
    value: ''
  });

  const submitUpdate = value => {
    updateTodo(edit.id, value);
    setEdit({
      id: null,
      value: ''
    });
  };

  if (edit.id) {
    return <TodoInput edit={edit} onSubmit={submitUpdate} />;
  }

  return todos.map((attrs) => (
    //    {todo_complete: false, todo_id: 0, todo_text: ""}
    <div
      className={attrs.todo_complete ? 'todo-row complete' : 'todo-row'}
      key={attrs.todo_id}
    >
      <div className="todo-text"  key={attrs.todo_id} onClick={() => completeTodo(attrs.todo_id)}>
        {attrs.todo_text}
      </div>
      <div className='icons'>
        <RiCloseCircleLine
          onClick={() => removeTodo(attrs.todo_id)}
          className='delete-icon'
        />
        <TiEdit
          onClick={() => setEdit({ id: attrs.todo_id, value: attrs.todo_text })}
          className='edit-icon'
        />
      </div>
    </div>
  ));
};

export default Todo;
