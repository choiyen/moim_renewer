import React from "react";
import styled from "@emotion/styled";
import { CheckCircle, Mail, Phone, User } from "lucide-react";
import { useState } from "react";

const MoimLicenseContainer = styled.div`
  width: 100%;
  margin-top: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  background-color: black;
  padding: 12px;
  border-radius: 4px;
  min-height: calc(80vh - 10px);
`;

const MoimCheckingContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 8px;
  color: white;
`;

const MoimLicenseSelect = styled.select`
  padding: 10px;
  margin: 10px;
  border: none;
  border-radius: 4px;
  font-size: 1rem;

  height: 40px;
  &:focus {
    outline: none;
    box-shadow: 0 0 0 2px skyblue;
  }
  background-color: white;
  color: black;
  &::placeholder {
    color: #ccc;
  }
`;

const MoimLicenseOption = styled.option`
  font-size: 1rem;
  color: black;
  background-color: white;
  &:hover {
    background-color: #f1f1f1;
  }
  &:active {
    background-color: #e2e8f0;
    font-weight: bold;
  }

  padding: 8px;
  border: none;
  cursor: pointer;
  &:focus {
    outline: none;
    box-shadow: 0 0 0 2px skyblue;
  }
`;

const MoimCardContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  color: white;
  gap: 12px;
  background-color: darkgray;
  padding: 12px;
  border-radius: 4px;
  min-height: calc(80vh - 10px);
`;

const MoimCard = styled.div`
  width: 300px;
  margin: 20px;
  padding: 16px;
  border: 1px solid #eee;
  border-radius: 4px;
  background-color: black;
`;

const MoimCardLabel = styled.div`
  display: flex;
  align-items: center;
  padding: 5px;
  gap: 10px;
`;

interface LicenseCheckbuttonProps {
  cancel?: boolean;
}

const LicenseCheckbutton = styled.button<LicenseCheckbuttonProps>`
  padding: 4px 16px;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  border: none;

  background-color: ${({ cancel }) => (cancel ? "#f87171" : "#3b82f6")};
  color: white;

  &:hover {
    background-color: ${({ cancel }) => (cancel ? "#dc2626" : "#2563eb")};
  }

  &:active {
    transform: scale(0.97);
  }
`;

const MoimCheckInput = styled.input`
  width: 20px;
  height: 20px;
  cursor: pointer;
  appearance: none;
  border: 2px solid #4f46e5;
  border-radius: 50%;
  position: relative;

  &:checked {
    background-color: #4f46e5;
    border-color: #4f46e5;
  }

  &:checked::after {
    content: "";
    position: absolute;
    top: 4px;
    left: 4px;
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background: white;
  }
`;

const MoimLicenseTitle = styled.h2`
  font-size: 1.5rem; /* 제목 크기 */
  font-weight: 600; /* 약간 굵게 */
  color: #f5f5f5;
`;

const MoimLicenseDescription = styled.p`
  font-size: 0.875rem; /* 작은 글씨 */
  color: #c7d2fe; /* 검은 배경에서 보이는 연한 전자 느낌 색상 */
  margin: 0 0 24px 0; /* 아래 여백 늘림 */
  line-height: 1.4;
  position: relative;

  &::after {
    content: "";
    display: block;
    height: 1px; /* 밑줄 두께 */
    width: 100%;
    background: linear-gradient(
      to right,
      rgba(199, 210, 254, 0),
      rgba(199, 210, 254, 0.7),
      rgba(199, 210, 254, 0)
    );
    margin-top: 20px; /* 글과 밑줄 간격 */
  }
`;

interface MoimLicense {
  id: number;
  username: string;
  email: string;
  phone: string;
  status: "approved" | "pending" | "rejected";
}

const StatusIcon = ({ status }: { status: string }) => {
  const baseStyle: React.CSSProperties = {
    padding: "4px 10px",
    borderRadius: "12px",
    fontWeight: "bold",
    display: "inline-block",
    fontSize: "0.875rem",
  };

  switch (status) {
    case "approved":
      return (
        <div
          style={{ ...baseStyle, backgroundColor: "#6ee7b7", color: "#065f46" }}
        >
          승인
        </div>
      );
    case "pending":
      return (
        <div
          style={{ ...baseStyle, backgroundColor: "#fef08a", color: "#713f12" }}
        >
          대기
        </div>
      );
    case "rejected":
      return (
        <div
          style={{ ...baseStyle, backgroundColor: "#fca5a5", color: "#7f1d1d" }}
        >
          거부
        </div>
      );
    default:
      return null;
  }
};

