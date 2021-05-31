import * as actions from '../actions.js';

export function loggingIn(user) {
  //user is expected to be an object as defined in our Spring endpoint
  return{
    type: actions.CURRENT_USER_STORED,
    payload: user
  }

}