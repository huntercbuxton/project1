import React, { useState } from 'react';
import RequestForm from './RequestForm';
import Request from './Request';

function RequestList() {

  const [todos, setTodos] = useState([{ id: 1, text: "amount: 500, status: pending, date: 05/03/2020 " },
  { id: 2, text: "amount: 4, status: pending, date: 05/03/2020 " },
  { id: 3, text: "amount: 30, status: complete, date: 05/02/2020 " },
  { id: 4, text: "amount: 200, status: denied, date: 05/01/2020 " },
  { id: 5, text: "amount: 1111, status: complete, date: 05/01/2020 " }]);

  const addTodo = todo => {
    if (!todo.text || /^\s*$/.test(todo.text)) {
      return;
    }
// 'amount: ' + todo + ' status: pending, date: 05/19/2021 '
    todo.text = 'amount: ' + todo.text + ' status: pending, date: 05/19/2021 '
    const newTodos = [todo, ...todos];

    setTodos(newTodos);
    console.log(...todos);
  };

  const updateTodo = (todoId, newValue) => {
    if (!newValue.text || /^\s*$/.test(newValue.text)) {
      return;
    }

    setTodos(prev => prev.map(item => (item.id === todoId ? newValue : item)));
  };

  const removeTodo = id => {
    const removedArr = [...todos].filter(todo => todo.id !== id);

    setTodos(removedArr);
  };

  const completeTodo = id => {
    let updatedTodos = todos.map(todo => {
      if (todo.id === id) {
        todo.isComplete = !todo.isComplete;
      }
      return todo;
    });
    setTodos(updatedTodos);
  };

//   addTodo("amount: 500, status: pending, date: 05/04/2020 ");

  return (
      <div>
        <h1>Reimbursements</h1>
     
     <Request
       todos={todos}
       completeTodo={completeTodo}
       removeTodo={removeTodo}
       updateTodo={updateTodo}
     />

      <RequestForm onSubmit={addTodo} />
      </div>
  );
}

export default RequestList;