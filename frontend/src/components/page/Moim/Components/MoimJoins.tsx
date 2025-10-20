import styled from "@emotion/styled";
import { useMoimStore } from "../../../../types/State";
import { Table } from "lucide-react";

const MoimJoinsContainer = styled.div`
  width: 70vw;
  background-color: #f0f4f8;
  border: 1px solid #ccc;
  padding: 50px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
`;

const MoimJoinFeldSet = styled.fieldset`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: none;
`;
const MoimJoinTiTle = styled.legend`
  font-size: 24px;
  font-weight: 600;
  color: #1e293b;
  margin-bottom: 20px;
`;
const MoimTable = styled.table`
  width: 50vw;
  margin: 0 auto; /* 테이블 자체를 화면 가운데로 */
  border-collapse: collapse;
  margin-bottom: 20px;

  & th,
  & td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: center; /* 텍스트 중앙 정렬 */

    & img {
      display: block; /* 블록으로 변환 */
      margin: 0 auto; /* 좌우 자동 마진으로 중앙 정렬 */
      border-radius: 8px;
      object-fit: cover;
      width: 200px; /* 원하는 크기 */
      height: 200px; /* 원하는 크기 */
    }
    & button {
      padding: 10px 20px;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 6px;
      cursor: pointer;
      &:hover {
        background-color: #0056b3;
      }
    }
  }

  & th {
    background-color: #f9fafb;
  }
`;

const MoimJoins = () => {
  const { moimData, moimDetail } = useMoimStore().MoimDataStore;
  const handleMoimJoin = () => {
    const data = {
      title: moimData.title,
      isOnline: moimData.isOnline,
      maxpeople: moimData.maxpeople,
      expirationDate: moimData.expirationDate,
      evenDate: moimData.evenDate,
      location: moimData.location,
      representImg: moimData.image,
      organizer: moimData.organizer,
      description: moimData.description,
      tag: moimData.tag,
    };
    console.log("모임 등록 신청 데이터 :", data);

    alert("모임 등록 신청이 완료되었습니다.");
  };

  return (
    <MoimJoinsContainer>
      <MoimJoinFeldSet>
        <MoimJoinTiTle>모임 생성 정보</MoimJoinTiTle>
        <MoimTable>
          <thead>
            <tr>
              <th>항목</th>
              <th>내용</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>모임 카테고리</td>
              <td>{moimData.title}</td>
            </tr>
            <tr>
              <td>모임 소개</td>
              <td>{moimData.description}</td>
            </tr>
            <tr>
              <td>모임 태그</td>
              <td>{moimData.tag.join(", ")}</td>
            </tr>
            <tr>
              <td>모임 이미지</td>
              <td>
                <img src={moimData.image} alt="모임 이미지" />
              </td>
            </tr>
            <tr>
              <td>모임 가입 인원</td>
              <td>
                {moimDetail.minPeople} 이상 {moimData.maxpeople} 이하
              </td>
            </tr>
            <tr>
              <td>모임 참가비</td>
              <td>{moimDetail.Pay} 원</td>
            </tr>
            <tr>
              <td>모임 내용</td>
              <td dangerouslySetInnerHTML={{ __html: moimDetail.content }} />
            </tr>
            <tr>
              <td colSpan={2} style={{ textAlign: "center" }}>
                <button onClick={() => handleMoimJoin()}>등록 신청</button>
              </td>
            </tr>
          </tbody>
        </MoimTable>
      </MoimJoinFeldSet>
    </MoimJoinsContainer>
  );
};

export default MoimJoins;
