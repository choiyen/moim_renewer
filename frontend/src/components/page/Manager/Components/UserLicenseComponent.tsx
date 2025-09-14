import styled from "@emotion/styled";
import { Search, CircleArrowLeft, CircleArrowRight } from "lucide-react";
import { useEffect, useState } from "react";

const LicenseContainer = styled.div`
  width: 100%;
  margin-top: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  background-color: gray;
  padding: 12px;
  border-radius: 4px;
  min-height: calc(80vh - 10px);
`;

const LicenseTitle = styled.div`
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 16px;
  color: white;
`;

const LicenseInputContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 40px;
`;

const LicenseSearchContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  background-color: white;
  padding: 50px;
  border-radius: 4px;
  width: 300px;
  height: 40px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 8px;
`;

const LicenseInput = styled.input`
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

  &::placeholder {
    color: #ccc;
  }

  background-color: white;
  color: black;

  & + svg {
    cursor: pointer;
    color: goldenrod;
    &:hover {
      color: skyblue;
    }
  }
`;

const SearchButton = styled.button<{ variant?: string }>`
  padding: 8px 16px;
  margin: 8px;
  border: ${({ variant }) =>
    variant === "cancel" ? "1px solid white" : "1px solid #357ab8"};
  border-radius: 4px;
  cursor: pointer;
  box-shadow: ${({ variant }) =>
    variant === "cancel"
      ? "0 2px 4px rgba(255, 255, 255, 0.1)"
      : "0 2px 4px rgba(53, 122, 184, 0.1)"};
  background-color: ${({ variant }) =>
    variant === "cancel" ? "inherit" : "#357ab8"};
  height: 40px;
  color: ${({ variant }) => (variant === "cancel" ? "#f5f5f5" : "#0c0707")};
  &:hover {
    background-color: ${({ variant }) =>
      variant === "cancel" ? "red" : "transparent"};
    border: ${({ variant }) =>
      variant === "cancel" ? "1px solid white" : "1px solid red"};
    color: ${({ variant }) => (variant === "cancel" ? "#f5f5f5" : "#f5f5f5")};
  }
`;

const LicenseTable = styled.table`
  width: 100%;
  border-collapse: collapse;
  background-color: white;
  border-radius: 8px;
  overflow: hidden;
`;

const TableTheader = styled.thead`
  background-color: #f4f4f4;
  color: #333;
  font-weight: bold;
`;

const TableRow = styled.tr`
  border-bottom: 1px solid #ddd;
  background-color: black;
  &:hover {
    background-color: black;
  }
`;

const TableHeader = styled.th`
  border-bottom: 2px solid #ddd;
  padding: 12px;
  text-align: left;
  color: black;
  background-color: #f4f4f4;
`;

const TableCell = styled.td<{ isHeader?: boolean }>`
  border-bottom: 1px solid #ddd;
  padding: 12px;
  text-align: center;

  font-weight: 300;
  color: ${({ isHeader }) => (isHeader ? "red" : "white")};
`;

const TableCheckbox = styled.input`
  width: 16px;
  height: 16px;
`;

