import styled from "@emotion/styled";
import { ArrowRight, Plus, Trash2 } from "lucide-react";
import { useEffect, useState } from "react";
import { DELETE, GET, POST, PUT } from "../../../comon/axios/axiosInstance";
import { type MoimCategorySub } from "../../../../types/MoimType";

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

interface MoimCategory {
  MoimCategoryId: number;
  categorisation: string;
  SubCategory: MoimCategorySub[];
}

const MoimSubCategory = () => {
  const [categories, setCategories] = useState<MoimCategory[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<MoimCategorySub[]>(
    []
  );
  const [editingId, setEditingId] = useState<number | null>(null);
  const [subEditingId, setSubEditingId] = useState<number | string | null>(
    null
  );
  const [draggedItem, setDraggedItem] = useState<{
    type: "main" | "sub";
    index: number;
  } | null>(null);
  const [isDragging, setIsDragging] = useState(false);
  const [SelectCategory, setSelectCategory] = useState<number>(0);

  // 초기 데이터 불러오기
  useEffect(() => {
    GET({ url: "/category" })
      .then((res) => {
        console.log(res.data);
        if (res.resultType === "success") setCategories(res.data);
      })
      .catch((err) => {
        if (err.response?.data?.resultType === "empty")
          alert(err.response.data.message);
        else console.log(err);
      });
  }, []);

  const handleSaveMain = async (id: number, value: string) => {
    if (value.trim() === "") {
      DELETE({
        url: "/category",
        data: { MoimCategoryId: id },
      }).then((res) => {
        console.log(res);
        // 빈 값 → 삭제
        setCategories((prev) =>
          (prev ?? []).filter((cat) => cat.MoimCategoryId !== id)
        );
      });
    } else if (id < 0) {
      // 음수 id → 신규 생성
      const res = await POST({
        url: "/category",
        data: { categorisation: value },
      });
      const newId = res.data.MoimCategoryId;

      setCategories((prev) =>
        (prev ?? []).map((cat) =>
          cat.MoimCategoryId === id
            ? { ...cat, MoimCategoryId: newId, categorisation: value }
            : cat
        )
      );
    } else {
      // 양수 id → 수정
      await PUT({
        url: "/category",
        data: { MoimCategoryId: id, categorisation: value },
      });

      setCategories((prev) =>
        (prev ?? []).map((cat) =>
          cat.MoimCategoryId === id ? { ...cat, categorisation: value } : cat
        )
      );
    }

    setEditingId(null);
  };

  // Sub Category 저장
  const handleSaveSub = (id: string, value: string) => {
    const newSub = [...selectedCategory];
    const index = newSub.findIndex((sub) => sub.id === id);
    if (index === -1) return;
    if (SelectCategory == 0) {
      alert("데이터 저장 불가능!!");
      window.location.reload();
    }
    if (value.trim() === "") {
      console.log(id);
      DELETE({
        url: "/CategoryDetail",
        data: {
          categoryDetailId: id,
        },
      }).then((res) => {
        console.log(res);
        newSub.splice(index, 1);
        setSelectedCategory(newSub);
      });
    } else {
      if (id == "Space") {
        POST({
          url: "/CategoryDetail",
          data: {
            MoimCategoryId: SelectCategory,
            MoimcategorisationDetail: value,
          },
        });
      } else {
        PUT({
          url: "/CategoryDetail",
          data: {
            MoimCategoryId: SelectCategory,
            MoimcategoryDetailId: id,
            MoimcategorisationDetail: value,
          },
        });
      }
      newSub[index].SubCategoryName = value;
      setSelectedCategory(newSub);
    }

    setSubEditingId(null);
  };

  // 드래그 삭제 처리
  const dragging = (
    draggedItem: { type: "main" | "sub"; index: number } | null
  ) => {
    if (
      confirm(
        "정말로 데이터를 삭제하시겠습니까?(메인 카테고리 삭제 시 서브 카테고리도 삭제)"
      )
    ) {
      if (!draggedItem) return;
      if (draggedItem.type === "main") {
        const category = categories[draggedItem.index];
        console.log(category);

        DELETE({
          url: "/category",
          data: { MoimCategoryId: category.MoimCategoryId },
        }).then((res) => {
          console.log(res);
          // 빈 값 → 삭제
          const newCats = [...categories];
          newCats.splice(draggedItem.index, 1);
          setCategories(newCats);
        });
      }
      if (draggedItem.type === "sub") {
        const category = selectedCategory[draggedItem.index];
        console.log(category);
        DELETE({
          url: "/CategoryDetail",
          data: {
            categoryDetailId: category.id,
          },
        }).then((res) => {
          console.log(res);
        });
        const newSub = [...selectedCategory];
        newSub.splice(draggedItem.index, 1);
        setSelectedCategory(newSub);
      }
      setDraggedItem(null);
      setIsDragging(false);
    } else {
      return;
    }
  };
  const SelectedCategory = (id: number) => {
    POST({
      url: "/CategoryDetail/get",
      data: {
        MoimCategoryId: id,
      },
    })
      .then((res) => {
        console.log(res.data);
        setSelectedCategory(
          res.data.map(
            (data: {
              MoimcategoryDetailId: string;
              MoimcategorisationDetail: string;
            }) => ({
              id: data.MoimcategoryDetailId,
              SubCategoryName: data.MoimcategorisationDetail,
            })
          )
        );
      })
      .catch((err) => {
        if (err.response.data.resultType == "empty") {
          setSelectedCategory([]);
        }
      });
  };

  return (
    <>
      {/* Main Category */}
      <SubContainer>
        <div style={{ textAlign: "center" }}>Main Category</div>
        <CategoryItem>
          {categories.length === 0 ? (
            <div style={{ color: "#aaa", textAlign: "center" }}>
              No categories found
            </div>
          ) : (
            categories &&
            categories.map((item, idx) => (
              <HopperStyle
                key={idx}
                draggable
                onDragStart={() => {
                  setDraggedItem({ type: "main", index: idx });
                  setIsDragging(true);
                }}
                onDragEnd={() => {
                  setDraggedItem(null);
                  setIsDragging(false);
                }}
                onClick={() => {
                  setSelectCategory(item.MoimCategoryId);
                  SelectedCategory(item.MoimCategoryId);
                }}
                onDoubleClick={() => setEditingId(item.MoimCategoryId)}
              >
                {editingId === item.MoimCategoryId ? (
                  <input
                    autoFocus
                    defaultValue={item.categorisation ?? ""}
                    onBlur={(e) =>
                      handleSaveMain(item.MoimCategoryId, e.target.value)
                    }
                    onKeyDown={(e) => {
                      if (e.key === "Enter")
                        handleSaveMain(
                          item.MoimCategoryId,
                          (e.target as HTMLInputElement).value
                        );
                      if (e.key === "Escape") {
                        setCategories((prev) => {
                          if (!prev) return prev; // null이면 그대로
                          return prev.filter((cat) => {
                            return cat.MoimCategoryId > 0;
                          });
                          // ID가 비어있는 임시 newCat 제거
                        });
                        setEditingId(null);
                      }
                    }}
                  />
                ) : (
                  item.categorisation ?? ""
                )}
              </HopperStyle>
            ))
          )}

          {/* 플러스 / 휴지통 버튼 */}
          <button
            onClick={() => {
              if (!isDragging) {
                const newCat = {
                  MoimCategoryId: Date.now() * -1, // 음수 id
                  categorisation: "",
                  SubCategory: [],
                };
                setCategories((prev) => [...(prev ?? []), newCat]);
                setEditingId(newCat.MoimCategoryId);
              } else if (draggedItem?.type === "main") {
                const newCats = [...categories];
                newCats.splice(draggedItem.index, 1);
                setCategories(newCats);
              }
            }}
            onDragOver={(e) => e.preventDefault()}
            onDrop={() => dragging(draggedItem)}
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
          {selectedCategory.length === 0 ? (
            <div style={{ color: "#aaa", textAlign: "center" }}>
              No subcategories found
            </div>
          ) : (
            selectedCategory &&
            selectedCategory.map((item) => (
              <HopperStyle
                key={item.id}
                draggable
                onDragStart={() => {
                  const idx = selectedCategory.findIndex(
                    (sub) => sub.id === item.id
                  );
                  setDraggedItem({ type: "sub", index: idx });
                  setIsDragging(true);
                }}
                onDragEnd={() => {
                  setDraggedItem(null);
                  setIsDragging(false);
                }}
                onDoubleClick={() => setSubEditingId(item.id)}
              >
                {subEditingId === item.id ? (
                  <input
                    autoFocus
                    defaultValue={item.SubCategoryName}
                    onBlur={(e) => handleSaveSub(item.id, e.target.value)}
                    onKeyDown={(e) => {
                      if (e.key === "Enter")
                        handleSaveSub(
                          item.id,
                          (e.target as HTMLInputElement).value
                        );
                      if (e.key === "Escape") {
                        setSubEditingId(null);
                        setSelectedCategory((prev) => {
                          if (!prev) return prev; // null이면 그대로
                          return prev.filter((cat) => {
                            return cat.id !== "Space";
                          });
                          // ID가 비어있는 임시 newCat 제거
                        });
                      }
                    }}
                  />
                ) : (
                  item.SubCategoryName
                )}
              </HopperStyle>
            ))
          )}

          <button
            onClick={() => {
              if (!isDragging) {
                const newSub = { id: "Space", SubCategoryName: "" };
                setSelectedCategory((prev) => [...(prev ?? []), newSub]);
                setSubEditingId(newSub.id);
              }
              if (draggedItem?.type === "sub") {
                const newSub = [...selectedCategory];
                newSub.splice(draggedItem.index, 1);
                setSelectedCategory(newSub);
              }
            }}
            onDragOver={(e) => e.preventDefault()}
            onDrop={() => dragging(draggedItem)}
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