const MoimLicenseComponent = () => {
  const [MoimUserList] = useState<MoimLicense[]>([
    {
      id: 1,
      username: "이민호",
      email: "user1@example.com",
      phone: "010-1234-5678",
      status: "pending",
    },
    {
      id: 2,
      username: "빅정우",
      email: "user2@example.com",
      phone: "010-2345-6789",
      status: "approved",
    },
    {
      id: 3,
      username: "최미나",
      email: "user3@example.com",
      phone: "010-3456-7890",
      status: "rejected",
    },
    {
      id: 4,
      username: "이성호",
      email: "user4@example.com",
      phone: "010-4567-8901",
      status: "pending",
    },
    {
      id: 5,
      username: "김진혁",
      email: "user5@example.com",
      phone: "010-5678-9012",
      status: "approved",
    },
    {
      id: 6,
      username: "user6",
      email: "user6@example.com",
      phone: "010-6789-0123",
      status: "pending",
    },
    {
      id: 7,
      username: "user7",
      email: "user7@example.com",
      phone: "010-7890-1234",
      status: "rejected",
    },
    {
      id: 8,
      username: "user8",
      email: "user8@example.com",
      phone: "010-8901-2345",
      status: "approved",
    },
  ]);

  return (
    <MoimLicenseContainer>
      <MoimLicenseTitle>모임 관리 페이지</MoimLicenseTitle>
      <MoimLicenseDescription>
        생성한 모임을 신청한 회원을 관리할 수 있습니다.
      </MoimLicenseDescription>
      <MoimCheckingContainer>
        <span>생성된 모임 : </span>
        <MoimLicenseSelect>
          {/* 본인이 개설한 모임에 관하여 뜨게 됨 */}
          <MoimLicenseOption value="">모임 1</MoimLicenseOption>
          <MoimLicenseOption value="">모임 2</MoimLicenseOption>
          <MoimLicenseOption value="">모임 3</MoimLicenseOption>
          <MoimLicenseOption value="">모임 4</MoimLicenseOption>
          <MoimLicenseOption value="">모임 5</MoimLicenseOption>
        </MoimLicenseSelect>
      </MoimCheckingContainer>
      <div
        style={{
          color: "white",
          display: "flex",
          alignItems: "center", // 수직 가운데 정렬
          gap: "8px", // 체크박스와 텍스트 사이 간격
        }}
      >
        <MoimCheckInput type="checkbox" id="join-approval" />
        <label htmlFor="join-approval" style={{ color: "white" }}>
          모임 전체 승인
        </label>
      </div>
      <MoimCardContainer>
        {MoimUserList.map((user) => (
          <MoimCard key={user.id}>
            <div
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
                marginBottom: "10px",
              }}
            >
              <div>모임 가입 신청자</div>
              <MoimCheckInput type="checkbox" name="" id="join-approval" />
            </div>
            <MoimCardLabel>
              <User /> {user.username}
            </MoimCardLabel>
            <MoimCardLabel>
              <Mail /> {user.email}
            </MoimCardLabel>
            <MoimCardLabel>
              <Phone /> {user.phone}
            </MoimCardLabel>
            <MoimCardLabel>
              <CheckCircle /> <StatusIcon status={user.status} />
            </MoimCardLabel>
            <div
              style={{
                display: "flex",
                justifyContent: "space-around",
                alignItems: "center",
                marginTop: "10px",
              }}
            >
              <LicenseCheckbutton>승인</LicenseCheckbutton>
              <LicenseCheckbutton cancel={true}>거부</LicenseCheckbutton>
            </div>
          </MoimCard>
        ))}
      </MoimCardContainer>
    </MoimLicenseContainer>
  );
};
export default MoimLicenseComponent;
// 모임 개설자만 접속 가능하도록 설정 필요
// 본인이 생성한 모임만 select box를 이용해서 가져오도록 함.
// 모임 데이터를 가져왔으면 모임 허가 필요 설정이 true이면 가입 승인 대기 중인 명단을 띄움.
// 승인 대기 중인 명단에서 승인 또는 거절 버튼을 누르면 해당 유저의 상태가 업데이트됨.
// 승인 거절 후에는 다시 승인 대기 중인 명단에서 사라짐.
// 거절 시에는 해당 유저에게 거절 사유를 입력할 수 있는 모달이 뜸.
// 거절 사유는 해당 유저에게 메일로 전송됨.
