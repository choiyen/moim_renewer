import styled from "@emotion/styled";
import { ArrowRight, Plus, Trash2 } from "lucide-react"; // TrashIcon 대체
import { useState } from "react";
import { hopper } from "../../../../types/MoimType";

const SubContainer = styled.div`
  width: 100%;
  flex: 1;
  background-color: white;
  border-radius: 4px;
  padding: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  gap: 8px;
`;

const CategoryItem = styled.div`
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #f9f9f9;
  cursor: pointer;
`;

const HopperStyle = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #f9f9f9;
  cursor: grab;

  &:hover {
    background-color: #f1f1f1;
  }

  input {
    width: 100%;
    padding: 4px 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: inherit;
  }
`;
const MoimSubCategory = () => {
  const [categories, setCategories] = useState(hopper);
  const [selectedCategory, setSelectedCategory] = useState<string[]>([]);

  const [editingId, setEditingId] = useState<number | null>(null);
  const [subEditingId, setSubEditingId] = useState<number | null>(null);

  const [draggedItem, setDraggedItem] = useState<{
    type: "main" | "sub";
    index: number;
  } | null>(null);

  const [isDragging, setIsDragging] = useState(false);

  const handleSaveMain = (id: number, value: string) => {
    if (value.trim() === "") {
      setCategories((prev) => prev.filter((cat) => cat.id !== id));
    } else {
      setCategories((prev) =>
        prev.map((cat) => (cat.id === id ? { ...cat, Category: value } : cat))
      );
    }
    setEditingId(null);
  };

  const handleSaveSub = (index: number, value: string) => {
    const newSub = [...selectedCategory];
    if (value.trim() === "") {
      newSub.splice(index, 1);
    } else {
      newSub[index] = value;
    }
    setSelectedCategory(newSub);
    setSubEditingId(null);
  };

  const dragging = (
    draggedItem: { type: "main" | "sub"; index: number } | null
  ) => {
    if (!draggedItem) return;

    if (draggedItem.type === "main") {
      const newCats = [...categories];
      newCats.splice(draggedItem.index, 1);
      setCategories(newCats);
    }
    if (draggedItem.type === "sub") {
      const newSub = [...selectedCategory];
      newSub.splice(draggedItem.index, 1);
      setSelectedCategory(newSub);
    }

    setDraggedItem(null);
    setIsDragging(false);
  };

  return (
    <>
      {/* Main Category */}
      <SubContainer>
        <div style={{ textAlign: "center" }}>Main Category</div>
        <CategoryItem>
          {categories.map((item, idx) => (
            <HopperStyle
              key={item.id}
              draggable
              onDragStart={() => {
                setDraggedItem({ type: "main", index: idx });
                setIsDragging(true);
              }}
              onDragEnd={() => {
                setDraggedItem(null);
                setIsDragging(false);
              }}
              onClick={() => setSelectedCategory(item.SubCategory)}
              onDoubleClick={() => setEditingId(item.id)}
            >
              {editingId === item.id ? (
                <input
                  autoFocus
                  defaultValue={item.Category}
                  onBlur={(e) => handleSaveMain(item.id, e.target.value)}
                  onKeyDown={(e) => {
                    if (e.key === "Enter")
                      handleSaveMain(
                        item.id,
                        (e.target as HTMLInputElement).value
                      );
                    if (e.key === "Escape") setEditingId(null);
                  }}
                />
              ) : (
                item.Category
              )}
            </HopperStyle>
          ))}

          {/* 플러스 / 휴지통 버튼 */}
          <button
            onClick={() => {
              if (!isDragging) {
                const newCat = {
                  id: Date.now(),
                  Category: "",
                  SubCategory: [],
                };
                setCategories((prev) => [...prev, newCat]);
                setEditingId(newCat.id);
              } else if (draggedItem?.type === "main") {
                // 삭제 처리
                const newCats = [...categories];
                newCats.splice(draggedItem.index, 1);
                setCategories(newCats);
              }
            }}
            onDragOver={(e) => {
              e.preventDefault(); // drop 허용
            }}
            onDrop={() => {
              dragging(draggedItem);
            }}
            style={{
              marginTop: "8px",
              width: "100%",
              padding: "8px",
              borderRadius: "4px",
              display: "flex",
              justifyContent: "center",
              backgroundColor: "#f9f9f9",
            }}
          >
            {isDragging && draggedItem?.type === "main" ? (
              <Trash2 size={36} color="red" />
            ) : (
              <Plus size={36} color="green" />
            )}
          </button>
        </CategoryItem>
      </SubContainer>

      <div style={{ alignSelf: "center", color: "white" }}>
        <ArrowRight />
      </div>

      {/* Sub Category */}
      <SubContainer>
        <div style={{ textAlign: "center" }}>Sub Category</div>
        <CategoryItem>
          {selectedCategory.map((item, idx) => (
            <HopperStyle
              key={idx}
              draggable
              onDragStart={() => {
                setDraggedItem({ type: "sub", index: idx });
                setIsDragging(true);
              }}
              onDragEnd={() => {
                setDraggedItem(null);
                setIsDragging(false);
              }}
              onDoubleClick={() => setSubEditingId(idx)}
            >
              {subEditingId === idx ? (
                <input
                  autoFocus
                  defaultValue={item}
                  onBlur={(e) => handleSaveSub(idx, e.target.value)}
                  onKeyDown={(e) => {
                    if (e.key === "Enter")
                      handleSaveSub(idx, (e.target as HTMLInputElement).value);
                    if (e.key === "Escape") setSubEditingId(null);
                  }}
                />
              ) : (
                item
              )}
            </HopperStyle>
          ))}

          <button
            onClick={() => {
              if (!isDragging) {
                setSelectedCategory((prev) => [...prev, ""]);
                setSubEditingId(selectedCategory.length);
              }
              if (draggedItem?.type === "sub") {
                const newSub = [...selectedCategory];
                newSub.splice(draggedItem.index, 1);
                setSelectedCategory(newSub);
              }
            }}
            onDragOver={(e) => {
              e.preventDefault(); // drop 허용
            }}
            onDrop={() => {
              dragging(draggedItem);
            }}
            style={{
              marginTop: "8px",
              width: "100%",
              padding: "8px",
              borderRadius: "4px",
              display: "flex",
              justifyContent: "center",
              backgroundColor: "#f9f9f9",
            }}
          >
            {isDragging && draggedItem?.type === "sub" ? (
              <Trash2 size={36} color="red" />
            ) : (
              <Plus size={36} color="green" />
            )}
          </button>
        </CategoryItem>
      </SubContainer>
    </>
  );
};

export default MoimSubCategory;
