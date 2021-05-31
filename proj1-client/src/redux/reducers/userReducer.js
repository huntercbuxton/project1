import * as actions from '../actions.js';

export default function userReducer( state = {}, action) {
  switch (action.type){
    case actions.CURRENT_USER_STORED:
      return {...state, currentUser: action.payload};
    default: 
      return state;
  }
}