import styled from "@emotion/styled";
import { ConsultType } from "../../../types/MoimDataDummy";
import { useRef, useState } from "react";
import {
  AiOutlineAlignLeft,
  AiOutlineAlignCenter,
  AiOutlineAlignRight,
  AiOutlineBold,
  AiOutlineItalic,
  AiOutlineUnderline,
  AiOutlineStrikethrough,
} from "react-icons/ai";
import { useNavigate } from "react-router-dom";
import { FaArrowLeft } from "react-icons/fa";

// =================== Styled Components ===================
const ConsultContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom, #e0e7ff, #f8fafc);
  min-height: 100vh;
  padding-bottom: 40px;
`;

const HandleContainer = styled.div`
  width: 70vw;
  background-color: aliceblue;
  text-align: center;
  border-radius: 12px;
  padding: 50px;
`;

const Consulttitle = styled.h2`
  font-size: 25px;
  padding: 10px;
`;

const ConSultSelect = styled.select`
  width: 200px;
  height: 50px;
`;

const ConSultInput = styled.input`
  width: 100%;
  height: 50px;
  border: 1px solid #ccc;
`;

const ConsultSpace = styled.div`
  background: linear-gradient(to bottom, #e0e7ff, #f8fafc);
  display: flex;
  justify-content: center;
  width: 100%;

  input,
  select {
    border: 1px solid #ccc;
    padding: 8px;
    flex: 1;
    box-sizing: border-box;
  }

  input {
    border-right: 2px solid gray;
  }
`;

const ButtonSpace = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding: 15px;
`;

const BoardEditor = styled.textarea<{ bgColor: string }>`
  width: 100%;
  min-height: 600px;
  border: 1px solid #ccc;
  overflow: auto;
  background-color: ${({ bgColor }) => bgColor};
`;

// 툴바 버튼 스타일
const ToolbarButton = styled.button`
  padding: 4px 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: white;
  cursor: pointer;

  &:hover {
    background-color: #f0f0f0;
  }
`;

const BoardButton = styled.button<{ bgColor: string }>`
  background-color: ${({ bgColor }) => bgColor};
  color: white;
  font-weight: bold;
  font-size: 16px;
  padding: 10px 20px;
  width: 100px;
  height: 50px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s ease;

  &:hover {
    filter: brightness(1.1);
  }

  &:active {
    transform: scale(0.98);
  }
`;

// =================== Component ===================
const ConsultInsert = () => {
  const editorRef = useRef<HTMLTextAreaElement>(null);
  const [bgColor] = useState("#ffffff");
  const [titleType, settitleType] = useState<ConsultType>("서비스");
  const nativeGate = useNavigate();
  const applyStyle = (command: string, value?: string) => {
    document.execCommand(command, false, value);
  };
  const Backed = () => {
    nativeGate("/consult/");
  };
  return (
    <ConsultContainer>
      <div
        style={{
          width: "100%",
          padding: "25px",
          marginBottom: "20px",
          display: "flex",
          justifyContent: "flex-end", // 오른쪽 정렬
        }}
      >
        <button
          className="flex items-center gap-2 p-3 border bg-gray-200 rounded shadow-md hover:bg-gray-300 hover:shadow-lg transition-all duration-200"
          onClick={() => {
            Backed();
          }}
        >
          <FaArrowLeft size={20} />
          이전
        </button>
      </div>
      <HandleContainer>
        {/* 타이틀 */}
        <Consulttitle>{titleType} 게시판</Consulttitle>
        {/* 닉네임 + 카테고리 선택 */}
        <ConsultSpace>
          <input type="text" placeholder="닉네임" />
          <ConSultSelect
            value={titleType}
            onChange={(e: React.ChangeEvent<HTMLSelectElement>) =>
              settitleType(e.target.value as ConsultType)
            }
          >
            {Object.values(ConsultType).map((label) => (
              <option key={label} value={label}>
                {label}
              </option>
            ))}
          </ConSultSelect>
        </ConsultSpace>
        {/* 스타일 옵션 툴바 */}
        <div
          style={{
            display: "flex",
            flexWrap: "nowrap", // 한 줄로 고정
            backgroundColor: "white", // 오타 수정: while → white
            justifyContent: "flex-end", // 오른쪽 정렬
            alignItems: "center", // 수직 중앙 정렬
            gap: "8px", // 아이템 간 간격
            overflowX: "auto", // 화면이 좁으면 스크롤 가능
            border: "1px solid #ccc",
            paddingRight: "5px",
          }}
        >
          {/* 글 제목 입력 */}
          <ConSultInput type="text" placeholder="제목" />
          {/* 글자 굵게 / 기울임 / 밑줄 / 취소선 */}
          <ToolbarButton onClick={() => applyStyle("bold")}>
            <AiOutlineBold size={20} />
          </ToolbarButton>
          <ToolbarButton onClick={() => applyStyle("italic")}>
            <AiOutlineItalic size={20} />
          </ToolbarButton>
          <ToolbarButton onClick={() => applyStyle("underline")}>
            <AiOutlineStrikethrough size={20} />
          </ToolbarButton>
          <ToolbarButton onClick={() => applyStyle("strikeThrough")}>
            <AiOutlineUnderline size={20} />
          </ToolbarButton>

          {/* 글자색 */}
          <input
            type="color"
            onChange={(e) => applyStyle("foreColor", e.target.value)}
            title="글자색"
          />

          {/* 배경색 */}
          <input
            type="color"
            onChange={(e) => applyStyle("hiliteColor", e.target.value)}
            title="배경색"
          />

          {/* 글자 크기 */}
          <select
            onChange={(e) => applyStyle("fontSize", e.target.value)}
            title="글자 크기"
          >
            <option value="1">14px</option>
            <option value="2">16px</option>
            <option value="3">18px</option>
            <option value="4">20px</option>
          </select>

          <ToolbarButton onClick={() => applyStyle("justifyLeft")}>
            <AiOutlineAlignLeft size={20} />
          </ToolbarButton>

          <ToolbarButton onClick={() => applyStyle("justifyCenter")}>
            <AiOutlineAlignCenter size={20} />
          </ToolbarButton>

          <ToolbarButton onClick={() => applyStyle("justifyRight")}>
            <AiOutlineAlignRight size={20} />
          </ToolbarButton>
        </div>

        {/* 리치 텍스트 에디터 */}
        <BoardEditor
          contentEditable
          ref={editorRef}
          suppressContentEditableWarning
          bgColor={bgColor}
          spellCheck={false}
          placeholder=" 여기에 글 내용을 작성하세요..."
        />
        <ButtonSpace>
          <BoardButton bgColor={"#28a745"}>작성</BoardButton>
          <BoardButton
            bgColor={"#dc3545"}
            onClick={() => {
              nativeGate("/consult/");
            }}
          >
            취소
          </BoardButton>
        </ButtonSpace>
      </HandleContainer>
    </ConsultContainer>
  );
};

export default ConsultInsert;
