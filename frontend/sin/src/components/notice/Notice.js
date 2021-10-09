import React from 'react';
import styled from 'styled-components';
import { Route, Link } from 'react-router-dom';
import Noticesection from './Noticesection';
import Faq from './Faq';

const Noticewrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Main = styled.div`padding-top: 30px; padding-bottom: 100px;`
const Mainleft = styled.div`float: left; padding-right: 20px;`
const Mainright = styled.div`float: left;`

const Mainlefttitle = styled.div`font-size:30px; font-weight: bold; padding-bottom: 20px;`
const Mainleftlilink = styled(Link)``
const Mainleftul = styled.ul``
const Mainleftli = styled.li`width: 170px; font-size: 14px; padding:15px 0 15px 20px;border: 1px solid #f2f2f2; background: #fafafa; &:hover { background: #dbdbdb; cursor: pointer;}; &:hover ${Mainleftlilink} {color: #5f0080; font-weight: bold;}`


const Notice = () => {
    return(
        <Noticewrap>
            <Container>
                <Row>
                    <Main className='clearfix'>
                        <Mainleft>
                            <Mainlefttitle>고객센터</Mainlefttitle>
                            <Mainleftul>
                                <Mainleftli><Mainleftlilink to='/shop/board/list?id=notice'>공지사항</Mainleftlilink></Mainleftli>
                                <Mainleftli><Mainleftlilink to='/shop/service/faq'>자주하는 질문</Mainleftlilink></Mainleftli>
                                <Mainleftli><Mainleftlilink to='/shop/mypage/mypage_qna'>1:1문의</Mainleftlilink></Mainleftli>
                            </Mainleftul>
                        </Mainleft>
                        <Mainright>
                            <Route path='/shop/board/list' exact component={Noticesection} />
                            <Route path='/shop/service/faq' exact component={Faq} />
                        </Mainright>
                    </Main>
                </Row>
            </Container>
        </Noticewrap>
    )
}

export default Notice;