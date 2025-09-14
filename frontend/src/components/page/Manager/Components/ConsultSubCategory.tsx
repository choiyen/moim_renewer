import styled from "@emotion/styled";
import { useState } from "react";
import { ConsultType } from "../../../../types/MoimDataDummy";
import { Plus, Trash2 } from "lucide-react";

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
  id: number;
  Category: string;
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
const ConsultSubCategory = () => {
  const [isDragging, setIsDragging] = useState(false);
  const [draggedItem, setDraggedItem] = useState<number | null>(null);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [ConsultCategory, setConsultCategory] = useState<ConsultCategory[]>(
    Object.values(ConsultType).map((cat, idx) => ({
      id: idx,
      Category: cat as string,
    }))
  );

  const handleSave = (id: number, newValue: string) => {
    if (newValue.trim() === "") {
      // 값이 비어있으면 추가된 항목 제거
      setConsultCategory((prev) => prev.filter((cat) => cat.id !== id));
    } else {
      setConsultCategory((prev) =>
        prev.map((cat) =>
          cat.id === id ? { ...cat, Category: newValue } : cat
        )
      );
    }
    setEditingId(null);
  };
  const dragging = (draggedId: number | null) => {
    if (draggedId === null) return;
    setConsultCategory((prev) => prev.filter((cat) => cat.id !== draggedId));
    setIsDragging(false);
    setDraggedItem(null);
  };

  return (
    <SubCategoryContainer>
      <TitleStyle>상담 카테고리 설정</TitleStyle>
      <SubContainer>
        <SubCategoryConsultContainer>
          {ConsultCategory.map((item) => (
            <ConsultStyle
              key={item.id}
              onDoubleClick={() => setEditingId(item.id)}
              draggable
              onDragStart={() => {
                setDraggedItem(item.id);
                setIsDragging(true);
              }}
              onDragEnd={() => {
                setDraggedItem(null);
                setIsDragging(false);
              }}
            >
              {editingId === item.id ? (
                <input
                  type="text"
                  defaultValue={item.Category}
                  autoFocus
                  onBlur={(e) => handleSave(item.id, e.target.value)}
                  onKeyDown={(e) => {
                    if (e.key === "Enter") {
                      const inputValue = (e.target as HTMLInputElement).value;
                      if (inputValue.trim() === "") {
                        setEditingId(null);
                        return;
                      } else {
                        handleSave(item.id, inputValue);
                      }
                    } else if (e.key === "Escape") {
                      setEditingId(null);
                    }
                  }}
                />
              ) : (
                item.Category
              )}
            </ConsultStyle>
          ))}
          <button
            onClick={() => {
              const newCategory = {
                id:
                  ConsultCategory.length > 0
                    ? ConsultCategory[ConsultCategory.length - 1].id + 1
                    : 1,
                Category: "",
                SubCategory: [],
              };
              setConsultCategory((prev) => [...prev, newCategory]);
              setEditingId(newCategory.id);
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
