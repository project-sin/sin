import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import {Swiper, SwiperSlide} from "swiper/react";

import SwiperCore, {Navigation} from "swiper";
import "swiper/swiper.scss";
import "swiper/components/navigation/navigation.scss";
import axios from 'axios';
import {BACKEND_ADDRESS} from '../constants/ADDRESS';
import mainBannerApi from "./api/home/MainBannerApi";
import Header from "./Header";
import cheapProductApi from "./api/home/CheapProductApi";
import mdChoiceApi from "./api/home/MDChoiceApi";
import todayRecommendationAPi from "./api/home/TodayRecommendationAPi";

SwiperCore.use([Navigation])

const Homewrap = styled.div``
const ContainerSlide = styled.div`width: 1050px; margin: 30px auto;`
const Row = styled.div``

const SubTitle = styled.div`text-align:center; margin-top:100px; font-size:30px; font-weight:bold; cursor: pointer;`

const Slide = styled.div`height: 300px; background: #999;`
const SectionSlide = styled.div`height: 500px;`
const Section1wrap = styled.div``
const Section1 = styled.div`height: 300px; background: #888;  overflow: hidden;`
const Section1item = styled.div`float: left; width: 300px; height: 200px; margin: 10px;`
const Section2wrap = styled.div``
const Section2 = styled.div`height: 300px; background: #777;`
const Section3wrap = styled.div``
const Section3 = styled.div`height: 300px; background: #666;`
const Section4wrap = styled.div``
const Section4 = styled.div`height: 300px; background: #555;`

const Container = styled.div`
  position: relative;
  width: 320px;
  margin:11px;
  height: 500px;
  cursor: pointer;
`;
const ProductImg = styled.img`
  position: absolute;
  width: 320px;
  height: 340px;
`;

const ProductDetails = styled.div`
  position: absolute;
  top: 350px;
`;

const ProductName = styled.div`
  font-size: 25px;
`;

const ProductDiscountPercent = styled.div`
  display: inline-block;
  font-size: 20px;
  color: red;
  font-weight: bold;
`;

const ProductPrice = styled.div`
  display: inline-block;
  font-size: 20px;
  margin-left:10px;
  font-weight: bold;
`;

const ProductPrevPrice = styled.div`
  color: gray;
  text-decoration:line-through
`;

const ProductSummary = styled.div`
`;

const Home = (props) => {
  const [banners, setBanners] = useState(null)
  const [cheapProducts, setCheapProducts] = useState(null)
  const [mdChoices, setMdChoices] = useState(null)
  const [recommendations, setRecommendations] = useState(null)

  const bannerList = banners ? banners.map((banner) => {
    return <SwiperSlide><img style={{height: '300px', width: "1050px"}}
                             src={banner.imageUrl}/></SwiperSlide>;
  }) : "";

  const productLists = cheapProducts ? cheapProducts.map((product) => {
    return <SwiperSlide><Container>
      <ProductImg src={product.imageUrl}/>
      <ProductDetails>
        <ProductName>{product.name}</ProductName>
        <ProductDiscountPercent>{product.discountPercent}%</ProductDiscountPercent>
        <ProductPrice>{Math.floor(product.price * (1 - product.discountPercent
            / 100))}원</ProductPrice>
        <ProductPrevPrice>{product.price}원</ProductPrevPrice>
      </ProductDetails>
    </Container></SwiperSlide>;
  }) : "";

  useEffect(() => {
    cheapProductApi().then(productsPromise => {
      setCheapProducts(productsPromise)
    })
    mainBannerApi().then(bannersPromise => {
      setBanners(bannersPromise)
    })
    mdChoiceApi().then(productsPromise => {
      setMdChoices(productsPromise)
    })
    todayRecommendationAPi().then(productsPromise => {
      setRecommendations(productsPromise)
    })
  }, [])

  console.log(cheapProducts)

  return (
      <>
        <Header/>
        <Homewrap>
          <ContainerSlide>
            <Row>
              <Slide>
                <Swiper
                    style={{height: '300px', width: "1050px"}}
                    className='banner'
                    slidesPerView={1}
                    navigation
                >
                  {bannerList}
                </Swiper>
              </Slide>
            </Row>
          </ContainerSlide>
          <SubTitle onClick={() => props.history.push(
              "/shop/goods/goods_list?list=sale")}>
            놓치면 후회할 가격 〉</SubTitle>
          <Section1wrap>
            <ContainerSlide>
              <Row>
                <SectionSlide>
                  <Swiper
                      style={{height: '500px', width: "1050px"}}
                      className='cheapProduct'
                      slidesPerView={3}
                      navigation
                  >
                    {productLists}
                  </Swiper>
                </SectionSlide>
              </Row>
            </ContainerSlide>
          </Section1wrap>
          {/*<Section2wrap>
            <ContainerSlide>
              <Row>
                <Section2>
                  <h2>section2</h2>
                </Section2>
              </Row>
            </ContainerSlide>
          </Section2wrap>
          <Section3wrap>
            <ContainerSlide>
              <Row>
                <Section3>
                  <h2>section3</h2>
                </Section3>
              </Row>
            </ContainerSlide>
          </Section3wrap>
          <Section4wrap>
            <ContainerSlide>
              <Row>
                <Section4>
                  <h2>section4</h2>
                </Section4>
              </Row>
            </ContainerSlide>
          </Section4wrap>*/}
        </Homewrap>
      </>
  )
}

export default Home;