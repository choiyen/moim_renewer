import styled from "@emotion/styled";
import { useEffect, useRef, useState } from "react";
import {
  AiOutlineAlignLeft,
  AiOutlineAlignCenter,
  AiOutlineAlignRight,
  AiOutlineBold,
  AiOutlineItalic,
  AiOutlineUnderline,
  AiOutlineStrikethrough,
} from "react-icons/ai";
import { useNavigate, useParams } from "react-router-dom";
import { FaArrowLeft } from "react-icons/fa";
import { GET, POST, PUT } from "../../comon/axios/axiosInstance";

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

const BoardEditor = styled.div<{ bgColor: string }>`
  width: 100%;
  min-height: 600px;
  border: 1px solid #ccc;
  overflow: auto;
  background-color: ${({ bgColor }) => bgColor};
  padding: 8px;

  /* placeholder 스타일 */
  &[data-placeholder]:empty::before {
    content: attr(data-placeholder);
    color: #888;
  }
`;

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

interface consult {
  consultCategoryId: number;
  consultType: string;
}

// =================== Component ===================
const SaveConsult = () => {
  const editorRef = useRef<HTMLDivElement>(null);
  const [bgColor] = useState("#ffffff");
  const [titleType, setTitleType] = useState<consult[]>([
    { consultCategoryId: 0, consultType: "" },
  ]);
  const [nickname, setNickname] = useState("");
  const [title, setTitle] = useState("");
  const [type, settype] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
  const isEdit = !!id; // id 있으면 수정 모드

  // =================== useEffect ===================
  useEffect(() => {
    const user = localStorage.getItem("user");
    if (user) {
      const userType = JSON.parse(user);
      GET({ url: "/ConsultCategory" }).then((res) => {
        console.log(res);
        setTitleType(res.data);
        setNickname(userType.nickname || "");
      });
    }

    if (isEdit) {
      GET({ url: `/consult`, params: { ConsultId: id } }).then((res) => {
        console.log(res);
        setTitle(res.data[0].Title || "");
        const SelectedCategory = titleType.find(
          (e) => e.consultCategoryId === res.data[0].consultCategoryId
        );
        console.log(SelectedCategory);
        settype(SelectedCategory?.consultType || "");
        if (editorRef.current) {
          editorRef.current.innerHTML = res.data[0].consultComment || "";
        }
      });
    }
  }, [id, isEdit]);

  // =================== 함수 ===================
  const applyStyle = (command: string, value?: string) => {
    document.execCommand(command, false, value);
  };

  const handleBack = () => {
    navigate("/consult/");
  };

  const handleSubmit = async () => {
    try {
      const contentHTML = editorRef.current?.innerHTML || "";
      const selectedCategory = titleType.find((e) => e.consultType === type);
      if (!selectedCategory) {
        alert("카테고리를 선택해주세요.");
        return;
      }

      const body = {
        Nickname: nickname || "",
        Title: title || "",
        moimConsultId: id ? id : "",
        consultCategoryId: selectedCategory.consultCategoryId,
        consultComment: contentHTML || "",
      };

      const res = isEdit
        ? await PUT({ url: "/consult", data: body })
        : await POST({ url: "/consult", data: body });
      if (res.resultType === "success") {
        alert(isEdit ? "게시글 수정 완료!" : "게시글 작성 완료!");
        navigate("/consult/");
      } else {
        alert("작성 실패");
      }
    } catch (err) {
      console.error(err);
      alert("서버 오류 발생");
    }
  };

  // =================== 렌더 ===================
  return (
    <ConsultContainer>
      <div
        style={{
          width: "100%",
          padding: "25px",
          marginBottom: "20px",
          display: "flex",
          justifyContent: "flex-end",
        }}
      >
        <button
          className="flex items-center gap-2 p-3 border bg-gray-200 rounded shadow-md hover:bg-gray-300 hover:shadow-lg transition-all duration-200"
          onClick={handleBack}
        >
          <FaArrowLeft size={20} />
          이전
        </button>
      </div>

      <HandleContainer>
        <Consulttitle>게시판</Consulttitle>

        {/* 닉네임 + 카테고리 선택 */}
        <ConsultSpace>
          <input
            type="text"
            placeholder="닉네임"
            value={nickname || ""}
            readOnly
          />
          <ConSultSelect
            value={type || ""}
            onChange={(e) => settype(e.target.value)}
          >
            {titleType.map((label) => (
              <option
                key={label.consultCategoryId}
                value={label.consultType || ""}
              >
                {label.consultType || ""}
              </option>
            ))}
          </ConSultSelect>
        </ConsultSpace>

        {/* 스타일 옵션 툴바 */}
        <div
          style={{
            display: "flex",
            flexWrap: "nowrap",
            backgroundColor: "white",
            justifyContent: "flex-end",
            alignItems: "center",
            gap: "8px",
            overflowX: "auto",
            border: "1px solid #ccc",
            paddingRight: "5px",
          }}
        >
          <ConSultInput
            type="text"
            placeholder="제목"
            value={title || ""}
            onChange={(e) => setTitle(e.target.value)}
          />
          <ToolbarButton onClick={() => applyStyle("bold")}>
            <AiOutlineBold size={20} />
          </ToolbarButton>
          <ToolbarButton onClick={() => applyStyle("italic")}>
            <AiOutlineItalic size={20} />
          </ToolbarButton>
          <ToolbarButton onClick={() => applyStyle("underline")}>
            <AiOutlineUnderline size={20} />
          </ToolbarButton>
          <ToolbarButton onClick={() => applyStyle("strikeThrough")}>
            <AiOutlineStrikethrough size={20} />
          </ToolbarButton>

          <input
            type="color"
            onChange={(e) => applyStyle("foreColor", e.target.value)}
            title="글자색"
          />
          <input
            type="color"
            onChange={(e) => applyStyle("hiliteColor", e.target.value)}
            title="배경색"
          />

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
          data-placeholder="여기에 글 내용을 작성하세요..."
        />

        <ButtonSpace>
          <BoardButton bgColor="#28a745" onClick={handleSubmit}>
            {isEdit ? "수정" : "작성"}
          </BoardButton>
          <BoardButton bgColor="#dc3545" onClick={handleBack}>
            취소
          </BoardButton>
        </ButtonSpace>
      </HandleContainer>
    </ConsultContainer>
  );
};

export default SaveConsult;
