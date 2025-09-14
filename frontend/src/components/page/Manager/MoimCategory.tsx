import styled from "@emotion/styled";
import { useState } from "react";
import MoimSubCategory from "./Components/MoimSubCategory";
import ConsultSubCategory from "./Components/ConsultSubCategory";
import UserLicenseComponent from "./Components/UserLicenseComponent";
const CategoryContainer = styled.div`
  width: 100%;
  padding: 16px;
  border: 1px solid #eee;
  border-radius: 4px;
  background-color: black;
`;

const TitleStyle = styled.div`
  font-size: 1.25rem;
  font-weight: bold;
  margin-bottom: 8px;
  color: white;
`;
const DescriptionStyle = styled.div`
  font-size: 1rem;
  color: gray;

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
    margin: 20px 0px; /* 글과 밑줄 간격 */
  }
`;

const MoimContainer = styled.div`
  margin-top: 16px;
  display: flex;
  gap: 12px;
  background-color: gray;
  padding: 12px;
  border-radius: 4px;
  min-height: calc(80vh - 10px);
`;

const CategoryButton = styled.button<{ active: boolean }>`
  color: ${(props) => (props.active ? "gray" : "white")};
  border-bottom: ${(props) => (props.active ? "4px solid skyblue" : "none")};
  padding: 8px 16px;
  cursor: pointer;
  margin-right: 8px;
`;

const MoimCategory = () => {
  const [CategoryType, setCategoryType] = useState<
    "moim" | "consult" | "license"
  >("moim");

  return (
    <CategoryContainer>
      <TitleStyle>카테고리 설정</TitleStyle>
      <DescriptionStyle>
        게시판과 모임 카테고리 설정을 조절할 수 있습니다.
      </DescriptionStyle>
      <CategoryButton
        onClick={() => setCategoryType("moim")}
        active={CategoryType === "moim"}
      >
        모임 카테고리
      </CategoryButton>
      <CategoryButton
        onClick={() => setCategoryType("consult")}
        active={CategoryType === "consult"}
      >
        상담 카테고리
      </CategoryButton>
      <CategoryButton
        onClick={() => setCategoryType("license")}
        active={CategoryType === "license"}
      >
        회원 관리 설정
      </CategoryButton>
      <MoimContainer>
        {CategoryType === "moim" ? (
          <MoimSubCategory />
        ) : CategoryType === "consult" ? (
          <ConsultSubCategory />
        ) : (
          <UserLicenseComponent />
        )}
      </MoimContainer>
    </CategoryContainer>
  );
};

export default MoimCategory;
