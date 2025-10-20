import styled from "@emotion/styled";
import { CirclePlus } from "lucide-react";
import { useState } from "react";
import { useMoimStore } from "../../../../types/State";

const MoimPayContainer = styled.div`
  background-color: #f0f4f8;
  padding: 50px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
`;
const Fieldset = styled.fieldset`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: none;
  border-bottom: 2px solid #ccc;
  padding: 0px;
  margin-bottom: 30px;
`;
const Legend = styled.legend`
  display: flex;
  font-size: 18px;
  font-weight: 600;
  color: #1e293b;
  padding: 10px;
`;

const MoimTag = styled.input`
  width: 50vw;
  display: block;
  margin: 10px 0;
  padding: 10px;
  text-align: center;
`;

//최소 모임 참여비. tag
export const MoimPay = () => {
  const [pluscount, setPlusCount] = useState(1);
  const MoimPay = useMoimStore().MoimDataStore.moimDetail.Pay;
  const MoimContent = useMoimStore().MoimDataStore.moimData.tag;
  const { setTag, setPay } = useMoimStore().MoimDataStore;

  return (
    <MoimPayContainer>
      <Fieldset>
        <Legend>최소 모임 참여비</Legend>
        <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
          <MoimTag
            type="number"
            value={MoimPay}
            onChange={(e) => {
              setPay(Number(e.target.value));
            }}
          />
          원
        </div>
      </Fieldset>
      <Fieldset>
        <Legend>모임을 검색하기 위한 테그</Legend>
        {Array.from({ length: pluscount }).map((_, index) => (
          <MoimTag
            key={index}
            type="text"
            placeholder={`태그 ${index + 1}`}
            value={MoimContent[index]}
            onChange={(e) => setTag(index, e.target.value)}
          />
        ))}
      </Fieldset>
      <CirclePlus
        size={50}
        color="#4f46e5"
        onClick={() => {
          if (pluscount < 5) {
            setPlusCount(pluscount + 1);
          } else {
            alert("최대 테그 지정 개수는 5개 입니다.");
          }
        }}
      />
    </MoimPayContainer>
  );
};
