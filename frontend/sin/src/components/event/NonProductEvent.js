import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import Header from "../Header";

const NonProducteventwrap = styled.div`display: flex; margin-top: 60px;`
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Left = styled.div`position: relative; min-width: 105px;`
const Leftul = styled.ul`position: absolute; margin-left: -40px;`
const Leftli = styled.li`margin-bottom: 20px;`
const Leftlink = styled(Link)`font-size: 16px; font-weight: 500; color: #999;`
const Right = styled.div`width: 780px;`

const Div1 = styled.div`padding-bottom: 50px; border-bottom: 2px solid #eee;`
const Title = styled.p`font-size: 31px; font-weight: bold; color: #333; letter-spacing: -2px;`
const Subtitle = styled.p`font-size: 15px; color: #666666; letter-spacing: -1px; padding-top: 20px;`
const Div2 = styled.div`display: flex; justify-content: space-between; padding-top: 50px; border-bottom: 3px solid #5f0080;`
const Thepurple = styled.div`position: relative; width: 120px; height: 90px; margin: auto 0 0; border: 1px solid #5f0080; border-bottom: none; cursor: pointer; &:first-child {background: #5f0080}; & > span {color: #fff}`
const Purple = styled.div`position: relative; width: 120px; height: 80px; text-align: center; margin: auto 0 0; border: 1px solid #5f0080; border-bottom: none; cursor: pointer;`
const Lavender = styled.div`position: relative; width: 120px; height: 70px; margin: auto 0 0; border: 1px solid #5f0080; border-bottom: none; cursor: pointer;`
const White = styled.div`position: relative; width: 120px; height: 60px; margin: auto 0 0; border: 1px solid #5f0080; border-bottom: none; cursor: pointer;`
const Friends = styled.div`position: relative; width: 120px; height: 50px; margin: auto 0 0; border: 1px solid #5f0080; border-bottom: none; cursor: pointer;`
const Normal = styled.div`position: relative; width: 120px; height: 40px; margin: auto 0 0; border: 1px solid #5f0080; border-bottom: none; cursor: pointer;`
const Name = styled.span`position: absolute; font-size: 18px; color: #5f0080; bottom: 5px; left: 6px;`
const Rate = styled.span`position: absolute; font-size: 18px; color: #5f0080; bottom: 5px; right: 8px;`

const Div3 = styled.div``
const Div2child = styled.div`display: none; height: 250px; border-bottom: 1px solid #eee; &:first-child {display: flex}`
const Div2childfir = styled.div`position: relative; width: 260px;`
const Firfir = styled.p`position: absolute; top: 40px; left: 10px; font-size: 22px; color: #5f0080; font-weight: 600; letter-spacing: -.5px;`
const Firsec = styled.p`position: absolute; top: 80px; left: 10px; font-size: 13px; letter-spacing: -.4px;`
const Firthi = styled.p`position: absolute; top: 100px; left: 10px; font-size: 13px; letter-spacing: -.4px;`
const Div2childsec = styled.div`position: relative; width: 260px;`
const Secfir = styled.p`position: absolute; top: 30px; left: 30px; font-size: 14px; letter-spacing: -.4px;`
const Secsec = styled.p`position: absolute; top: 70px; left: 30px; font-size: 14px; letter-spacing: -.4px;`
const Secthi = styled.p`position: absolute; top: 110px; left: 30px; font-size: 14px; letter-spacing: -.4px;`
const Secfou = styled.p`position: absolute; top: 140px; left: 30px; font-size: 14px; letter-spacing: -.4px;`
const Secfiv = styled.p`position: absolute; top: 170px; left: 30px; font-size: 14px; letter-spacing: -.4px;`
const Strong = styled.span`font-size: 20px; font-weight: bold;`
const Div2childthi = styled.div`position: relative; width: 260px;`
const Thifir = styled.p`position: absolute; top: 80px; left: 40px; font-size: 14px; letter-spacing: -.8px;`
const Thisec = styled.p`position: absolute; top: 110px; left: 45px; font-size: 12px; color: #999999; letter-spacing: -.8px;`
const Div4 = styled.ul`padding: 25px 15px 40px;`
const Div4p = styled.li`font-size: 13px; color: #999999; line-height: 23px; letter-spacing: -1px;`

