import styled from "@emotion/styled";
import { Pencil, Camera } from "lucide-react";
import { useState } from "react";
import { useMoimStore } from "../../../../types/State"; // 경로는 프로젝트 구조에 맞게 조정하세요.
import "../Components/CKeditor.css"; // CKEditor 스타일을 위한 CSS 파일

const Fieldset = styled.fieldset`
  border: none;
  border-bottom: 2px solid #ccc;
  padding: 0px;
  margin-bottom: 30px;
  width: 100%;
`;

const Fieldset2 = styled.fieldset`
  border: none;
  padding: 10px;
  margin-bottom: 30px;
  width: 100%;
`;

const Legend = styled.legend`
  font-size: 18px;
  font-weight: 600;
  color: #1e293b;
  padding: 10px;
`;

const HiddenInput = styled.input`
  display: none;
`;

const FileLabel = styled.label`
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  height: 250px;
  border: 2px dashed #3b82f6;
  border-radius: 12px;
  background-color: #f0f4f8;
  color: #3b82f6;
  font-weight: 600;
  font-size: 16px;
  position: relative;
  overflow: hidden;
  transition: background-color 0.3s;

  &:hover {
    background-color: #dbeafe;
  }
`;

const PreviewImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  border-radius: 12px;
  padding: 20px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: white;
`;

const PlaceholderText = styled.span`
  z-index: 1;
  display: flex;
  gap: 10px;
`;

const RemoveButton = styled.button`
  position: absolute;
  bottom: 8px;
  right: 8px;
  background-color: rgba(255, 255, 255, 0.8);
  border: none;
  border-radius: 6px;
  padding: 4px 8px;
  font-size: 14px;
  color: #ef4444;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #fee2e2;
  }
`;

const UnderlineInput = styled.input`
  border: none;
  padding: 10px 5px;
  font-size: 16px;
  outline: none;
  width: 100%;
  background: transparent;
`;

const MoimNameTitle = styled.h2`
  text-align: center;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  color: #1e293b;
  font-weight: 600;
  & > svg {
    margin: 0 10px;
  }
`;

const MoimNameContainer = styled.div`
  width: 70vw;
  max-width: 800px;
  background-color: #f0f4f8;
  padding: 50px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
`;

const MoimName = () => {
  const { moimData, setTitle, setimage } = useMoimStore().MoimDataStore;
  const [preview, setPreview] = useState(moimData.image || "");

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onloadend = () => {
      const base64 = reader.result as string;
      setPreview(base64);
      setimage(base64);
    };
    reader.readAsDataURL(file);
  };

  const handleRemoveImage = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    setPreview("");
    setimage("");
  };

  return (
    <MoimNameContainer>
      <MoimNameTitle>
        <Pencil size={24} />
        어떤 모임을 만들고 싶으신가요?
        <Pencil size={24} style={{ transform: "scaleX(-1)" }} />
      </MoimNameTitle>

      <Fieldset>
        <Legend>개설할 모임 이름</Legend>
        <UnderlineInput
          type="text"
          placeholder="모임 이름을 입력하세요"
          value={moimData.title}
          onChange={(e) => setTitle(e.target.value)}
        />
      </Fieldset>

      <Fieldset2>
        <Legend>모임 타이틀 이미지</Legend>
        <FileLabel htmlFor="file-upload">
          {!preview && (
            <PlaceholderText>
              <Camera size={24} />
              이미지 선택하기
            </PlaceholderText>
          )}
          {preview && (
            <>
              <PreviewImage src={preview} alt="미리보기 이미지" />
              <RemoveButton onClick={handleRemoveImage} type="button">
                이미지 취소
              </RemoveButton>
            </>
          )}
        </FileLabel>

        <HiddenInput
          id="file-upload"
          type="file"
          accept="image/*"
          onChange={handleFileChange}
        />
      </Fieldset2>
    </MoimNameContainer>
  );
};

export default MoimName;
