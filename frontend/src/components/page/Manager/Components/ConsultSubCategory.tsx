import styled from "@emotion/styled";
import { useEffect, useState } from "react";
import { ConsultType } from "../../../../types/MoimDataDummy";
import { Plus, Trash2 } from "lucide-react";
import { DELETE, GET, POST, PUT } from "../../../comon/axios/axiosInstance";

const SubCategoryContainer = styled.div`
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
const SubContainer = styled.div`
  flex: 1;
  display: flex;
  justify-content: center;
  background-color: aliceblue;
  border-radius: 4px;
  padding: 8px;
`;
const SubCategoryConsultContainer = styled.div`
  background-color: white;
  border-radius: 4px;
  padding: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  width: 50vw;
  gap: 8px;

  overflow-y: auto;
`;
interface ConsultCategory {
  consultCategoryId: number;
  consultType: string;
}
const ConsultStyle = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #f9f9f9;
  cursor: pointer;
  &:hover {
    background-color: #f1f1f1;
  }
  &:active {
    background-color: #e2e8f0;
    font-weight: bold;
  }

  input {
    width: 100%;
    padding: 4px 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: inherit;
  }
`;

const WarningMessage = styled.div`
  width: 100%;
  border: 1px solid #ccc;
  padding: 8px;
  text-align: center;
  border-radius: 4px;
  color: red;
`;

const ConsultSubCategory = () => {
  const [isDragging, setIsDragging] = useState(false);
  const [draggedItem, setDraggedItem] = useState<number | null>(null);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [ConsultCategory, setConsultCategory] = useState<ConsultCategory[]>(
    Object.values(ConsultType).map((cat, idx) => ({
      consultCategoryId: idx,
      consultType: cat as string,
    }))
  );

  useEffect(() => {
    GET({
      url: "/ConsultCategory",
    })
      .then((res) => {
        console.log(res);
        setConsultCategory(res.data);
      })
      .catch((err) => {
        console.log(err);
        setConsultCategory([]);
      });
  }, []);

  const handleSave = (id: number, newValue: string) => {
    if (id > 0) {
      if (newValue.trim() === "") {
        DELETE({
          url: "/ConsultCategory",
          params: {
            consultCategoryId: id,
          },
        }).then((res) => {
          console.log(res);
        });
        setConsultCategory((prev) =>
          prev.filter((cat) => cat.consultCategoryId !== id)
        );
      } else {
        PUT({
          url: "/ConsultCategory",
          data: {
            consultCategoryId: id,
            consultType: newValue,
          },
        }).then((res) => {
          console.log(res);
          setConsultCategory((prev) =>
            prev.map((item) =>
              item.consultCategoryId === id
                ? { ...item, consultType: newValue }
                : item
            )
          );
        });
      }
    } else {
      if (newValue.trim() === "") {
        setConsultCategory((prev) =>
          prev.filter((cat) => cat.consultCategoryId !== id)
        );
      } else {
        POST({
          url: "/ConsultCategory",
          data: {
            consultType: newValue,
          },
        }).then((res) => {
          console.log(res);
        });
        setConsultCategory((prev) =>
          prev.map((cat) =>
            cat.consultCategoryId === id
              ? { ...cat, consultType: newValue }
              : cat
          )
        );
      }
    }
    setEditingId(null);
  };
  const dragging = (draggedId: number | null) => {
    if (draggedId === null) return;
    if (confirm("정말로 게시판을 삭제하시겠습니까?(데이터 전부 삭제됨)")) {
      DELETE({
        url: "/ConsultCategory",
        params: {
          consultCategoryId: draggedId,
        },
      });
      setConsultCategory((prev) =>
        prev.filter((cat) => cat.consultCategoryId !== draggedId)
      );
      setIsDragging(false);
      setDraggedItem(null);
    }
  };

  return (
    <SubCategoryContainer>
      <TitleStyle>상담 카테고리 설정</TitleStyle>
      <SubContainer>
        <SubCategoryConsultContainer>
          {ConsultCategory.length > 0 ? (
            ConsultCategory &&
            ConsultCategory.map((item, keyId) => (
              <ConsultStyle
                key={keyId}
                onDoubleClick={() => setEditingId(keyId)}
                draggable
                onDragStart={() => {
                  setDraggedItem(item.consultCategoryId);
                  setIsDragging(true);
                }}
                onDragEnd={() => {
                  setDraggedItem(null);
                  setIsDragging(false);
                }}
              >
                {editingId === keyId ? (
                  <input
                    type="text"
                    defaultValue={item.consultType}
                    autoFocus
                    onBlur={(e) =>
                      handleSave(item.consultCategoryId, e.target.value)
                    }
                    onKeyDown={(e) => {
                      if (e.key === "Enter") {
                        const inputValue = (e.target as HTMLInputElement).value;
                        handleSave(item.consultCategoryId, inputValue);
                      } else if (e.key === "Escape") {
                        setEditingId(null);
                      }
                    }}
                  />
                ) : (
                  item.consultType
                )}
              </ConsultStyle>
            ))
          ) : (
            <WarningMessage>
              Empty Consult fields cannot be saved.
            </WarningMessage>
          )}
          <button
            onClick={() => {
              const newCategory = {
                consultCategoryId: -1,
                consultType: "",
              };
              setConsultCategory((prev) => [...prev, newCategory]);
              setEditingId(ConsultCategory.length);
            }}
            style={{
              textAlign: "center",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              marginTop: "8px",
              width: "100%",
              backgroundColor: "#f9f9f9",
              padding: "8px",
              borderRadius: "4px",
            }}
            onDragOver={(e) => {
              e.preventDefault(); // drop 허용
            }}
            onDrop={() => {
              dragging(draggedItem);
            }}
          >
            {isDragging ? (
              <Trash2 size={36} color="red" />
            ) : (
              <Plus size={36} color="green" />
            )}
          </button>
        </SubCategoryConsultContainer>
      </SubContainer>
    </SubCategoryContainer>
  );
};

export default ConsultSubCategory;
//드래그해서 Drop 시 진짜 삭제할 지 묻는 모달 컴포넌트 구현했으나, 추가 안됨
// 운영자가 아니면 접근 못하게 막는 기능도 추가 안됨
