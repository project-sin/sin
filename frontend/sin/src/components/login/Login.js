import React, {useState} from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import loginApi from "../api/auth/LoginApi";
import Header from "../Header";

const Loginwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div`padding: 0 350px;`

const Logintitle = styled.div`font-size: 20px; font-weight: bold; text-align: center; padding-top: 100px; padding-bottom: 30px;`
const Loginform = styled.div``
const Logininput = styled.input`display: block; width: 100%; box-sizing: border-box; height: 50px; padding: 0 20px; margin-top: 10px;`

const Loginadd = styled.div`padding-top: 20px; font-size: 12px; font-weight: bold;`
const Loginaddsecurity = styled.div`float: left;`
const Loginaddforget = styled.div`float: right;`

const Loginfooter = styled.div`padding: 20px 0;`
const Loginfooterbuttonlogin = styled.button`display: block; width: 100%; height: 50px; color: #fff; font-size: 16px; font-weight: bold; box-sizing: border-box; margin-top: 10px; background: #5f0080; border: 1px solid #5f0080;`
const Loginfooterbuttonsignup = styled.button`display: block; width: 100%; height: 50px; color: #5f0080; font-size: 16px; font-weight: bold; box-sizing: border-box; margin-top: 10px; border: 1px solid #5f0080;`

const Login = ({history}) => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const login = () => {
    loginApi(id, password, history);
  };

  return (
      <>
        <Header/>
        <Loginwrap>
          <Container>
            <Row>
              <Logintitle>로그인</Logintitle>
              <Loginform>
                <Logininput type='text'
                            value={id}
                            onChange={e => setId(e.target.value)}
                            placeholder='아이디를 입력해주세요'/>
                <Logininput type='password'
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            placeholder='비밀번호를 입력해주세요'/>
                <Loginadd className='clearfix'>
                  <Loginaddsecurity>
                    <input type='checkbox' id='security'/>
                    <label htmlFor='security'>보안접속</label>
                  </Loginaddsecurity>
                  <Loginaddforget>
                    <Link to=''>아이디 찾기</Link>/<Link to=''>비밀번호 찾기</Link>
                  </Loginaddforget>
                </Loginadd>
                <Loginfooter>
                  <Loginfooterbuttonlogin
                      onClick={() => login()}>로그인</Loginfooterbuttonlogin>
                  <Loginfooterbuttonsignup onClick={() => history.push(
                      '/shop/member/join')}>회원가입</Loginfooterbuttonsignup>
                </Loginfooter>
              </Loginform>
            </Row>
          </Container>
        </Loginwrap>
      </>
  )
}

export default Login;