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
const ContainerSlide = styled.div`width: 1050px; margin: 0 auto;`
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Slide = styled.div`height: 300px; background: #999;`
const Section1wrap = styled.div``
const Section1 = styled.div`height: 300px; background: #888;  overflow: hidden;`
const Section1item = styled.div`float: left; width: 300px; height: 200px; margin: 10px;`
const Section2wrap = styled.div``
const Section2 = styled.div`height: 300px; background: #777;`
const Section3wrap = styled.div``
const Section3 = styled.div`height: 300px; background: #666;`
const Section4wrap = styled.div``
const Section4 = styled.div`height: 300px; background: #555;`

const Home = () => {
  const [banners, setBanners] = useState(null)
  const [cheapProducts, setCheapProducts] = useState(null)
  const [mdChoices, setMdChoices] = useState(null)
  const [recommendations, setRecommendations] = useState(null)

  const bannerList = banners ? banners.map((banner) => {
    return <SwiperSlide><img style={{height: '300px', width: "1050px"}}
                             src={banner.imageUrl}/></SwiperSlide>;
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
          <Section1wrap>
            <Container>
              <Row>
                <Section1 className='clearfix'>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                  <Section1item>item</Section1item>
                </Section1>
              </Row>
            </Container>
          </Section1wrap>
          <Section2wrap>
            <Container>
              <Row>
                <Section2>
                  <h2>section2</h2>
                </Section2>
              </Row>
            </Container>
          </Section2wrap>
          <Section3wrap>
            <Container>
              <Row>
                <Section3>
                  <h2>section3</h2>
                </Section3>
              </Row>
            </Container>
          </Section3wrap>
          <Section4wrap>
            <Container>
              <Row>
                <Section4>
                  <h2>section4</h2>
                </Section4>
              </Row>
            </Container>
          </Section4wrap>
        </Homewrap>
      </>
  )
}

export default Home;