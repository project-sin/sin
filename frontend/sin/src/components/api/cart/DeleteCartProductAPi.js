import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const deleteCartProductAPi = (accessToken, query) => {
  const config = {
    headers: {
      "Authorization": "Bearer " + accessToken
    }
  };

  axios.delete(BACKEND_ADDRESS + "/goods/cart?productCode=" + query, config)
  .then(response => {
    if (response.status === 200) {
      alert(response.data);
    }
  })
  .catch(error => {
    if (error.response.status === 401 || error.response.status === 403) {
      alert("로그인이 만료되었습니다. 다시 로그인해주세요.");
    } else if (error.response.status === 400 || error.response.status === 404) {
      alert(error.response.data.message);
      return Promise.reject();
    } else {
      alert("글 삭제 실패");
    }
    return Promise.reject();
  });
}
export default deleteCartProductAPi;