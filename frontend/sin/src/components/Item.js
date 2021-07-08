import React from 'react';
import styled from 'styled-components';

const Itemwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Section1 = styled.div`height: 400px; background: #999;`
const Section1left = styled.div`float: left; width: 30%; height: 400px; margin: 10px; background: #fff;`
const Section1right = styled.div`float: right; width: 60%; height: 400px; margin: 10px; background: #fff;`
const Section2 = styled.div`height: 300px; background: #888;`
const Section3 = styled.div`height: 300px; background: #777;`
const Section4 = styled.div`height: 300px; background: #666;`

const Item = () => {
    return (
        <Itemwrap>
            <Container>
                <Row>
                    <Section1 className='clearfix'>
                        <Section1left>img</Section1left>
                        <Section1right>iteminfo</Section1right>
                    </Section1>
                    <Section2></Section2>
                    <Section3></Section3>
                    <Section4></Section4>
                </Row>
            </Container>
        </Itemwrap>        
    )
}

export default Item;