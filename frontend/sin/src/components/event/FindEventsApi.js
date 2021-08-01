import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../constants/ADDRESS";

const findEventsApi = () => {
  return (axios.get(BACKEND_ADDRESS + "/shop/goods/event")
  .then(response => response.data))
};

export default findEventsApi;