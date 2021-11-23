import React, {useEffect, useState} from 'react';
import Modal from "react-modal";
import CancelButton from "./CancelButton";
import CompleteButton from "./CompleteButton";
import styled from "styled-components";

const Name = styled.div`
  position: absolute;
  top: 15px;
  left: 10px;
  width: 100%;
  height: 30px;
  font-size: 15px;
  text-align:left;
`;

const PrevPrice = styled.div`
  display: inline-block;
  color: gray;
  text-decoration:line-through
`;

const Price = styled.div`
  position: absolute;
  top: 55px;
  left: 10px;
  width: 100%;
  height: 30px;
  font-size: 15px;
  text-align:left;
  font-weight: bold;
`;

const Calculator = styled.div`
  position: absolute;
  top: 50px;
  right: 5px;
  width: 100px;
  height: 30px;
  font-weight: bold;
  border: 1px solid rgb(200,200,200);
  -webkit-user-select:none; 
  -moz-user-select:none; 
  -ms-user-select:none; 
  user-select:none
`;

const MinusButton = styled.div`
  position: absolute;
  display: inline-block;
  cursor: pointer;
  top: -18px;
  left: 7px;
  font-size: 40px;
`;

const Count = styled.div`
  position: absolute;
  display: inline-block;
  cursor: pointer;
  bottom: 0;
  left: 35px;
  font-size: 20px;
  width: 30px;
  height:30px;
`;

const PlusButton = styled.div`
  position: absolute;
  display: inline-block;
  cursor: pointer;
  font-size: 30px;
  right: 4px;
  bottom: 0;
`;

const Sum = styled.div`
  position: absolute;
  top: 160px;
  left: 10px;
  width: 100%;
  height: 30px;
  font-size: 15px;
  text-align:left;
`;

const TotalPrice = styled.div`
  position: absolute;
  top: 155px;
  right: 10px;
  width: 100%;
  height: 30px;
  font-size: 20px;
  text-align: right;
  font-weight: bold;
`;

const NonLoginInfo = styled.div`
  position: absolute;
  top: 190px;
  right: 10px;
  width: 100%;
  height: 30px;
  font-size: 13px;
  text-align: right;
`;

const AddCartListModal = ({
  openAddCartListModal,
  setOpenAddCartListModal,
  productName,
  productPrice,
  productCode,
  discountInModal,
  accessToken
}) => {
  const [count, setCount] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);
  const discountedPrice = Math.floor(productPrice * (1 - discountInModal
      / 100));
  useEffect(() => {
    setTotalPrice(accessToken ? count * discountedPrice : count * productPrice);
  }, [count]);
  return (
      <Modal
          isOpen={openAddCartListModal}        //모달의 오픈유무를 bool값으로 정한다.
          shouldCloseOnOverlayClick={false}     //close버튼을 눌러야만 모달이 종료
          style={{
            overlay: {
              backgroundColor: '#00112255'
            },
            content: {
              maxWidth: '400px',
              margin: 'auto',
              backgroundColor: 'white',
              maxHeight: '300px',
              textAlign: 'center',
              borderRadius: '20px',
            }
          }}
          contentLabel="create group"       //모달의 라벨
      >
        <div style={{
          position: "relative",
          width: "100%",
          height: "280px",
        }}>
          <Name>{productName}</Name>
          {accessToken ?
              <Price>{discountedPrice} 원&nbsp;
                <PrevPrice> {productPrice} 원</PrevPrice></Price>
              : <Price>{productPrice} 원</Price>
          }
          <Calculator>
            <MinusButton onClick={() => {
              if (count >= 1) {
                setCount(count - 1)
              } else {
                setCount(0)
              }
            }}>-</MinusButton>
            <Count>{count}</Count>
            <PlusButton onClick={() => {
              setCount(count + 1)
            }}>+</PlusButton>
          </Calculator>
          <Sum>합계</Sum>
          <NonLoginInfo>로그인 후, 회원할인가와 적립혜택 적용</NonLoginInfo>
          <TotalPrice>{totalPrice} 원</TotalPrice>
          <CancelButton
              setOpenAddCartListModal={setOpenAddCartListModal}
              setCount={setCount}
              setTotalPrice={setTotalPrice}
          />
          <CompleteButton
              productCode={productCode}
              count={count}
              setOpenAddCartListModal={setOpenAddCartListModal}
              setCount={setCount}
              setTotalPrice={setTotalPrice}
          />
        </div>
      </Modal>
  );
};

export default AddCartListModal;