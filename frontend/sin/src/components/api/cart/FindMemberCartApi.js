import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const findMemberCartApi = (accessToken) => {
  const config = {
    headers: {
      "Authorization": "Bearer " + accessToken
    }
  };

  return (axios.get(BACKEND_ADDRESS + "/goods/cart/member", config)
  .then(response => response.data))
};

export default findMemberCartApi;