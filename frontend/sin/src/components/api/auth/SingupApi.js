import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const signUpApi = (id, email, password, name, phoneNumber, gender, birth, history) => {
  const body = {
    id: id,
    email: email,
    password: password,
    name: name,
    phone_number: phoneNumber,
    address: "address",
    gender: gender,
    birth: birth
  };

  axios.post(BACKEND_ADDRESS + "/member/join", body)
  .then(response => {
    if (response.status === 200) {
      alert(response.data);
      history.push("/shop/member/login");
    }
  })
  .catch(error => {
    if (error.response.status === 401 || error.response.status === 400) {
      alert("에러발생");
      return Promise.reject();
    }
    alert("뭔지 모르지만 회원가입실패?! 홈으로..");
    history.push("/");
  });
};

export default signUpApi;