import React, {useState} from 'react';
import DaumPostcode from "react-daum-postcode";
import styled from "styled-components";

const Input = styled.input`width:90%; height: 60px; margin:10px; font-size: 20px; color: #333; box-sizing: border-box; padding: 0 14px; background: #fff; border: 1px solid #ccc; border-radius: 3px; `
const AddressBtn = styled.button`width:90%; height: 60px; margin:10px; font-size: 20px; color: #5f0080; background: #fff; border: 1px solid #5f0080; border-radius: 3px; font-weight: bold; cursor: pointer;`
const ReWrite = styled.div`
  width:100%;
  margin:10px;
  padding: 20px;
  font-size: 20px;
  border-bottom: 1px solid gray;
`;
const Container = styled.div`
  width:100%;
  height: 400px;
  text-align: center; 
`;

const PopupPostCode = () => {
  // 우편번호 검색 후 주소 클릭 시 실행될 함수, data callback 용
  const [fullAddress, setFullAddress] = useState(null);
  const [detailedAddress, setDetailedAddress] = useState();
  const handlePostCode = (data) => {
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress += (extraAddress !== '' ? `, ${data.buildingName}`
            : data.buildingName);
      }
      setFullAddress((extraAddress !== '' ? ` (${extraAddress})` : ''))
    }
  }
  const setParentText = () => {
    window.opener.document.getElementById(
        "pInput").value = document.getElementById("cInput").value
    window.opener.document.getElementById(
        "pDetailedInput").value = document.getElementById("cDetailedInput").value
    window.opener.document.getElementById(
        "address").style.display = ''
    window.opener.document.getElementById(
        "detailedAddress").style.display = ''
    window.opener.document.getElementById(
        "search").style.display = 'none'
    window.close()
  }

  const postCodeStyle = {
    display: "block",
    width: "100%",
    height: "500px",
    padding: "7px",
  };

  return (<>
        {fullAddress && <>
          <ReWrite onClick={() => window.location.reload()}>〈 주소 재입력</ReWrite>
          <Container>
            <Input type="text" id="cInput" value={fullAddress} readOnly={true}/>
            <Input type='text'
                   id="cDetailedInput"
                   value={detailedAddress}
                   onChange={e => setDetailedAddress(e.target.value)}
                   placeholder="나머지 주소를 입력해 주세요"/>
            <AddressBtn type="button" value="전달하기" onClick={setParentText}>
              주소입력</AddressBtn>
          </Container>
        </>
        }
        <DaumPostcode style={postCodeStyle} onComplete={handlePostCode}/>
      </>
  )
}

export default PopupPostCode;