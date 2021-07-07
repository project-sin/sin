import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Eventwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Eventli = styled.div`display: block; height: 200px; background: #dbdbdb; margin: 10px 0px; &:hover {cursor: pointer;}`


const Event = ({history}) => {
    const move = () => {
        history.push('/')
    }
    return(
        <Eventwrap>
            <Container>
                <Row>
                    <ul>
                        <Eventli onClick={move}></Eventli>
                        <Eventli onClick={move}></Eventli>
                        <Eventli onClick={move}></Eventli>
                        <Eventli onClick={move}></Eventli>
                        <Eventli onClick={move}></Eventli>
                        <Eventli onClick={move}></Eventli>
                    </ul>
                </Row>
            </Container>
        </Eventwrap>
    )
}

export default Event;