const UserLicenseComponent = () => {
  const [selectnumber, setSelectnumber] = useState<number | null>(5); //페이지 네이션 넘버
  useEffect(() => {
    setSelectnumber(5);
  }, []);
  return (
    <LicenseContainer>
      <LicenseInputContainer>
        <LicenseTitle>회원 관리</LicenseTitle>
        <div
          style={{
            display: "flex",
            width: "fit-content",
            alignItems: "center",
            padding: "10px",
          }}
        >
          <LicenseSearchContainer>
            <LicenseInput type="text" placeholder="Search..." />
            <Search />
          </LicenseSearchContainer>
          <SearchButton>회원정지</SearchButton>
          <SearchButton variant="cancel">회원삭제</SearchButton>
        </div>
      </LicenseInputContainer>
      <LicenseTable>
        <TableTheader>
          <TableRow>
            <TableHeader>
              <TableCheckbox type="checkbox" name="" id="" />
            </TableHeader>
            <TableHeader>회원 ID</TableHeader>
            <TableHeader>이름</TableHeader>
            <TableHeader>이메일</TableHeader>
            <TableHeader>가입일</TableHeader>
            <TableHeader>상태</TableHeader>
            <TableHeader>역할</TableHeader>
            <TableHeader>권한관리</TableHeader>
          </TableRow>
        </TableTheader>
        <tbody>
          <TableRow>
            <TableCell>
              <TableCheckbox type="checkbox" name="" id="" />
            </TableCell>
            <TableCell>user123</TableCell>
            <TableCell>홍길동</TableCell>
            <TableCell>YYY145945@NAVER.COM</TableCell>
            <TableCell>2023-01-15</TableCell>
            <TableCell>활성</TableCell>
            <TableCell>일반회원</TableCell>
            <TableCell>
              <SearchButton>회원정지</SearchButton>
              <SearchButton variant="cancel">회원삭제</SearchButton>
              <SearchButton>역할변경</SearchButton>
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell>
              <TableCheckbox type="checkbox" name="" id="" />
            </TableCell>
            <TableCell>user123</TableCell>
            <TableCell>홍길동</TableCell>
            <TableCell>YYY145945@NAVER.COM</TableCell>
            <TableCell>2023-01-15</TableCell>
            <TableCell>활성</TableCell>
            <TableCell>일반회원</TableCell>
            <TableCell>
              <SearchButton>회원정지</SearchButton>
              <SearchButton variant="cancel">회원삭제</SearchButton>
              <SearchButton>역할변경</SearchButton>
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell>
              <TableCheckbox type="checkbox" name="" id="" />
            </TableCell>
            <TableCell>user123</TableCell>
            <TableCell>홍길동</TableCell>
            <TableCell>YYY145945@NAVER.COM</TableCell>
            <TableCell>2023-01-15</TableCell>
            <TableCell>활성</TableCell>
            <TableCell>일반회원</TableCell>
            <TableCell>
              <SearchButton>회원정지</SearchButton>
              <SearchButton variant="cancel">회원삭제</SearchButton>
              <SearchButton>역할변경</SearchButton>
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell>
              <TableCheckbox type="checkbox" name="" id="" />
            </TableCell>
            <TableCell>user123</TableCell>
            <TableCell>홍길동</TableCell>
            <TableCell>YYY145945@NAVER.COM</TableCell>
            <TableCell>2023-01-15</TableCell>
            <TableCell>활성</TableCell>
            <TableCell>일반회원</TableCell>
            <TableCell>
              <SearchButton>회원정지</SearchButton>
              <SearchButton variant="cancel">회원삭제</SearchButton>
              <SearchButton>역할변경</SearchButton>
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell>
              <TableCheckbox type="checkbox" name="" id="" />
            </TableCell>
            <TableCell>user123</TableCell>
            <TableCell>홍길동</TableCell>
            <TableCell>YYY145945@NAVER.COM</TableCell>
            <TableCell>2023-01-15</TableCell>
            <TableCell isHeader={true}>30일정지</TableCell>
            <TableCell>일반회원</TableCell>
            <TableCell>
              <SearchButton>회원정지</SearchButton>
              <SearchButton variant="cancel">회원삭제</SearchButton>
              <SearchButton>역할변경</SearchButton>
            </TableCell>
          </TableRow>
        </tbody>
      </LicenseTable>
      {selectnumber !== null && selectnumber > 0 ? (
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            gap: "12px",
            marginTop: "16px",
          }}
        >
          <CircleArrowLeft color="white" />
          <div style={{ display: "flex", justifyContent: "center" }}>
            {[...Array(selectnumber ?? 0)].map((_, idx) => (
              <button
                key={idx}
                style={{
                  margin: "4px",
                  padding: "2px 8px",
                  borderRadius: "25%",
                  border: "1px solid #ccc",
                  backgroundColor: "white",
                  color: "black",
                }}
              >
                {idx + 1}
              </button>
            ))}
          </div>
          <CircleArrowRight color="black" />
        </div>
      ) : null}
    </LicenseContainer>
  );
};
export default UserLicenseComponent;