const NonProductEvent = () => {
  const [on, setOn] = useState('thepurple')
  const [parenton, setParenton] = useState('parentthepurple')

  const handler = (id) => {
    document.getElementById(`${on}`).style.display = 'none'
    document.getElementById(`${id}`).style.display = 'flex'
    setOn(id)
  }
  const color = (parentid) => {
    document.getElementById(`${parenton}`).style.backgroundColor = '#fff'
    document.getElementById(`${parenton}`).childNodes.forEach(e => {
      e.style.color = '#5f0080'
    })
    document.getElementById(`${parenton}`).style.transition = 'all .2s ease'
    document.getElementById(`${parentid}`).style.backgroundColor = '#5f0080'
    document.getElementById(`${parentid}`).childNodes.forEach(e => {
      e.style.color = '#fff'
    })
    document.getElementById(`${parentid}`).style.transition = 'all .2s ease'
    setParenton(parentid)
  }

  return (
      <>
        <Header/>
        <Container>
          <Row>
            <NonProducteventwrap>
              <Left>
                <Leftul>
                  <Leftli><Leftlink to=''>전체보기</Leftlink></Leftli>
                  <Leftli><Leftlink to=''>회원혜택</Leftlink></Leftli>
                  <Leftli><Leftlink to=''>친구초대</Leftlink></Leftli>
                  <Leftli><Leftlink to=''>장바구니</Leftlink></Leftli>
                  <Leftli><Leftlink to=''>결제혜택</Leftlink></Leftli>
                </Leftul>
              </Left>
              <Right>
                <Div1>
                  <Title>컬리 러버스 혜택</Title>
                  <Subtitle>고객님께 받은 사랑을 혜택으로 돌려드려요. 매월 실적에 따라 달라지는 실용적이고 다채로운
                    혜택을 확인해보세요.</Subtitle>
                </Div1>
                <Div2>
                  <Thepurple id='parentthepurple' onClick={() => {
                    handler('thepurple');
                    color('parentthepurple')
                  }}><Name>더퍼플</Name><Rate>7%</Rate></Thepurple>
                  <Purple id='parentpurple' onClick={() => {
                    handler('purple');
                    color('parentpurple')
                  }}><Name>퍼플</Name><Rate>7%</Rate></Purple>
                  <Lavender id='parentlavender' onClick={() => {
                    handler('lavender');
                    color('parentlavender')
                  }}><Name>라벤더</Name><Rate>5%</Rate></Lavender>
                  <White id='parentwhite' onClick={() => {
                    handler('white');
                    color('parentwhite')
                  }}><Name>화이트</Name><Rate>3%</Rate></White>
                  <Friends id='parentfriends' onClick={() => {
                    handler('friends');
                    color('parentfriends')
                  }}><Name>프렌즈</Name><Rate>1%</Rate></Friends>
                  <Normal id='parentnormal' onClick={() => {
                    handler('normal');
                    color('parentnormal')
                  }}><Name>일반</Name><Rate>0.5%</Rate></Normal>
                </Div2>
                <Div3>
                  <Div2child id='thepurple'>
                    <Div2childfir>
                      <Firfir>더퍼플</Firfir>
                      <Firsec>전월 실적 150만원 이상</Firsec>
                      <Firthi>(결제 금액+적립금 사용액)</Firthi>
                    </Div2childfir>
                    <Div2childsec>
                      <Secfir>적립<Strong>7</Strong>%</Secfir>
                      <Secsec>더블 후기 적립금</Secsec>
                      <Secthi>매월 더퍼플 선물</Secthi>
                      <Secfou>최대<Strong>2</Strong>만원 추가 혜택</Secfou>
                      <Secfiv>(깜짝 쿠폰,적립 이벤트 등)</Secfiv>
                    </Div2childsec>
                    <Div2childthi>
                      <Thifir>총 적립 금액 126만원 이상</Thifir>
                      <Thisec> 더퍼플 등급 연간 유지 시</Thisec>
                    </Div2childthi>
                  </Div2child>
                  {/* 더퍼플 */}
                  <Div2child id='purple'>
                    <Div2childfir>
                      <Firfir>퍼플</Firfir>
                      <Firsec>전월 실적 100만원 이상</Firsec>
                      <Firthi>(결제 금액+적립금 사용액)</Firthi>
                    </Div2childfir>
                    <Div2childsec>
                      <Secfir>적립<Strong>7</Strong>%</Secfir>
                      <Secsec>더블 후기 적립금</Secsec>
                      <Secthi>매월 더퍼플 선물</Secthi>
                      <Secfou>최대<Strong>2</Strong>만원 추가 혜택</Secfou>
                    </Div2childsec>
                    <Div2childthi>
                      <Thifir>총 적립 금액 84-126만원 이상</Thifir>
                      <Thisec> 더퍼플 등급 연간 유지 시</Thisec>
                    </Div2childthi>
                  </Div2child>
                  {/* 퍼플 */}
                  <Div2child id='lavender'>
                    <Div2childfir>
                      <Firfir>라벤더</Firfir>
                      <Firsec>전월 실적 50만원 이상</Firsec>
                      <Firthi>(결제 금액+적립금 사용액)</Firthi>
                    </Div2childfir>
                    <Div2childsec>
                      <Secfir>적립<Strong>5</Strong>%</Secfir>
                      <Secsec>더블 후기 적립금</Secsec>
                      <Secthi>매월 더퍼플 선물</Secthi>
                      <Secfou>최대<Strong>2</Strong>만원 추가 혜택</Secfou>
                    </Div2childsec>
                    <Div2childthi>
                      <Thifir>총 적립 금액 30-60만원 이상</Thifir>
                      <Thisec> 더퍼플 등급 연간 유지 시</Thisec>
                    </Div2childthi>
                  </Div2child>
                  {/* 라벤더 */}
                  <Div2child id='white'>
                    <Div2childfir>
                      <Firfir>화이트</Firfir>
                      <Firsec>전월 실적 30만원 이상</Firsec>
                      <Firthi>(결제 금액+적립금 사용액)</Firthi>
                    </Div2childfir>
                    <Div2childsec>
                      <Secfir>적립<Strong>3</Strong>%</Secfir>
                      <Secsec>더블 후기 적립금</Secsec>
                      <Secthi>매월 더퍼플 선물</Secthi>
                    </Div2childsec>
                    <Div2childthi>
                      <Thifir>총 적립 금액 10-18만원 이상</Thifir>
                      <Thisec> 더퍼플 등급 연간 유지 시</Thisec>
                    </Div2childthi>
                  </Div2child>
                  {/* 화이트 */}
                  <Div2child id='friends'>
                    <Div2childfir>
                      <Firfir>프렌즈</Firfir>
                      <Firsec>전월 실적 10만원 이상</Firsec>
                      <Firthi>(결제 금액+적립금 사용액)</Firthi>
                    </Div2childfir>
                    <Div2childsec>
                      <Secfir>적립<Strong>1</Strong>%</Secfir>
                      <Secsec>더블 후기 적립금</Secsec>
                      <Secthi>매월 더퍼플 선물</Secthi>
                    </Div2childsec>
                    <Div2childthi>
                      <Thifir>총 적립 금액 1-3만원 이상</Thifir>
                      <Thisec> 더퍼플 등급 연간 유지 시</Thisec>
                    </Div2childthi>
                  </Div2child>
                  {/* 프렌즈 */}
                  <Div2child id='normal'>
                    <Div2childfir>
                      <Firfir>일반</Firfir>
                      <Firsec>전월 실적 15만원 미만</Firsec>
                      <Firthi>(결제 금액+적립금 사용액)</Firthi>
                    </Div2childfir>
                    <Div2childsec>
                      <Secfir>적립<Strong>0.5</Strong>%</Secfir>
                    </Div2childsec>
                    <Div2childthi>
                      <Thifir>총 적립 금액 1만원 이상</Thifir>
                    </Div2childthi>
                  </Div2child>
                  {/* 노멀 */}
                </Div3>
                <Div4>
                  <Div4p>추가 혜택은 월 중 지급되며, 혜택 지급 시 SMS 수신동의해주신 고객님께 문자 개별 안내드릴
                    예정입니다.</Div4p>
                  <Div4p>SMS 수신 여부는 [마이컬리&gt;개인정보 수정]에서 설정하실 수 있습니다.</Div4p>
                  <Div4p>총 적립 금액은 12개월간 동일 등급을 유지할 경우 받게 되는 적립 금액의
                    총합계입니다.</Div4p>
                  <Div4p>등급별 혜택은 변경될 수 있습니다.</Div4p>
                </Div4>
              </Right>
            </NonProducteventwrap>
          </Row>
        </Container>
      </>
  );
};

export default NonProductEvent;