import React, { useEffect, useState } from 'react';


function TableView(props) {

  // const User = () => 
  const [items, setItems] = useState(0);
   
  useEffect(() => {
    getUsers();
  }, []);

  async function getUsers() {
    const response = await fetch('https://jsonplaceholder.typicode.com/users');
    setItems( await response());
  }
    
  return (
      <ul>
      {items.map(user => (
          <li>{item.name}</li>
      ))}
      </ul>
  );

}

export default TableView;