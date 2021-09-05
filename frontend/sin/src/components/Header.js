import React, { useState } from 'react';
import styled from 'styled-components';
import { Link, useHistory } from 'react-router-dom';

const Headerwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``


const Usermenu = styled.div``
const Usermenuleft = styled.div`float: left;`
const Usermenuright = styled.div`float: right`

const Headerlogo = styled.div``
const Logolink = styled(Link)`display: block; width: 200px; margin: 0 auto; font-size: 40px; font-weight: bold; color: purple; text-align: center;`

const Headernav = styled.div`display: flex;`
const Totalcategorysubnav = styled.div`display: none; box-sizing: border-box; position: absolute; top: 0px; left: 200px; width: 200px; height: 300px; font-size: 14px; text-align: left; background: #f7f7f7;`
const Totalcategorynav = styled.div`display: none; box-sizing: border-box; position: absolute; top: 60px; width: 200px; height: 300px; font-size: 14px; text-align: left; background: #fff; &:hover ${Totalcategorysubnav} {display: block}`
const Tcnli = styled.li`padding: 10px 0 10px 20px; &:hover {background: #f7f7f7;};`
const Tcsnli = styled.li`padding: 10px 0 10px 20px; &:hover {color: #5f0080; text-decoration: underline}`
const Totalcategoryp = styled.p`line-height: 60px;`
const Totalcategory = styled.div`z-index: 100; position: relative; width: 168px; font-size: 16px; color: #333; text-align: center; cursor: pointer; &:hover ${Totalcategorynav} {display: block}`
const Subnav1 = styled.ul`display: none;`
const Subnav2 = styled.ul`display: none;`
const Subnav3 = styled.ul`display: none;`
const Subnav4 = styled.ul`display: none;`
const Subnav5 = styled.ul`display: none;`



const Category = styled.ul``
const Categoryitem = styled.li`float: left; font-size: 16px; line-height:33px; color: #333; text-align: center; margin: 15px 20px;`
const Search = styled.div`position: relative; width:250px; height:30px; background: #f7f7f7; border-radius: 18px; margin: 15px 30px`
const Searchinput = styled.input`position: absolute; left: 5px; top: 8px; float: left; border: none; background: none;`
const Serachsubmit = styled.button`position: absolute; right: 10px; top: 8px; float: left; border: none; background: none;`
const Shoppinglist = styled(Link)`line-height:33px; margin: 15px 0px 15px 30px;`

const Header = (props) => {
    const history = useHistory()
    const [sub,setSub] = useState('subnav1')

    const subnav = (id) => {
        document.getElementById(sub).style.display = 'none'
        document.getElementById(id).style.display = 'block'
        setSub(id)
    }

    return (
        <Headerwrap>
            <Container>
                <Row>
                    <Usermenu className='clearfix'>
                        <Usermenuleft>item</Usermenuleft>
                        <Usermenuright>
                            <Link to='/shop/member/join'>회원가입</Link>
                            <Link to='/shop/member/login'>로그인</Link> / 
                            <Link to='/shop/mypage/mypage_orderlist'>로그인중</Link>
                            <Link to='/shop/board/list?id=notice'>고객센터</Link>
                        </Usermenuright>
                    </Usermenu>
                    <Headerlogo className='clearfix'>
                        <Logolink to='/shop/main/index'>kurly</Logolink>
                    </Headerlogo>
                    <Headernav>
                        <Totalcategory>
                            <Totalcategoryp>전체 카테고리</Totalcategoryp>
                            <Totalcategorynav>
                                <ul>
                                    <Tcnli onMouseOver={()=>subnav('subnav1')} onClick={()=>history.push('/shop/goods/goods_list?category=907')}>채소</Tcnli>
                                    <Tcnli onMouseOver={()=>subnav('subnav2')} onClick={()=>history.push('/shop/goods/goods_list?category=908')}>과일·견과·쌀</Tcnli>
                                    <Tcnli onMouseOver={()=>subnav('subnav3')} onClick={()=>history.push('/shop/goods/goods_list?category=909')}>수산·해산·건어물</Tcnli>
                                    <Tcnli onMouseOver={()=>subnav('subnav4')} onClick={()=>history.push('/shop/goods/goods_list?category=910')}>정육·계란</Tcnli>
                                    <Tcnli onMouseOver={()=>subnav('subnav5')} onClick={()=>history.push('/shop/goods/goods_list?category=911')}>국·반찬·메인요리</Tcnli>
                                </ul>
                                <Totalcategorysubnav>
                                    <Subnav1 id='subnav1'>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=907008')}>친환경</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=907001')}>고구마·감자·당근</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=907002')}>시금치·쌈채소·나물</Tcsnli>
                                    </Subnav1>
                                    {/* 채소 */}
                                    <Subnav2 id='subnav2'>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=908008')}>친환경</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=908001')}>제철과일</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=908002')}>국산과일</Tcsnli>
                                    </Subnav2>
                                    {/* 과일 */}
                                    <Subnav3 id='subnav3'>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=909008')}>제철수산</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=909001')}>생선류</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=909002')}>굴비·반건류</Tcsnli>
                                    </Subnav3>
                                    {/* 수산 */}
                                    <Subnav4 id='subnav4'>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=910008')}>국내산 소고기</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=910001')}>수입산 소고기</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=910002')}>돼지고기</Tcsnli>
                                    </Subnav4>
                                    {/* 정육 */}
                                    <Subnav5 id='subnav5'>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=911008')}>국·탕·찌개</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=911001')}>밀키트·메인요리</Tcsnli>
                                        <Tcsnli onClick={()=>history.push('/shop/goods/goods_list?category=911002')}>밑반찬</Tcsnli>
                                    </Subnav5>
                                    {/* 국 */}
                                </Totalcategorysubnav>
                            </Totalcategorynav>
                            
                        </Totalcategory>
                        <Category className='clearfix'>
                            <Categoryitem><Link to='/shop/goods/goods_list?category=038'>신상품</Link></Categoryitem>
                            <Categoryitem><Link to='/shop/goods/goods_list?category=029'>베스트</Link></Categoryitem>
                            <Categoryitem><Link to='/shop/goods/goods_list?list=sale'>알뜰쇼핑</Link></Categoryitem>
                            <Categoryitem><Link to='/shop/goods/event'>특가/혜택</Link></Categoryitem>
                        </Category>
                        <Search className='clearfix'>
                            <Searchinput type='text' placeholder='건강 기원 새해맞이 보양식 레시피' />
                            <Serachsubmit>button</Serachsubmit>
                        </Search>
                        <Shoppinglist><Link to='/shop/goods/goods_cart'>장바구니</Link></Shoppinglist>
                    </Headernav>
                </Row>
            </Container>
        </Headerwrap>
    )
}

export default Header;