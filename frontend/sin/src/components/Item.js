import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import axios from 'axios';
import query from 'query-string';
import {BACKEND_ADDRESS} from "../constants/ADDRESS";
import Header from "./Header";

const Itemwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Section1 = styled.div`display: flex; justify-content: space-between; height: 700px;`
const Section1left = styled.img`width: 430px; height: 550px; background: #fff;`
const Section1right = styled.div`width: 560px; height: 550px; background: #fff;`
const RightDiv1 = styled.div`padding: 10px 0px 30px 0;`
const ItemTitle = styled.p`font-size: 24px; font-weight: bold;`
const ItemSubTitle = styled.p`font-size: 14px; color: #999999;`

const RightDiv2 = styled.div`margin-bottom: 10px;`
const PriceForMember = styled.p`font-size: 14px;`
const PriceP = styled.p`font-size: 28px;`
const Price = styled.span`color: #333333;`
const Won = styled.span`font-size: 18px; color: #333333; padding-right: 10px;`
const Discount = styled.span`color: #fa622f`
const Nodiscount = styled.p`text-decoration: line-through; text-decoration-color: #999999; padding-top: 7px;`
const NdcPrice = styled.span`font-size: 16px; color: #999999;`
const Info = styled.p`font-size: 14px; color: #5f0080; padding-top: 7px;`

const RightDiv3 = styled.div``
const ItemInfo = styled.p`padding: 18px 0; border-top: 1px solid #f4f4f4; &:last-child {border-bottom: 1px solid #f4f4f4;}`
const ItemInfoTag = styled.span`display: inline-block; width: 150px; font-size: 14px; color: #666666;`
const ItemInfoValue = styled.span`font-size: 14px; color: #333333;`
const DeliveryDesc = styled.span`font-size: 12px;`

const TotalPriceDiv = styled.div`text-align: right; padding: 30px 0;`
const TotalPrice = styled.span`font-size: 32px; font-weight: bold; margin-right: 5px;`
const TotalPriceTag = styled.span`font-size: 13px; font-weight: bold; margin-right: 10px;`
const TotalPriceWon = styled.span`font-size: 20px; font-weight: bold;`
const Save = styled.span`font-size: 11px; background: #FFBF00; padding: 3px 6px; color: #fff; margin-right: 5px; border: 1px solid #FFBF00; border-radius: 10px;`
const SaveDesc = styled.span`font-size: 14px; color: #333333;`
const Btndiv = styled.div`display: flex; justify-content: space-between; font-size: 16px;`
const Restocked = styled.button`width: 138px; height: 56px; color: #DDDDDD; border: 1px solid #ddd; font-weight: bold; background: #FFFFFF; cursor: pointer;`
const Basket = styled.button`width: 408px; height: 56px; color: #FFFFFF; border: 1px solid #5f0080; font-weight: bold; background: #5f0080; cursor: pointer;`

const Section2 = styled.div`height: 300px;`
const Section3 = styled.div`height: 300px;`
const Section4 = styled.div`height: 300px;`

