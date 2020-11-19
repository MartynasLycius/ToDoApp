async function fetchGraphQL(operationsDoc, operationName, variables) {
  const urlHasura = "https://om-todo.hasura.app/v1/graphql"
  //const urlHasura = "http://localhost:8080/graphql";

  const result = await fetch(urlHasura, {
    method: "POST",
    body: JSON.stringify({
      query: operationsDoc,
      variables: variables,
      operationName: operationName,
    }),
  });

  return await result.json();
}

const operationsDoc = `
    query deleted_count {
      todo_stats {
        counter
      }
    }
    
    query list_todos {
      todo {
        todo_complete
        todo_id
        todo_text
      }
    }
    
    mutation add_todo($todo_text: String = "hello world 2") {
      insert_todo(objects: {todo_text: $todo_text}) {
        returning {
          todo_id
        }
      }
    }
    
    mutation update_todo_complete($_eq: Int = 0, $todo_complete: Boolean = false) {
      update_todo(where: {todo_id: {_eq: $_eq}}, _set: {todo_complete: $todo_complete}) {
        returning {
          todo_id
        }
      }
    }

    mutation update_todo_text($_eq: Int = 0, $todo_text: String = "") {
        update_todo(where: {todo_id: {_eq: $_eq}}, _set: {todo_text: $todo_text}) {
          returning {
            todo_id
          }
        }
    }
    
    mutation inc_deletes {
      update_todo_stats(where: {id: {_eq: 2}}, _inc: {counter: 1}) {
        returning {
          counter
        }
      }
    }
    
    mutation todo_delete($_eq: Int = 0) {
      delete_todo(where: {todo_id: {_eq: $_eq}}) {
        returning {
          todo_id
        }
      }
    }
  `;

function fetchDeleted_count() {
  return fetchGraphQL(operationsDoc, "deleted_count", {});
}

function fetchList_todos() {
  return fetchGraphQL(operationsDoc, "list_todos", {});
}

function executeAdd_todo(todo_text) {
  return fetchGraphQL(operationsDoc, "add_todo", { todo_text: todo_text });
}

function executeUpdate_todo_complete(_eq, todo_complete) {
  return fetchGraphQL(operationsDoc, "update_todo_complete", {
    _eq: _eq,
    todo_complete: todo_complete,
  });
}

function executeUpdate_todo_text(_eq, todo_text) {
  return fetchGraphQL(operationsDoc, "update_todo_text", {
    _eq: _eq,
    todo_text: todo_text,
  });
}

function executeInc_deletes() {
  return fetchGraphQL(operationsDoc, "inc_deletes", {});
}

function executeTodo_delete(_eq) {
  return fetchGraphQL(operationsDoc, "todo_delete", { _eq: _eq });
}

async function startFetchDeleted_count() {
  const { errors, data } = await fetchDeleted_count();
  return handleData(errors, data);
}

async function startFetchList_todos() {
  const { errors, data } = await fetchList_todos();
  return handleData(errors, data);
}

async function startExecuteAdd_todo(todo_text) {
  const { errors, data } = await executeAdd_todo(todo_text);
  return handleData(errors, data);
}

async function startExecuteUpdate_todo_complete(_eq, todo_complete) {
  const { errors, data } = await executeUpdate_todo_complete(
    _eq,
    todo_complete
  );
  return handleData(errors, data);
}

async function startExecuteUpdate_todo_text(_eq, todo_text) {
  const { errors, data } = await executeUpdate_todo_text(_eq, todo_text);
  return handleData(errors, data);
}

async function startExecuteInc_deletes() {
  const { errors, data } = await executeInc_deletes();
  return handleData(errors, data);
}

async function startExecuteTodo_delete(_eq) {
  const { errors, data } = await executeTodo_delete(_eq);
  return handleData(errors, data);
}

function handleData(errors, data) {
  if (errors) {
    // handle these errors
    console.error(errors);
  }

  return data;
}

export {
  startExecuteTodo_delete,
  startExecuteInc_deletes,
  startExecuteUpdate_todo_complete,
  startExecuteAdd_todo,
  startFetchDeleted_count,
  startFetchList_todos,
  startExecuteUpdate_todo_text,
};
