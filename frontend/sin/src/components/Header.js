import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Headerwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``


const Usermenu = styled.div``
const Usermenuleft = styled.div`float: left;`
const Usermenuright = styled.div`float: right`


const Headerlogo = styled.div``
const Logolink = styled(Link)`display: block; width: 200px; margin: 0 auto; font-size: 40px; font-weight: bold; color: purple; text-align: center;`


const Headernav = styled.div``
const Allcategory = styled(Link)`float: left; font-size: 16px; line-height:33px; color: #333; text-align: center; margin: 15px 30px;`
const Category = styled.ul`float: left;`
const Categoryitem = styled.li`float: left; font-size: 16px; line-height:33px; color: #333; text-align: center; margin: 15px 30px;`
const Search = styled.div`position: relative; float: left; width:250px; height:30px; background: #f7f7f7; border-radius: 18px; margin: 15px 30px`
const Searchinput = styled.input`position: absolute; left: 5px; top: 8px; float: left; border: none; background: none;`
const Serachsubmit = styled.button`position: absolute; right: 10px; top: 8px; float: left; border: none; background: none;`
const Shoppinglist = styled(Link)`float: left; line-height:33px; margin: 15px 0px 15px 30px;`

const Header = () => {
    return (
        <Headerwrap>
            <Container>
                <Row>
                    <Usermenu className='clearfix'>
                        <Usermenuleft>item</Usermenuleft>
                        <Usermenuright>
                            <Link to=''>회원가입</Link>
                            <Link to=''>로그인</Link>
                            <Link to=''>고객센터</Link>
                        </Usermenuright>
                    </Usermenu>
                    <Headerlogo className='clearfix'>
                        <Logolink to='/'>kurly</Logolink>
                    </Headerlogo>
                    <Headernav className='clearfix'>
                        <Allcategory>전체 카테고리</Allcategory>
                        <Category className='clearfix'>
                            <Categoryitem><Link to='/list/new'>신상품</Link></Categoryitem>
                            <Categoryitem><Link to='/list/best'>베스트</Link></Categoryitem>
                            <Categoryitem><Link to='/list/price'>알뜰쇼핑</Link></Categoryitem>
                            <Categoryitem><Link to='/event'>특가/혜택</Link></Categoryitem>
                        </Category>
                        <Search className='clearfix'>
                            <Searchinput type='text' placeholder='건강 기원 새해맞이 보양식 레시피' />
                            <Serachsubmit>button</Serachsubmit>
                        </Search>
                        <Shoppinglist>장바구니</Shoppinglist>
                    </Headernav>
                </Row>
            </Container>
        </Headerwrap>
    )
}

export default Header;