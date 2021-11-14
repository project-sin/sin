import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const mdChoiceApi = () => {
  return (axios.get(BACKEND_ADDRESS + '/main/index/md-choice')
  .then(response => response.data))
};


export default mdChoiceApi;