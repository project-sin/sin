import React from 'react';
import styled from 'styled-components';

const Signupwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div`padding: 0 200px;`

const Signuptitlewrap = styled.div`text-align: center; padding-top: 50px; padding-bottom:55px; border-bottom: 2px solid black;`
const Signuptitle = styled.strong`font-size: 30px;`
const Input = styled.input`width:330px`
const Th = styled.th`width:155px; text-align: left; padding: 20px 0px 0px 20px`
const Singupfooter = styled.div`padding-top: 40px;`
const Signupbutton = styled.button`display: block; width:230px; height:50px; margin: 0 auto;`

const Signup = () => {
    return (
        <Signupwrap>
            <Container>
                <Row>
                    <Signuptitlewrap><Signuptitle>회원가입</Signuptitle></Signuptitlewrap>
                    <table>
                        <tr>
                            <Th>아이디</Th>
                            <td><Input type='text' /></td>
                        </tr>
                        <tr>
                            <Th>비밀번호</Th>
                            <td><Input type='password' /></td>
                        </tr>
                        <tr>
                            <Th>비밀번호 확인</Th>
                            <td><Input type='password' /></td>
                        </tr>
                        <tr>
                            <Th>이름</Th>
                            <td><Input type='text' /></td>
                        </tr>
                        <tr>
                            <Th>이메일</Th>
                            <td><Input type='text' /></td>
                        </tr>
                        <tr>
                            <Th>휴대폰</Th>
                            <td><Input type='number' /></td>
                        </tr>
                        <tr>
                            <Th>주소</Th>
                            <td><button>주소검색</button></td>
                        </tr>
                        <tr>
                            <Th>성별</Th>
                            <td>
                                <input type='radio' name='sex' id='sex1' />
                                <label htmlFor='sex1'>남자</label>
                                <input type='radio' name='sex' id='sex2' />
                                <label htmlFor='sex2'>여자</label>
                                <input type='radio' name='sex' id='sex3' />
                                <label htmlFor='sex3'>선택안함</label>
                            </td>
                        </tr>
                        <tr>
                            <Th>생년월일</Th>
                            <td>
                                <input type='number' />
                                <input type='number' />
                                <input type='number' />
                            </td>
                        </tr>
                        <tr>
                            <Th>추가입력 사항</Th>
                            <td>
                                <input type='radio' name='option' id='option1' />
                                <label htmlFor='option1'>추천인 아이디</label>
                                <input type='radio' name='option' id='option2' />
                                <label htmlFor='option2'>참여 이벤트명</label>
                            </td>
                        </tr>
                        <tr>
                            <Th>이용약관동의</Th>
                            <td>
                                <div>
                                    <input type='checkbox' id='agree1' />
                                    <label htmlFor='agree1'>전체 동의합니다</label>
                                </div>
                                <div>
                                    <input type='checkbox' id='agree2' />
                                    <label htmlFor='agree2'>이용약관 동의</label>
                                </div>
                                <div>
                                    <input type='checkbox' id='agree3' />
                                    <label htmlFor='agree3'>개인정보 수집.이용 동의(필수)</label>
                                </div>
                                <div>
                                    <input type='checkbox' id='agree4' />
                                    <label htmlFor='agree4'>개인정보 수집.이용 동의(선택)</label>
                                </div>
                                <div>
                                    <input type='checkbox' id='agree5' />
                                    <label htmlFor='agree5'>무료배송,할인쿠폰 등 혜택/정보 수신 동의</label>
                                </div>
                                <div>
                                    <input type='checkbox' id='agree6' />
                                    <label htmlFor='agree6'>sms</label>
                                    <input type='checkbox' id='agree7' />
                                    <label htmlFor='agree7'>이메일</label>
                                </div>
                                <div>
                                    <input type='checkbox' id='agree8' />
                                    <label htmlFor='agree8'>본인은 만 14세 이상입니다.</label>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <Singupfooter>
                        <Signupbutton>가입하기</Signupbutton>
                    </Singupfooter>
                </Row>
            </Container>
        </Signupwrap>
    )
}

export default Signup;