import DecoupledEditor from "@ckeditor/ckeditor5-build-decoupled-document";
import { CKEditor } from "@ckeditor/ckeditor5-react";
import styled from "@emotion/styled";
import { useRef } from "react";
import { useMoimStore } from "../../../../types/State";
import { Compass } from "lucide-react";
import { MapPin, FileText } from "lucide-react";

const Fieldset = styled.fieldset`
  border: none;
  border-bottom: 2px solid #ccc;
  padding: 0px;
  margin-bottom: 30px;
  width: 100%;
`;
const Legend = styled.legend`
  display: flex;
  font-size: 18px;
  font-weight: 600;
  color: #1e293b;
  padding: 10px;
`;

const ToolbarContainer = styled.div`
  width: 40vw;
  padding: 10px;
  margin-bottom: 10px;
`;

const EditorWrapper = styled.div`
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  background-color: white;
  min-height: 200px;
`;

const MoimContentContainer = styled.div`
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

const MoimContentTitle = styled.h3`
  display: flex;
  font-size: 25px;
  padding: 20px;
  gap: 15px;
`;
const UnderlineInput = styled.input`
  border: none;
  padding: 10px 5px;

  font-size: 16px;
  outline: none;
  width: 100%;
  background: transparent;
`;

const MoimContent = () => {
  const toolbarRef = useRef<HTMLDivElement | null>(null);
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const editorRef = useRef<any>(null);
  const MoimData = useMoimStore().MoimDataStore.moimData;
  const MoimDetailData = useMoimStore().MoimDataStore.moimDetail;
  const { setContent, setDescription } = useMoimStore().MoimDataStore;

  return (
    <MoimContentContainer>
      <MoimContentTitle>
        <Compass size={36} />
        사람을 모을 수 있도록 모임을 소개해볼까요?
        <Compass size={36} style={{ transform: "scaleX(-1)" }} />
      </MoimContentTitle>
      <Fieldset>
        <Legend>
          <MapPin />
          모임 한줄 소개
        </Legend>
        <UnderlineInput
          type="text"
          placeholder="모임에 대해 한줄로 소개해보세요?(15글자 이내)"
          value={MoimData.description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </Fieldset>
      <Fieldset>
        <Legend>
          <FileText />
          모임 소개글
        </Legend>
        <ToolbarContainer ref={toolbarRef} />
        <EditorWrapper>
          <CKEditor
            // eslint-disable-next-line @typescript-eslint/no-explicit-any
            editor={DecoupledEditor as any}
            data={MoimDetailData.content}
            onReady={(editor) => {
              const toolbar = editor.ui?.view?.toolbar?.element;
              if (
                toolbarRef.current &&
                toolbar &&
                !toolbarRef.current.contains(toolbar)
              ) {
                toolbarRef.current.innerHTML = ""; // Clear previous toolbar
                toolbarRef.current.appendChild(toolbar);
              }

              // ✅ ref 설정은 조건 없이 할당
              editorRef.current = editor;
            }}
            onChange={(_event, editor) => {
              const data = editor.getData();
              setContent(data);
            }}
            onError={(error, { willEditorRestart }) => {
              if (willEditorRestart) {
                console.warn("CKEditor will restart due to error:", error);

                // 안정성 확보: 실제 DOM이 존재하는 경우에만 초기화
                if (toolbarRef.current instanceof HTMLElement) {
                  toolbarRef.current.innerHTML = "";
                }

                editorRef.current = null;
              } else {
                console.error("CKEditor unrecoverable error:", error);
              }
            }}
            config={{
              placeholder: "모임에 대한 자세한 설명을 입력하세요.",
            }}
          />
        </EditorWrapper>
      </Fieldset>
    </MoimContentContainer>
  );
};

export default MoimContent;
