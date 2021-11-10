import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";
import {ACCESS_TOKEN} from "../../../constants/Sessionstorage";

const loginApi = (id, password, history) => {
  const body = {
    id: id,
    password: password
  };

  axios.post(BACKEND_ADDRESS + "/member/login", body)
  .then(response => {
    if (response.status === 200) {
      sessionStorage.setItem(ACCESS_TOKEN, response.data.token);
      alert("로그인 되었습니다.");
      history.push("/");
    }
  })
  .catch(error => {
    if (error.response.status === 401 || error.response.status === 400) {
      alert("가입하지 않은 이메일이거나, 잘못된 비밀번호 입니다");
      return Promise.reject();
    }
    alert("로그인 실패!");
    history.push("/");
  });
};

export default loginApi;