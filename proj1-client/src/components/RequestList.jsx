import React, { useState } from 'react';
import RequestForm from './RequestForm';
import Request from './Request';

function RequestList() {

  const [requests, setRequests] = useState([{ id: 1, text: "amount: 500, status: pending, date: 05/03/2020 " },
  { id: 2, text: "amount: 4, status: pending, date: 05/03/2020 " },
  { id: 3, text: "amount: 30, status: complete, date: 05/02/2020 " },
  { id: 4, text: "amount: 200, status: denied, date: 05/01/2020 " },
  { id: 5, text: "amount: 1111, status: complete, date: 05/01/2020 " }]);

  const addRequest = request => {
    if (!request.text || /^\s*$/.test(request.text)) {
      return;
    }
// 'amount: ' + todo + ' status: pending, date: 05/19/2021 '
    request.text = 'amount: ' + request.text + ' status: pending, date: 05/19/2021 '
    const newTodos = [request, ...requests];

    setRequests(newTodos);
    console.log(...requests);
  };

  const updateRequest = (todoId, newValue) => {
    if (!newValue.text || /^\s*$/.test(newValue.text)) {
      return;
    }

    setRequests(prev => prev.map(item => (item.id === todoId ? newValue : item)));
  };

  const removeRequest = id => {
    const removedArr = [...requests].filter(todo => todo.id !== id);

    setRequests(removedArr);
  };

  const completeRequest = id => {
    let updatedTodos = requests.map(todo => {
      if (todo.id === id) {
        todo.isComplete = !todo.isComplete;
      }
      return todo;
    });
    setRequests(updatedTodos);
  };

//   addTodo("amount: 500, status: pending, date: 05/04/2020 ");

  return (
      <div>
        <h1>Reimbursements</h1>
     
     <Request
       todos={requests}
       completeTodo={completeRequest}
       removeTodo={removeRequest}
       updateTodo={updateRequest}
     />

      <RequestForm onSubmit={addRequest} />
      </div>
  );
}

export default RequestList;