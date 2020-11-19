import React, { useState, useEffect, useRef } from "react";
import {PieChartGraph} from './PieChartGraph';

function TodoInput(props) {
  const [input, setInput] = useState(props.edit ? props.edit.value : "");
  // const [modal, setModalState] = useState(0);

  // useEffect(() => {
  //   if (modal === 0) {
  //     setModalState(1);
  //   }
  // });

  const inputRef = useRef(null);

  useEffect(() => {
    inputRef.current.focus();
  });

  const handleChange = (e) => {
    setInput(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    props.onSubmit({
      todo_text: input,
    });

    setInput("");
  };

  return (
    <form onSubmit={handleSubmit} className="todo-form">
      {props.edit ? (
        <>
          <input
            placeholder="Update your item"
            value={input}
            onChange={handleChange}
            name="text"
            ref={inputRef}
            className="todo-input edit"
          />
          <button onClick={handleSubmit} className="todo-button edit">
            Update
          </button>
        </>
      ) : (
        <>
          <input
            placeholder="Write your task here"
            value={input}
            onChange={handleChange}
            name="text"
            className="todo-input"
            ref={inputRef}
          />
          <button onClick={handleSubmit} className="todo-button">
            Add todo
          </button>
          <PieChartGraph/>
        </>
      )}
    </form>
  );
}

export default TodoInput;
