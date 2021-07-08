import React from 'react';
import styled from 'styled-components';

const Footerwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto; height: 400px; background: #444;`
const Row = styled.div``

const Footer = () => {
    return (
        <Footerwrap>
            <Container>
                <Row>
                    <h2>Footer</h2>
                </Row>
            </Container>
        </Footerwrap>
    )
}

export default Footer;