const Item = (props) => {
  const goodsno = query.parse(props.location.search).goodsno
  const [item, setItem] = useState({
    productName: 'test',
    contentSummary: 'test',
    thumbnailUrl: 'test',
    price: 'test',
    discountPercent: 'test',
    saleUnit: 'test',
    weight: 'test',
    deliveryClassification: 'test',
    packingType: 'test',
    originCountry: 'test',
    allergicReaction: 'test',
    expirationDate: 'test',
    // productName: null,
    // contentSummary: null,
    // thumbnailUrl: null,
    // price : null,
    // discountPercent: null,
    // saleUnit: null,
    // weight: null,
    // deliveryClassification: null,
    // packingType: null,
    // originCountry: null,
    // allergicReaction: null,
    // expirationDate: null
  })
  const [delay, setDelay] = useState(null)

  useEffect(() => {
    axios({
      method: 'get',
      url: BACKEND_ADDRESS + `/goods/goods_view?goodsno=${goodsno}`
    }).then(res => {
      setItem({
        ...item,
        productName: res.data.productName,
        contentSummary: res.data.contentSummary,
        thumbnailUrl: res.data.thumbnailUrl,
        price: res.data.price,
        discountPercent: res.data.discountPercent,
        saleUnit: res.data.saleUnit,
        weight: res.data.weight,
        deliveryClassification: res.data.deliveryClassification,
        packingType: res.data.packingType,
        originCountry: res.data.originCountry,
        allergicReaction: res.data.allergicReaction,
        expirationDate: res.data.expirationDate
      })
    }).catch(err => {
    }).then(
        setDelay('on'))
  }, [])

  return (
      <>
        <Header/>
        <Itemwrap>
          <Container>
            <Row>
              {delay === 'on' ?
                  <>
                    <Section1>
                      <Section1left/>
                      <Section1right>
                        <RightDiv1>
                          <ItemTitle>{item.productName}</ItemTitle>
                          <ItemSubTitle>{item.contentSummary}</ItemSubTitle>
                        </RightDiv1>
                        <RightDiv2>
                          {item.discountPercent ?
                              <>
                                <PriceForMember>???????????????</PriceForMember>
                                <PriceP>
                                  <Price>{item.price}</Price>
                                  <Won>???</Won>
                                  <Discount>{item.discountPercent}</Discount>
                                  <Discount>%</Discount>
                                </PriceP>
                                <Nodiscount>
                                  <NdcPrice>nodiscount</NdcPrice>
                                  <NdcPrice>???</NdcPrice>
                                </Nodiscount>
                                <Info>????????? ???, ?????????????????? ??????????????? ???????????????./???????????? ?????????</Info>
                              </>
                              :
                              <>
                                <PriceP>
                                  <Price>{item.price}</Price>
                                  <Won>???</Won>
                                </PriceP>
                                <Info>????????? ???, ?????????????????? ??????????????? ???????????????./???????????? ?????????</Info>
                              </>
                          }
                        </RightDiv2>
                        <RightDiv3>
                          {item.saleUnit ?
                              <>
                                <ItemInfo>
                                  <ItemInfoTag>????????????</ItemInfoTag>
                                  <ItemInfoValue>{item.saleUnit}</ItemInfoValue>
                                </ItemInfo>
                              </> : null
                          }
                          {item.weight ?
                              <>
                                <ItemInfo>
                                  <ItemInfoTag>??????/??????</ItemInfoTag>
                                  <ItemInfoValue>{item.weight}</ItemInfoValue>
                                </ItemInfo>
                              </> : null
                          }
                          {item.deliveryClassification ?
                              <>
                                <ItemInfo>
                                  <ItemInfoTag>????????????</ItemInfoTag>
                                  <ItemInfoValue>{item.deliveryClassification}</ItemInfoValue>
                                </ItemInfo>
                              </> : null
                          }
                          {item.packingType ?
                              <>
                                <ItemInfo>
                                  <p>
                                    <ItemInfoTag>????????????</ItemInfoTag>
                                    <ItemInfoValue>{item.saleUnit}</ItemInfoValue>
                                  </p>
                                  <p>
                                    <ItemInfoTag/>
                                    <DeliveryDesc>??????????????? ??????????????? ??????????????????
                                      ???????????????.</DeliveryDesc>
                                  </p>
                                </ItemInfo>
                              </> : null
                          }
                          {item.originCountry ?
                              <>
                                <ItemInfo>
                                  <ItemInfoTag>?????????</ItemInfoTag>
                                  <ItemInfoValue>{item.originCountry}</ItemInfoValue>
                                </ItemInfo>
                              </> : null
                          }
                          {item.allergicReaction ?
                              <>
                                <ItemInfo>
                                  <ItemInfoTag>??????????????????</ItemInfoTag>
                                  <ItemInfoValue>{item.allergicReaction}</ItemInfoValue>
                                </ItemInfo>
                              </> : null
                          }
                          {item.expirationDate ?
                              <>
                                <ItemInfo>
                                  <ItemInfoTag>?????? ????????????</ItemInfoTag>
                                  <ItemInfoValue>{item.expirationDate}</ItemInfoValue>
                                </ItemInfo>
                              </> : null
                          }
                        </RightDiv3>
                        <div>
                          <TotalPriceDiv>
                            <p>
                              <TotalPriceTag>??? ????????????:</TotalPriceTag>
                              <TotalPrice>TotalPrice</TotalPrice>
                              <TotalPriceWon>???</TotalPriceWon>
                            </p>
                            <p>
                              <Save>??????</Save>
                              <SaveDesc>????????? ???, ???????????? ??????/???????????? ?????????</SaveDesc>
                            </p>
                          </TotalPriceDiv>
                          <Btndiv>
                            <Restocked>????????? ??????</Restocked>
                            <Basket>???????????? ??????</Basket>
                          </Btndiv>
                        </div>
                      </Section1right>
                    </Section1>
                    <Section2></Section2>
                    <Section3></Section3>
                    <Section4></Section4>
                  </> :
                  null
              }
            </Row>
          </Container>
        </Itemwrap>
      </>
  )
}

export default Item;