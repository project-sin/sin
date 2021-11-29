import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const minusCountInCartApi = ({productCode, accessToken}) => {
  const body = {
    productCode: productCode,
    cnt: -1,
  };
  const config = {
    headers: {
      "Authorization": "Bearer " + accessToken
    }
  };
  axios.post(BACKEND_ADDRESS + "/goods/cart", body, config)
  .then(response => {
    if (response.status === 200) {
    }
  })
  .catch(error => {
    if (error.response.status === 401 || error.response.status === 403) {
      alert("로그인이 만료되었습니다. 다시 로그인해주세요.");
      return Promise.reject();
    } else if (error.response.status === 404 || error.response.status === 400) {
      alert("에러발생");
      return Promise.reject();
    }
    alert("뭔지 모르지만 에러, 홈으로..");
  });
};

export default minusCountInCartApi;