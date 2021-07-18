import React from 'react';
import { Route, Link } from 'react-router-dom';
import styled from 'styled-components';
import Mypage_orderlist from './mypage/Mypage_orderlist';
import Destination from './mypage/Destination'
import Mypage_review from './mypage/Mypage_review';
import Product_inquiry from './mypage/Product_inquiry';
import Mypage_emoney from './mypage/Mypage_emoney';
import Mypage_coupon from './mypage/Mypage_coupon';

const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div`padding: 0 30px;`
const Mypageuserinfowrap = styled.div`padding: 50px 0; background: #f7f7f7;`
const Mypagemainwrap = styled.div``

const Userinfo = styled.div`display: flex;`
const Userinfototal = styled.div`background: #fff; padding: 30px;`
const Userinfototallink = styled.span`font-size: 11px; padding: 5px 10px; margin: 5px; background: #f4f4f4; border: none; border-radius: 10px;`
const Userinfolinkwrap = styled.div`display: flex;`
const Userinfolink = styled(Link)`width: 130px; background: #fff; padding: 30px 0 30px 30px; margin-left: 5px;`

const Main = styled.div`display: flex; padding-top: 50px; margin-bottom: 100px;`
const Mainleft = styled.div`width: 20%; margin-right: 20px;`
const Mainleftul = styled.ul`margin-top: 20px;`
const Mainleftlink = styled(Link)`font-size: 12px; font-weight: bold; color: #777;`
const Mainleftli = styled.li`padding: 15px 0 15px 20px; border: 1px solid #dbdbdb; &:hover {background: #fafafa; cursor: pointer;}  &:hover ${Mainleftlink}{color: #5f0800;}`
const Mainright = styled.div`width: 80%;`
const Mypage = ({match}) => {
    const type = match.params.mypageparams
    console.log(type)
    return(
        <>
        <Mypageuserinfowrap>
            <Container>
                <Row>
                    <Userinfo>
                        <Userinfototal>
                            <div><span>일반</span><strong>유저님</strong></div>
                            <div><span>적립</span>0.5%</div>
                            <div>
                                <Link to=''><Userinfototallink>전체등급 보기</Userinfototallink></Link>
                                <Link to=''><Userinfototallink>다음 달 예상등급 보기</Userinfototallink></Link>
                            </div>
                        </Userinfototal>
                        <Userinfolinkwrap>
                            <Userinfolink to=''>
                                <div><Link to=''>적립금</Link></div>
                                <div>0원</div>
                                <div>소멸예정 0원</div>
                            </Userinfolink>
                            <Userinfolink to=''>
                                <div><Link to=''>쿠폰</Link></div>
                                <div>0개</div>
                            </Userinfolink>
                            <Userinfolink to=''>
                                <div><Link to=''>컬리 퍼플 박스</Link></div>
                                <div><Link to=''>알아보기</Link></div>
                            </Userinfolink>
                            <Userinfolink to=''>
                                <div><Link to=''>컬리패스</Link></div>
                                <div><Link to=''>알아보기</Link></div>
                            </Userinfolink>
                        </Userinfolinkwrap>
                    </Userinfo>
                </Row>
            </Container>
        </Mypageuserinfowrap>
        <Mypagemainwrap>
            <Container>
                <Row>
                    <Main>
                        <Mainleft>
                            <h2>마이컬리</h2>
                            <Mainleftul>
                                <Mainleftli><Mainleftlink to='/shop/mypage/mypage_orderlist'>주문 내역</Mainleftlink></Mainleftli>
                                <Mainleftli><Mainleftlink to='/shop/mypage/destination/list'>배송지 관리</Mainleftlink></Mainleftli>
                                <Mainleftli><Mainleftlink to='/shop/mypage/mypage_review'>상품 후기</Mainleftlink></Mainleftli>
                                <Mainleftli><Mainleftlink to='/shop/mypage/product_inquiry'>상품 문의</Mainleftlink></Mainleftli>
                                <Mainleftli><Mainleftlink to='/shop/mypage/mypage_emoney'>적립금</Mainleftlink></Mainleftli>
                                <Mainleftli><Mainleftlink to='/shop/mypage/mypage_coupon'>쿠폰</Mainleftlink></Mainleftli>
                                <Mainleftli><Mainleftlink to=''>개인 정보 수정</Mainleftlink></Mainleftli>
                            </Mainleftul>
                        </Mainleft>
                        <Mainright>
                            <Route path='/shop/mypage/mypage_orderlist' exact component={Mypage_orderlist} />
                            <Route path='/shop/mypage/destination/list' exact component={Destination} />
                            <Route path='/shop/mypage/mypage_review' exact component={Mypage_review} />
                            <Route path='/shop/mypage/product_inquiry' exact component={Product_inquiry} />
                            <Route path='/shop/mypage/mypage_emoney' exact component={Mypage_emoney} />
                            <Route path='/shop/mypage/mypage_coupon' exact component={Mypage_coupon} />
                        </Mainright>
                    </Main>
                </Row>
            </Container>
        </Mypagemainwrap>
        </>
    )
}

export default Mypage;