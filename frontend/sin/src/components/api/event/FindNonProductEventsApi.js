import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const findNonProductEventsApi = (name) => {
  return (axios.get(BACKEND_ADDRESS + "/main/event?name="+name)
  .then(response => response.data))
};

export default findNonProductEventsApi;