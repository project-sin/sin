import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import {Link, useHistory} from 'react-router-dom';
import {ACCESS_TOKEN} from "../constants/Sessionstorage";
import findUserApi from "./api/user/FIndUserApi";
import searchProductApi from "./api/product/SearchProductApi";
import findProductListApi from "./api/product/FindProductListApi";

const Headerwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div`margin-bottom: -20px;`

const Usermenu = styled.div``
const Usermenuleft = styled.div`float: left;`
const Usermenuright = styled.div`float: right`

const Headerlogo = styled.div``
const Logolink = styled(
    Link)`display: block; width: 200px; margin: 0 auto; font-size: 40px; font-weight: bold; color: rgb(112,48,160); text-align: center;`

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
const Location = styled.div`line-height:33px; margin: 15px 0px 15px 30px;`
const Shoppinglist = styled.div`line-height:33px; margin: 15px 0px 15px 30px;`

const Header = () => {
  const history = useHistory()
  const [sub, setSub] = useState('subnav1')

  const subnav = (id) => {
    document.getElementById(sub).style.display = 'none'
    document.getElementById(id).style.display = 'block'
    setSub(id)
  }

  const [user, setUser] = useState(null);
  const accessToken = sessionStorage.getItem(ACCESS_TOKEN);

  const [sword, setSword] = useState("")
  const search = () => {
    if (!sword.length) {
      alert("???????????? ??????????????????")
    } else {
      history.push(
          "/shop/goods/goods_list?searched=Y&sword=" + sword)
    }
  }
  const enterKey = () => {
    if (window.event.keyCode == 13) {
      search();
    }
  }

  useEffect(() => {
    if (!accessToken) {
      return;
    }
    findUserApi(accessToken).then(userPromise => {
      setUser(userPromise)
    })
  }, [accessToken]);

  return (
      <Headerwrap>
        <Container>
          <Row>
            <Usermenu className='clearfix'>
              <Usermenuleft>item</Usermenuleft>
              <Usermenuright>
                {user == null ?
                    <block><Link
                        to='/shop/member/join'>????????????</Link> |
                      <Link to='/shop/member/login'>?????????</Link> |
                    </block>
                    : user.loginId + " ??? | "
                }
                <Link to='/shop/board/list?id=notice'>????????????</Link>
              </Usermenuright>
            </Usermenu>
            <Headerlogo className='clearfix'>
              <Logolink to='/shop/main/index'>kurly</Logolink>
            </Headerlogo>
            <Headernav>
              <Totalcategory>
                <Totalcategoryp>?????? ????????????</Totalcategoryp>
                <Totalcategorynav>
                  <ul>
                    <Tcnli onMouseOver={() => subnav('subnav1')}
                           onClick={() => history.push(
                               '/shop/goods/goods_list?category=907')}>??????</Tcnli>
                    <Tcnli onMouseOver={() => subnav('subnav2')}
                           onClick={() => history.push(
                               '/shop/goods/goods_list?category=908')}>???????????????????</Tcnli>
                    <Tcnli onMouseOver={() => subnav('subnav3')}
                           onClick={() => history.push(
                               '/shop/goods/goods_list?category=909')}>?????????????????????????</Tcnli>
                    <Tcnli onMouseOver={() => subnav('subnav4')}
                           onClick={() => history.push(
                               '/shop/goods/goods_list?category=910')}>??????????????</Tcnli>
                    <Tcnli onMouseOver={() => subnav('subnav5')}
                           onClick={() => history.push(
                               '/shop/goods/goods_list?category=911')}>?????????????????????????</Tcnli>
                  </ul>
                  <Totalcategorysubnav>
                    <Subnav1 id='subnav1'>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=907008')}>?????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=907001')}>?????????????????????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=907002')}>????????????????????????????</Tcsnli>
                    </Subnav1>
                    {/* ?????? */}
                    <Subnav2 id='subnav2'>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=908008')}>?????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=908001')}>????????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=908002')}>????????????</Tcsnli>
                    </Subnav2>
                    {/* ?????? */}
                    <Subnav3 id='subnav3'>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=909008')}>????????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=909001')}>?????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=909002')}>?????????????????</Tcsnli>
                    </Subnav3>
                    {/* ?????? */}
                    <Subnav4 id='subnav4'>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=910008')}>?????????
                        ?????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=910001')}>?????????
                        ?????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=910002')}>????????????</Tcsnli>
                    </Subnav4>
                    {/* ?????? */}
                    <Subnav5 id='subnav5'>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=911008')}>????????????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=911001')}>???????????????????????</Tcsnli>
                      <Tcsnli onClick={() => history.push(
                          '/shop/goods/goods_list?category=911002')}>?????????</Tcsnli>
                    </Subnav5>
                    {/* ??? */}
                  </Totalcategorysubnav>
                </Totalcategorynav>

              </Totalcategory>
              <Category className='clearfix'>
                <Categoryitem><Link
                    to='/shop/goods/goods_list?category=038'>?????????</Link></Categoryitem>
                <Categoryitem><Link
                    to='/shop/goods/goods_list?category=029'>?????????</Link></Categoryitem>
                <Categoryitem><Link
                    to='/shop/goods/goods_list?list=sale'>????????????</Link></Categoryitem>
                <Categoryitem><Link
                    to='/shop/goods/event'>??????/??????</Link></Categoryitem>
              </Category>
              <Search className='clearfix'>
                <Searchinput type='text'
                             value={sword}
                             onChange={e => setSword(e.target.value.trim())}
                             onKeyUp={enterKey}
                />
                <Serachsubmit><img
                    src="/search.png"
                    alt="my image"
                    style={{
                      marginTop: "-3px",
                      width: "20px",
                      height: "20px",
                      cursor: "pointer"
                    }}
                    onClick={search}
                /></Serachsubmit>
              </Search>
              <Location><Link to='/'><img
                  src="/location.png"
                  alt="my image"
                  style={{
                    marginLeft: "-15px",
                    width: "36px",
                    height: "29px"
                  }}
              /></Link></Location>
              <Shoppinglist><Link to='/shop/goods/goods_cart'><img
                  src="/cart.png"
                  alt="my image"
                  style={{
                    marginLeft: "8px",
                    width: "28px",
                    height: "28px"
                  }}
              /></Link></Shoppinglist>
            </Headernav>
          </Row>
        </Container>
      </Headerwrap>
  )
}

export default Header;