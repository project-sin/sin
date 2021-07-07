import React from 'react';
import styled from 'styled-components';

const Homewrap = styled.div``
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
    return (
        <Homewrap>
            <Slide><h2>Slide</h2></Slide>
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
    )
}

export default Home;