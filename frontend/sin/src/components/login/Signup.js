import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import signUpApi from "../api/auth/SingupApi";
import checkIsDuplicateEmail from "../api/auth/CheckIsDuplicateEmail";
import checkIsDuplicateId from "../api/auth/CheckIsDuplicateId";
import Header from "../Header";

const Signupwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div`padding: 0 200px;`

const Table = styled.table`border-collapse: collapse; border-spacing: 0;`

const Signuptitlewrap = styled.div`text-align: center; padding-top: 50px; padding-bottom:55px; border-bottom: 2px solid black;`
const Signuptitle = styled.strong`font-size: 25px;`
const Input = styled.input`width:330px; font-size: 14px; color: #333; box-sizing: border-box; padding: 0 14px; background: #fff; border: 1px solid #ccc; border-radius: 3px; height: 45px;`
const Button = styled.button` cursor: pointer; margin-left: -140px; width:100px; font-size: 14px; color: rgb(112,48,160); border-box; background: #fff; border: 1px solid rgb(112,48,160); border-radius: 3px; height: 45px;`
const Addressbtn = styled.button`width:330px; font-size: 14px; color: #5f0080; background: #fff; border: 1px solid #5f0080; border-radius: 3px; height: 45px; font-weight: bold; cursor: pointer;`
const Th = styled.th`width:155px; font-size: 14px; text-align: left; padding: 30px 0px 20px 20px; color: #333333;`
const Singupfooter = styled.div`padding-top: 40px;`
const Signupbutton = styled.button`display: block; width:240px; height:56px; font-size: 16px; background: #5f0080; border: 1px solid #5f0080; color: #fff; cursor: pointer; margin: 0 auto;`
const Sexradio = styled.input`margin-right: 10px;`
const Sexlabel = styled.label`padding: 10px 52px 10px 0; font-size: 14px; color: #4c4c4c;`
const Birthtd = styled.td`width: 330px; margin-top: 15px; display: flex; border: 1px solid #ccc;`
const Birthinput = styled.input`width: 33%; height: 40px; background: none; border: none; text-align: center;`

const Signupbot = styled.tr``
const Signupbotth = styled.th`padding-top: 20px; border-top: 1px solid #333;`
const Signupbottd = styled.td`padding-top: 20px; border-top: 1px solid #333;`
const Agreediv = styled.div`width: 480px; height: 40px;`
const Agreeinput = styled.input`margin-right: 10px;`
const Agreelabel = styled.label`font-size: 14px; color: #4c4c4c;`

const Signup = (props) => {

  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [checkPassword, setCheckPassword] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [gender, setGender] = useState("");
  const [birthYear, setBirthYear] = useState("");
  const [birthMonth, setBirthMonth] = useState("");
  const [birthDay, setBirthDay] = useState("");

  const [checkDuplicatedId, setCheckDuplicatedId] = useState(false);
  const [checkDuplicatedEmail, setCheckDuplicatedEmail] = useState(false);

  const [address, setAddress] = useState("");
  const [detailedAddress, setDetailedAddress] = useState("");

  const signup = () => {
    if (document.getElementById("address").style.display !== 'none'
        && id.length && password.length && checkPassword.length && name.length
        && email.length && phoneNumber.length && gender.length && birthYear.length
        && birthMonth.length && birthDay.length) {
      if (password === checkPassword) {
        if (birthYear >= 0 && birthMonth >= 1 && birthMonth <= 12 && birthDay
            >= 1 && birthDay <= 31) {
          if (checkDuplicatedId === true && checkDuplicatedEmail === true) {
            const birth = birthYear + birthMonth + birthDay;
            signUpApi(id, email, password, name, phoneNumber, gender, birth,
                document.getElementById(
                    "pInput").value, document.getElementById(
                    "pDetailedInput").value, props.history
            )
            ;
          } else {
            alert("?????????, ????????? ??????????????? ??????????????????");
          }
        } else {
          alert("??????????????? ???????????? ????????? ?????????");
        }
      } else {
        alert("??????????????? ???????????? ????????????");
      }
    } else {
      alert("????????? ??? ???????????????");
    }
  };

  useEffect(() => {
    document.getElementById("address").style.display = 'none';
    document.getElementById("detailedAddress").style.display = 'none';
  }, []);

  return (
      <>
        <Header/>
        <Signupwrap>
          <Container>
            <div id='popupDom'>
            </div>
            <Row>
              <Signuptitlewrap><Signuptitle>????????????</Signuptitle></Signuptitlewrap>
              <Table>
                <tr>
                  <Th>?????????</Th>
                  <td><Input type='text'
                             value={id}
                             onChange={e => {
                               setId(e.target.value)
                               setCheckDuplicatedId(false)
                             }}
                             placeholder="6??? ????????? ?????? ?????? ????????? ????????? ??????"/></td>
                  <td>
                    <Button onClick={() => {
                      checkIsDuplicateId(id, setCheckDuplicatedId).then(message => {
                        if (message != null) {
                          setCheckDuplicatedId(true);
                          alert(message);
                        }
                      })
                    }}>????????????</Button>
                  </td>
                </tr>
                <tr>
                  <Th>????????????</Th>
                  <td><Input type='password'
                             value={password}
                             onChange={e => setPassword(e.target.value)}
                             placeholder="??????????????? ??????????????????"/></td>
                </tr>
                <tr>
                  <Th>???????????? ??????</Th>
                  <td><Input type='password'
                             value={checkPassword}
                             onChange={e => setCheckPassword(e.target.value)}
                             placeholder="??????????????? ?????? ??? ??????????????????"/>
                  </td>
                </tr>
                <tr>
                  <Th>??????</Th>
                  <td><Input type='text'
                             value={name}
                             onChange={e => setName(e.target.value)}
                             placeholder="????????? ??????????????????"/></td>
                </tr>
                <tr>
                  <Th>?????????</Th>
                  <td><Input type='text'
                             value={email}
                             onChange={e => {
                               setEmail(e.target.value)
                               setCheckDuplicatedEmail(false)
                             }}
                             placeholder="???: marketkurly@kurly.com"/>
                  </td>
                  <td>
                    <Button onClick={() => {
                      checkIsDuplicateEmail(email, setCheckDuplicatedEmail).then(message => {
                        if (message != null) {
                          setCheckDuplicatedEmail(true);
                          alert(message);
                        }
                      })
                    }}>????????????</Button>
                  </td>
                </tr>
                <tr>
                  <Th>?????????</Th>
                  <td><Input type='number'
                             value={phoneNumber}
                             onChange={e => setPhoneNumber(e.target.value)}
                             placeholder="????????? ??????????????????"/></td>
                </tr>
                <tr id="address">
                  <Th>??????</Th>
                  <td><Input type='text'
                             id="pInput"
                             onChange={e => setAddress(e.target.value)}
                             readOnly={true}
                  /></td>
                  <td>
                    <Button onClick={() => {
                      window.name = "parentForm";
                      window.open('/shop/address', 'childForm',
                          'top=10, left=10, width=650, height=600, status=no, menubar=no, toolbar=no, resizable=no');
                    }}>?????????</Button>
                  </td>
                </tr>
                <tr id="detailedAddress">
                  <Th></Th>
                  <td><Input type='text'
                             id="pDetailedInput"
                             onChange={e => {
                               console.log(e.target.value)
                               setDetailedAddress(
                                   e.target.value)
                             }}
                             placeholder="????????????" readOnly={true}/></td>
                </tr>
                <tr id="search">
                  <Th>??????</Th>
                  <td><Addressbtn onClick={() => {
                    window.name = "parentForm";
                    window.open('/shop/address', 'childForm',
                        'top=10, left=10, width=650, height=600, status=no, menubar=no, toolbar=no, resizable=no');
                  }}>????????????</Addressbtn></td>
                </tr>
                <tr>
                  <Th>??????</Th>
                  <td>
                    <Sexradio type='radio'
                              name='sex'
                              value={gender}
                              onChange={e => setGender('Male')}
                              id='sex1'/>
                    <Sexlabel htmlFor='sex1'>??????</Sexlabel>
                    <Sexradio type='radio'
                              name='sex'
                              value={gender}
                              onChange={e => setGender('Female')}
                              id='sex2'/>
                    <Sexlabel htmlFor='sex2'>??????</Sexlabel>
                  </td>
                </tr>
                <tr>
                  <Th>????????????</Th>
                  <Birthtd>
                    <Birthinput type='number'
                                value={birthYear}
                                onChange={e => setBirthYear(e.target.value)}
                                placeholder='YYYY'/>
                    <Birthinput type='number'
                                value={birthMonth}
                                onChange={e => setBirthMonth(e.target.value)}
                                placeholder='MM'/>
                    <Birthinput type='number'
                                value={birthDay}
                                onChange={e => setBirthDay(e.target.value)}
                                placeholder='DD'/>
                  </Birthtd>
                </tr>
                <tr>
                  <Th>???????????? ??????</Th>
                  <td>
                    <Sexradio type='radio' name='option' id='option1'/>
                    <Sexlabel htmlFor='option1'>????????? ?????????</Sexlabel>
                    <Sexradio type='radio' name='option' id='option2'/>
                    <Sexlabel htmlFor='option2'>?????? ????????????</Sexlabel>
                  </td>
                </tr>
                <Signupbot>
                  <Signupbotth>??????????????????</Signupbotth>
                  <Signupbottd>
                    <Agreediv>
                      <Agreeinput type='checkbox' id='agree1'/>
                      <Agreelabel htmlFor='agree1'>?????? ???????????????</Agreelabel>
                    </Agreediv>
                    <Agreediv>
                      <Agreeinput type='checkbox' id='agree2'/>
                      <Agreelabel htmlFor='agree2'>???????????? ??????</Agreelabel>
                    </Agreediv>
                    <Agreediv>
                      <Agreeinput type='checkbox' id='agree3'/>
                      <Agreelabel htmlFor='agree3'>???????????? ??????.??????
                        ??????(??????)</Agreelabel>
                    </Agreediv>
                    <Agreediv>
                      <Agreeinput type='checkbox' id='agree4'/>
                      <Agreelabel htmlFor='agree4'>???????????? ??????.??????
                        ??????(??????)</Agreelabel>
                    </Agreediv>
                    <Agreediv>
                      <Agreeinput type='checkbox' id='agree5'/>
                      <Agreelabel htmlFor='agree5'>????????????,???????????? ??? ??????/?????? ??????
                        ??????</Agreelabel>
                    </Agreediv>
                    <Agreediv>
                      <Agreeinput type='checkbox' id='agree6'/>
                      <Agreelabel
                          htmlFor='agree6'>sms&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Agreelabel>
                      <Agreeinput type='checkbox' id='agree7'/>
                      <Agreelabel htmlFor='agree7'>?????????</Agreelabel>
                    </Agreediv>
                    <Agreediv>
                      <Agreeinput type='checkbox' id='agree8'/>
                      <Agreelabel htmlFor='agree8'>????????? ??? 14??? ???????????????.</Agreelabel>
                    </Agreediv>
                  </Signupbottd>
                </Signupbot>
              </Table>
              <Singupfooter>
                <Signupbutton onClick={() => signup()}>????????????</Signupbutton>
              </Singupfooter>
            </Row>
          </Container>
        </Signupwrap>
      </>
  )
}

export default Signup;